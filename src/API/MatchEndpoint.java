package API;

import API.DTO.MatchDTO;
import API.DTO.MatchListDTO;
import API.DTO.MatchTimelineDTO;

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

    public MatchDTO getMatchById(long matchId) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("matches") + matchId);

        if (requestUrl == null) {
            return null;
        }

        return this.send(requestUrl, RequestMethod.GET, MatchDTO.class);
    }

    public MatchListDTO getMatchListByAccountId(String accountId) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("matchlists/by-account") + accountId);

        if (requestUrl == null) {
            return null;
        }

        return this.send(requestUrl, RequestMethod.GET, MatchListDTO.class);
    }

    public MatchTimelineDTO getMatchTimelinesById(long matchId) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("timelines/by-match") + matchId);

        if (requestUrl == null) {
            return null;
        }

        return this.send(requestUrl, RequestMethod.GET, MatchTimelineDTO.class);
    }

    public long[] getMatchIdsByTournamentCode(long tournamentCode) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("matches/by-tournament-code/") + tournamentCode + "/ids");

        if (requestUrl == null) {
            return null;
        }

        return this.send(requestUrl, RequestMethod.GET, long[].class);
    }

    public MatchDTO getMatchByIdAndTournamentCode(long matchId, long tournamentCode) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("matches") + matchId + "/by-tournament-code/" + tournamentCode);

        if (requestUrl == null) {
            return null;
        }

        return this.send(requestUrl, RequestMethod.GET, MatchDTO.class);
    }
}
