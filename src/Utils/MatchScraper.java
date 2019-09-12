package Utils;

import API.DTO.LeagueItemDTO;
import API.DTO.LeagueListDTO;
import API.DTO.MatchReferenceDTO;
import API.DTO.SummonerDTO;
import API.LeagueEndpoint;
import API.MatchEndpoint;
import API.RiotAPIHandler;
import API.SummonerEndpoint;
import Database.DatabaseManager;
import GameElements.Match;
import Main.Session;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class MatchScraper {

    public static void getMatchesFromTopOfLadderForPatch(String patch, Match.PatchMatchMode mode) {
        String[] regions = {"na1", "euw1", "eun1", "br1", "kr"};
        CountDownLatch latch = new CountDownLatch(regions.length);

        for (String r : regions) {
            Thread t = new Thread(() -> {
                MatchScraper.getMatchesFromTopOfLadderForPatch(r, patch, mode);
                latch.countDown();
            });
            t.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Set<Long> getMatchesFromTopOfLadderForPatch(String region, String patch, Match.PatchMatchMode patchMatchMode) {
        System.out.println("Scraping for region: " + region + ", on patch: " + patch);
        DatabaseManager dbmanager = DatabaseManager.getInstance();
        RiotAPIHandler api = new RiotAPIHandler();
        LeagueEndpoint leagueEndpoint = new LeagueEndpoint(region, api.getApi_key());
        MatchEndpoint matchEndpoint = new MatchEndpoint(region, api.getApi_key());
        SummonerEndpoint summonerEndpoint = new SummonerEndpoint(region, api.getApi_key());

        Session.getInstance().setCurrentRegion(region);

        Set<Long> foundGameIds = new HashSet<>(dbmanager.getAllMatchIds());

        LeagueListDTO challengerFlex = leagueEndpoint.getChallengerLeagueByQueue(LeagueEndpoint.LeagueQueueType.RANKED_FLEX_SR);
        LeagueListDTO challengerSolo = leagueEndpoint.getChallengerLeagueByQueue(LeagueEndpoint.LeagueQueueType.RANKED_SOLO_5X5);

        LeagueListDTO grandmasterFlex = leagueEndpoint.getGrandmasterLeagueByQueue(LeagueEndpoint.LeagueQueueType.RANKED_FLEX_SR);
        LeagueListDTO grandmasterSolo = leagueEndpoint.getGrandmasterLeagueByQueue(LeagueEndpoint.LeagueQueueType.RANKED_SOLO_5X5);

        LeagueListDTO masterFlex = leagueEndpoint.getMasterLeagueByQueue(LeagueEndpoint.LeagueQueueType.RANKED_FLEX_SR);
        LeagueListDTO masterSolo = leagueEndpoint.getMasterLeagueByQueue(LeagueEndpoint.LeagueQueueType.RANKED_SOLO_5X5);

        List<LeagueItemDTO> leagues = new ArrayList<>();

        if (challengerFlex != null) {
            leagues.addAll(challengerFlex.getEntries());
        }

        if (challengerSolo != null) {
            leagues.addAll(challengerSolo.getEntries());
        }

        if (grandmasterFlex != null) {
            leagues.addAll(grandmasterFlex.getEntries());
        }

        if (grandmasterSolo != null) {
            leagues.addAll(grandmasterSolo.getEntries());
        }

        if (masterFlex != null) {
            leagues.addAll(masterFlex.getEntries());
        }

        if (masterSolo != null) {
            leagues.addAll(masterSolo.getEntries());
        }

        for (LeagueItemDTO league : leagues) {
            System.out.println(league);
            if (league == null) {
                continue;
            }

            SummonerDTO summoner = summonerEndpoint.getSummonerByName(league.getSummonerName());

            if (summoner == null) {
                System.out.println("Skipped: " + league.getSummonerName());
                continue;
            }

            List<MatchReferenceDTO> matches = matchEndpoint.getMatchListByAccountIdWithQueueTypes(
                    summoner.getAccountId(),
                    Arrays.asList(Match.QueueType.SR_RANKED_FLEX, Match.QueueType.SR_RANKED_SOLO)
            );

            if (matches == null) {
                System.out.println("Skipped matches for: " + league.getSummonerName());
                continue;
            }

            List<Match> matchSubset = new ArrayList<>();

            for (MatchReferenceDTO match : matches) {
                if (foundGameIds.contains(match.getGameId())) {
                    System.out.println("Already found: " + match.getGameId());
                    continue;
                }

                Match m = new Match(region, match.getGameId());

                if (m.isValid() && m.isFromPatch(patch, patchMatchMode)) {
                    if (foundGameIds.add(match.getGameId())) {
                        matchSubset.add(m);
                        System.out.println(
                                "Added: " + match.getGameId() +
                                " - patch: " + m.getGameVersion() +
                                " (" + foundGameIds.size() + ")"
                        );
                    }
                } else {
                    /// Not a patch-matching game.. doesn't help to go further back in history
                    break;
                }
            }

            dbmanager.addMatches(matchSubset);
        }

        return foundGameIds;
    }
}
