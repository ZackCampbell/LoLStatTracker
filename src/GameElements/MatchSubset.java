package GameElements;

import Stats.TemporalNumberStat;
import Stats.TemporalStat;
import Stats.TimeSample;

import java.util.Comparator;
import java.util.List;

public class MatchSubset {

    private List<Match> matches;
    private int totalMatches;

    public MatchSubset(List<Match> matches, int totalMatches) {
        this.matches = matches;
        this.totalMatches = totalMatches;

        this.matches.sort(Comparator.comparing(Match::getTimestamp));
    }

    public float getSubsetPercentage() {
        return this.matches.size() / (totalMatches * 1.f);
    }

    public TemporalNumberStat<Float> getWinRate(long championId, String fromPatch, Match.PatchMatchMode mode) {
        TemporalNumberStat<Float> winRate = new TemporalNumberStat<>();
        int games = 0;
        int won = 0;

        for (Match match : this.matches) {
            if (!match.isFromPatch(fromPatch, mode)) {
                continue;
            }

            Match.WinCondition winCondition = match.getWinConditionForChampion(championId);

            if (winCondition == Match.WinCondition.WIN) {
                won++;
                games++;
            } else if (winCondition == Match.WinCondition.LOSS) {
                games++;
            }
            /// Not incrementing game counter for remakes. Maybe we'll want this?

            winRate.addSample(
                    new TimeSample<>(
                            won / (games * 1.f),
                            match.getTimestamp().getTime(),
                            match.getGameVersion()
                    )
            );
        }

        return winRate;
    }

    public TemporalStat<ItemBuild> getBuilds(long championId, String fromPatch, Match.PatchMatchMode mode) {
        TemporalStat<ItemBuild> itemBuilds = new TemporalStat<>();
        for (Match match : this.matches) {
            if (!match.isFromPatch(fromPatch, mode)) {
                continue;
            }

            MatchParticipantTimeline timeline = match.getChampionTimeline(championId);
            ItemBuild build = new ItemBuild(timeline);

            Match.WinCondition winCondition = match.getWinConditionForChampion(championId);

            if (winCondition == Match.WinCondition.WIN) {
                build.isWinningBuild = true;
            } else if (winCondition == Match.WinCondition.REMAKE) {
                continue;
            }

            itemBuilds.addSample(
                    new TimeSample<>(
                            build,
                            match.getTimestamp().getTime(),
                            match.getGameVersion()
                    )
            );
        }

        return itemBuilds;
    }
}
