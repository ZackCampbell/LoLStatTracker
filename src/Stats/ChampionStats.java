package Stats;

import API.DTO.ItemDTO;
import Database.DatabaseManager;
import GameElements.*;

import java.util.*;
import java.util.stream.Collectors;

public class ChampionStats {
    private Long championId;
    private String patch;
    private Map<Match.Lane, MatchSubset> matchesByLane;

    public ChampionStats(Long championId, String patch) {
        DatabaseManager dbmanager = DatabaseManager.getInstance();

        this.matchesByLane = new HashMap<>();
        this.championId = championId;
        this.patch = patch;

        List<Match> matches = dbmanager.getMatchesWithChampionsFromPatch(Collections.singletonList(championId), patch);

        Map<Match.Lane, List<Match>> grouped = matches.stream().collect(Collectors.groupingBy(
                match -> match.getParticipantOfChampion(championId).getLane()
        ));

        /// TODO: need to figure out what to do with invalidly grouped matches

        for (Map.Entry<Match.Lane, List<Match>> e : grouped.entrySet()) {
            this.matchesByLane.put(e.getKey(), new MatchSubset(e.getValue(), matches.size()));
        }
    }

    public List<ItemDTO> getLatestCondensedBuild_TEST() {
        TemporalStat<ItemBuild> b = this.matchesByLane
                .get(Match.Lane.MIDDLE)
                .getBuilds(this.championId, patch, Match.PatchMatchMode.MINOR_VERSION);

        return b.getLastSample().getData().getCondensed().stream().map(ItemWrapper::getItem).collect(Collectors.toList());
    }
}
