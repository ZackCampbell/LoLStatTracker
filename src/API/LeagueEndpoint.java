package API;

import API.DTO.LeagueEntryDTO;
import API.DTO.LeagueItemDTO;
import API.DTO.LeagueListDTO;
import lombok.Getter;

import java.net.URL;
import java.util.*;

public class LeagueEndpoint extends Endpoint {

    public LeagueEndpoint(String regionName, String apiKey) {
        super(regionName, apiKey);
    }

    @Override
    String getEndpointName() {
        return "league";
    }

    @Override
    int getVersion() {
        return 4;
    }

    public LeagueListDTO getChallengerLeagueByQueue(LeagueQueueType queue) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("challengerleagues/by-queue") + queue.getId());

        if (requestUrl == null) {
            return null;
        }

        try {
            EndpointRequest<LeagueListDTO> req = this.queueRequest(requestUrl, RequestMethod.GET, LeagueListDTO.class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public LeagueListDTO getGrandmasterLeagueByQueue(LeagueQueueType queue) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("grandmasterleagues/by-queue") + queue.getId());

        if (requestUrl == null) {
            return null;
        }

        try {
            EndpointRequest<LeagueListDTO> req = this.queueRequest(requestUrl, RequestMethod.GET, LeagueListDTO.class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public LeagueListDTO getMasterLeagueByQueue(LeagueQueueType queue) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("masterleagues/by-queue") + queue.getId());

        if (requestUrl == null) {
            return null;
        }

        try {
            EndpointRequest<LeagueListDTO> req = this.queueRequest(requestUrl, RequestMethod.GET, LeagueListDTO.class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public LeagueEntryDTO[] getLeagueEntriesBySummonerId(String summonerId) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("entries/by-summoner") + summonerId);

        if (requestUrl == null) {
            return null;
        }

        try {
            EndpointRequest<LeagueEntryDTO[]> req = this.queueRequest(requestUrl, RequestMethod.GET, LeagueEntryDTO[].class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public LeagueListDTO getLeagueById(String leagueId) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("leagues") + leagueId);

        if (requestUrl == null) {
            return null;
        }

        try {
            EndpointRequest<LeagueListDTO> req = this.queueRequest(requestUrl, RequestMethod.GET, LeagueListDTO.class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public LeagueEntryDTO[] getLeagueEntries(LeagueQueueType queue, LeagueTier tier, int division) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("entries") + queue.getId() + "/" + tier.getId() + "/" + divisionFromNumber(division));

        if (requestUrl == null) {
            return null;
        }

        try {
            EndpointRequest<LeagueEntryDTO[]> req = this.queueRequest(requestUrl, RequestMethod.GET, LeagueEntryDTO[].class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public List<LeagueItemDTO> getTopOfLadderByQueue(LeagueQueueType queue) {
        LeagueListDTO challenger = this.getChallengerLeagueByQueue(queue);
        LeagueListDTO grandmaster = this.getGrandmasterLeagueByQueue(queue);
        LeagueListDTO master = this.getMasterLeagueByQueue(queue);

        List<LeagueItemDTO> ret = new ArrayList<>();

        if (challenger != null) {
            ret.addAll(challenger.getEntries());
        }

        if (grandmaster != null) {
            ret.addAll(grandmaster.getEntries());
        }

        if (master != null) {
            ret.addAll(master.getEntries());
        }

        return ret;
    }

    @Getter
    public enum LeagueQueueType {
        RANKED_SOLO_5X5("RANKED_SOLO_5X5"),
        RANKED_TFT("RANKED_TFT"),
        RANKED_FLEX_SR("RANKED_FLEX_SR"),
        RANKED_FLEX_TT("RANKED_FLEX_TT"),
        UNKNOWN("UNKNOWN");

        private static Map<String, LeagueQueueType> reverseLookup = new HashMap<>();
        private String id;

        LeagueQueueType(String id) {
            this.id = id;
        }

        static {
            for (LeagueQueueType queueType : LeagueQueueType.values()) {
                reverseLookup.put(queueType.id, queueType);
            }
        }

        public static LeagueQueueType fromId(final String id) {
            return reverseLookup.getOrDefault(id, UNKNOWN);
        }
    }

    @Getter
    public enum LeagueTier {
        DIAMOND("DIAMOND"),
        PLATINUM("PLATINUM"),
        GOLD("GOLD"),
        SILVER("SILVER"),
        BRONZE("BRONZE"),
        IRON("IRON"),
        UNRANKED("UNRANKED");

        private static Map<String, LeagueTier> reverseLookup = new HashMap<>();
        private String id;

        LeagueTier(String id) {
            this.id = id;
        }

        static {
            for (LeagueTier tier : LeagueTier.values()) {
                reverseLookup.put(tier.id, tier);
            }
        }

        public static LeagueTier fromId(final String id) {
            return reverseLookup.getOrDefault(id, UNRANKED);
        }
    }

    public static String divisionFromNumber(int num) {
        switch (num) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
        }
        return "IV";
    }
}
