package Stats;

import Database.DatabaseManager;
import GameElements.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChampionStats {

    private List<Match> matches;
    private TemporalNumberStat<Float> winRate;
    private TemporalStat<ItemBuild> coreItemBuilds;
    private Long championId;

    public ChampionStats(Long championId, String patch) {
        DatabaseManager dbmanager = DatabaseManager.getInstance();

        this.championId = championId;
        this.matches = dbmanager.getMatchesWithChampionsFromPatch(Collections.singletonList(championId), patch);
        this.matches.sort(Comparator.comparing(Match::getTimestamp));

        Map<Match.Lane, List<Match>> matchesByLane = this.matches.stream().collect(Collectors.groupingBy(
                match -> {
                   return match.getParticipantOfChampion(championId).getLane();
                }
        ));

        for (Map.Entry<Match.Lane, List<Match>> e : matchesByLane.entrySet()) {
            System.out.println(e.getKey().getPrettyValue() + ": " + e.getValue().size());
        }

        System.out.println(matches.size() + " matches");

        this.winRate = new TemporalNumberStat<>();
        this.calculateWinRate(1);

        System.out.println(this.winRate.getLastSample());

        this.coreItemBuilds = new TemporalStat<>();
        this.aggregateBuilds();
    }

    public void calculateWinRate(int samplingRate) {
        int games = 0;
        int won = 0;

        int batchCounter = 0;

        for (Match match : this.matches) {
            Match.WinCondition winCondition = match.getWinConditionForChampion(this.championId);
            if (winCondition == Match.WinCondition.WIN) {
                won++;
                games++;
                batchCounter++;
            } else if (winCondition == Match.WinCondition.LOSS) {
                games++;
                batchCounter++;
            }

            /// Not incrementing game counter for remakes. Maybe we'll want this?

            if (batchCounter >= samplingRate) {
                this.winRate.addSample(
                        new TimeSample<>(
                                won / (games * 1.f),
                                match.getTimestamp().getTime(),
                                match.getGameVersion()
                        )
                );
                batchCounter = 0;
            }
        }
    }

    public void aggregateBuilds() {
        for (Match match : this.matches) {
            MatchParticipantTimeline timeline = match.getChampionTimeline(this.championId);
            ItemBuild build = new ItemBuild(timeline);

            Match.WinCondition winCondition = match.getWinConditionForChampion(this.championId);

            if (winCondition == Match.WinCondition.WIN) {
                build.isWinningBuild = true;
            } else if (winCondition == Match.WinCondition.REMAKE) {
                continue;
            }

            this.coreItemBuilds.addSample(
                    new TimeSample<>(
                            build,
                            match.getTimestamp().getTime(),
                            match.getGameVersion()
                    )
            );
        }

        List<ItemBuild> builds = this.coreItemBuilds.samples.stream()
                .filter(timeSample -> timeSample.getPatch().startsWith("9.17"))
                .map(TimeSample::getData)
                .collect(Collectors.toList());

        Map<Integer, List<ItemBuild>> grouped = ItemBuild.groupByCoreItems(builds);

        Integer mode = -1;
        Integer num = 0;

        for (Map.Entry<Integer, List<ItemBuild>> e : grouped.entrySet()) {
            if (e.getValue().size() >= num) {
                num = e.getValue().size();
                mode = e.getKey();
            }
        }

        System.out.println(num);

        if (mode == -1) {
            // Error
            return;
        }

        List<ItemWrapper> coreBuild = grouped.get(mode).stream().map(ItemBuild::getCoreItemPurchases).collect(Collectors.toList()).get(0);

        System.out.println(coreBuild);
    }
}
