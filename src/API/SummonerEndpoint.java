package API;

import API.DTO.SummonerDTO;

import java.io.IOException;
import java.net.URL;

public class SummonerEndpoint extends Endpoint {

    public SummonerEndpoint(String regionName, String apiKey) {
        super(regionName, apiKey);
    }

    @Override
    String getEndpointName() {
        return "summoner";
    }

    @Override
    int getVersion() {
        return 4;
    }

    public SummonerDTO getSummonerByEncryptedId(String encryptedId) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("summoners/by-account") + encryptedId);

        if (requestUrl == null) {
            return null;
        }

        return this.send(requestUrl, RequestMethod.GET, SummonerDTO.class);
    }

    public SummonerDTO getSummonerByName(String name) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("summoners/by-name") + name);

        if (requestUrl == null) {
            return null;
        }

        return this.send(requestUrl, RequestMethod.GET, SummonerDTO.class);
    }

    public SummonerDTO getSummonerByPuuid(String puuid) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("summoners/by-puuid") + puuid);

        if (requestUrl == null) {
            return null;
        }

        return this.send(requestUrl, RequestMethod.GET, SummonerDTO.class);
    }

    public SummonerDTO getSummonerBySummonerId(String summonerId) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("summoners") + summonerId);

        if (requestUrl == null) {
            return null;
        }

        return this.send(requestUrl, RequestMethod.GET, SummonerDTO.class);
    }
}
