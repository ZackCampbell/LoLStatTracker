package API;

import java.io.IOException;
import java.net.URL;

public class MatchEndpoint extends Endpoint {

    public MatchEndpoint(String regionName, String apiKey) {
        super(regionName, apiKey);
    }

    @Override
    String getEndpointName() {
        return "match";
    }

    @Override
    int getVersion() {
        return 4;
    }

    // TODO: make this return a Match object
    public Object getMatchById(int matchId) throws IOException {
        URL requestUrl = new URL(this.getBaseUrl("matches") + matchId);

        return this.send(requestUrl, RequestMethod.GET, Object.class);
    }

    // TODO: make this return a list of Match objects
    public Object getMatchListByAccountId(int accountId) throws IOException {
        URL requestUrl = new URL(this.getBaseUrl("matchlists/by-account") + accountId);

        return this.send(requestUrl, RequestMethod.GET, Object.class);
    }

    // TODO: make this return a list of MatchTimeline objects
    public Object[] getMatchTimelinesById(int matchId) throws IOException {
        URL requestUrl = new URL(this.getBaseUrl("timelines/by-match") + matchId);

        return this.send(requestUrl, RequestMethod.GET, Object[].class);
    }

    public long[] getMatchIdsByTournamentCode(int tournamentCode) throws IOException {
        URL requestUrl = new URL(this.getBaseUrl("matches/by-tournament-code/") + tournamentCode + "/ids");

        return this.send(requestUrl, RequestMethod.GET, long[].class);
    }

    public Object getMatchByIdAndTournamentCode(int matchId, int tournamentCode) throws IOException {
        URL requestUrl = new URL(this.getBaseUrl("matches") + matchId + "/by-tournament-code/" + tournamentCode);

        return this.send(requestUrl, RequestMethod.GET, Object.class);
    }
}
