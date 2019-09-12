package GameElements;

import java.util.List;

public class MatchSubset {

    private List<Match> matches;
    private int totalMatches;

    public MatchSubset(List<Match> matches, int totalMatches) {
        this.matches = matches;
        this.totalMatches = totalMatches;
    }
}
