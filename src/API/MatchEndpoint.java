package API;

import API.DTO.MatchDTO;
import API.DTO.MatchListDTO;
import API.DTO.MatchReferenceDTO;
import API.DTO.MatchTimelineDTO;
import GameElements.Match;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        try {
            EndpointRequest<MatchDTO> req = this.queueRequest(requestUrl, RequestMethod.GET, MatchDTO.class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public List<MatchDTO> getMatchesByIds(List<Long> matchIds) {
        List<EndpointRequest<MatchDTO>> requests = new ArrayList<>();

        for (Long id : matchIds) {
            URL requestUrl = this.buildUrl(this.getBaseUrl("matches") + id);
            requests.add(new EndpointRequest<>(this, requestUrl, RequestMethod.GET, MatchDTO.class));
        }

        try {
            EndpointThrottler.getInstance().queueMultiple(requests);
        } catch (InterruptedException e) {
            System.out.println("Interrupted.."); // TODO Move to log
        }

        return requests.stream().map(EndpointRequest::getResponse).collect(Collectors.toList());
    }

    public MatchListDTO getMatchListByAccountId(String accountId) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("matchlists/by-account") + accountId);

        if (requestUrl == null) {
            return null;
        }

        try {
            EndpointRequest<MatchListDTO> req = this.queueRequest(requestUrl, RequestMethod.GET, MatchListDTO.class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public List<MatchReferenceDTO> getMatchListByAccountIdWithQueueTypes(String accountId, List<Match.QueueType> queueTypes) {
        return getMatchListByAccountIdWithQueueTypes(accountId, queueTypes, 0, Integer.MAX_VALUE);
    }

    public List<MatchReferenceDTO> getMatchListByAccountIdWithQueueTypes(String accountId, List<Match.QueueType> queueTypes, int cap) {
        return getMatchListByAccountIdWithQueueTypes(accountId, queueTypes, 0, cap);
    }

    public List<MatchReferenceDTO> getMatchListByAccountIdWithQueueTypes(String accountId, List<Match.QueueType> queueTypes, int beginIndex, int cap) {
        StringBuilder queryString = new StringBuilder();

        for (Match.QueueType queueType : queueTypes) {
            queryString.append(queryString.length() == 0 ? "?" : "&").append("queue=").append(queueType.getId());
        }

        String baseUrl = this.getBaseUrl("matchlists/by-account") + accountId + queryString.toString();

        List<MatchReferenceDTO> matches = new ArrayList<>();

        while (matches.size() < cap) {
            String beginString = "&beginIndex=" + beginIndex;
            URL requestUrl = this.buildUrl(baseUrl + beginString);

            if (requestUrl == null) {
                break;
            }

            try {
                EndpointRequest<MatchListDTO> req = this.queueRequest(requestUrl, RequestMethod.GET, MatchListDTO.class);
                MatchListDTO ml = req.getResponse();

                matches.addAll(ml.getMatches());
                beginIndex = ml.getEndIndex();

                if (ml.getEndIndex().equals(ml.getTotalGames())) {
                    break;
                }
            } catch (InterruptedException e) {
                break;
            }
        }

        return matches;
    }

    public MatchTimelineDTO getMatchTimelineById(long matchId) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("timelines/by-match") + matchId);

        if (requestUrl == null) {
            return null;
        }

        try {
            EndpointRequest<MatchTimelineDTO> req = this.queueRequest(requestUrl, RequestMethod.GET, MatchTimelineDTO.class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public long[] getMatchIdsByTournamentCode(long tournamentCode) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("matches/by-tournament-code/") + tournamentCode + "/ids");

        if (requestUrl == null) {
            return null;
        }

        try {
            EndpointRequest<long[]> req = this.queueRequest(requestUrl, RequestMethod.GET, long[].class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public MatchDTO getMatchByIdAndTournamentCode(long matchId, long tournamentCode) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("matches") + matchId + "/by-tournament-code/" + tournamentCode);

        if (requestUrl == null) {
            return null;
        }

        try {
            EndpointRequest<MatchDTO> req = this.queueRequest(requestUrl, RequestMethod.GET, MatchDTO.class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }
}
