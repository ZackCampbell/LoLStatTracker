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

import java.util.*;
import java.util.stream.Collectors;

public class MatchScraper {

    public static Set<Long> getMatchesFromTopOfLadderForPatch(String region, String[] patchSet, Match.PatchMatchMode patchMatchMode) {
        DatabaseManager dbmanager = DatabaseManager.getInstance();
        RiotAPIHandler api = new RiotAPIHandler();
        LeagueEndpoint leagueEndpoint = new LeagueEndpoint(region, api.getApi_key());
        MatchEndpoint matchEndpoint = new MatchEndpoint(region, api.getApi_key());
        SummonerEndpoint summonerEndpoint = new SummonerEndpoint(region, api.getApi_key());
        Set<Long> foundGameIds = new HashSet<>();

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
                Match m = new Match(match.getGameId());

                for (String patch : patchSet) {
                    if (m.isValid() && m.isFromPatch(patch, patchMatchMode)) {
                        if (foundGameIds.add(match.getGameId())) {
                            matchSubset.add(m);
                            System.out.println(foundGameIds.size());
                        }

                        break;
                    }
                }
            }

            dbmanager.addMatches(matchSubset);
        }

        return foundGameIds;
    }
}
