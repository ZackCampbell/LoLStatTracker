package API;

import GameElements.Summoner;

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

    public Summoner getSummonerByEncryptedId(String encryptedId) throws IOException {
        URL requestUrl = new URL(this.getBaseUrl("summoners/by-account") + encryptedId);

        return this.send(requestUrl, RequestMethod.GET, Summoner.class);
    }

    public Summoner getSummonerByName(String name) throws IOException {
        URL requestUrl = new URL(this.getBaseUrl("summoners/by-name") + name);

        return this.send(requestUrl, RequestMethod.GET, Summoner.class);
    }

    public Summoner getSummonerByPuuid(String puuid) throws IOException {
        URL requestUrl = new URL(this.getBaseUrl("summoners/by-puuid") + puuid);

        return this.send(requestUrl, RequestMethod.GET, Summoner.class);
    }

    public Summoner getSummonerBySummonerId(String summonerId) throws IOException {
        URL requestUrl = new URL(this.getBaseUrl("summoners") + summonerId);

        return this.send(requestUrl, RequestMethod.GET, Summoner.class);
    }
}
