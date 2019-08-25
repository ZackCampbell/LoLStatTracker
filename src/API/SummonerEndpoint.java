package API;

import API.DTO.SummonerDTO;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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

        try {
            EndpointRequest<SummonerDTO> req = this.queueRequest(requestUrl, RequestMethod.GET, SummonerDTO.class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public SummonerDTO getSummonerByName(String name) {
        try {
            name = URLEncoder.encode(name, StandardCharsets.UTF_8.toString()).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            // TODO Log
            return null;
        }

        URL requestUrl = this.buildUrl(this.getBaseUrl("summoners/by-name") + name);

        if (requestUrl == null) {
            return null;
        }

        try {
            EndpointRequest<SummonerDTO> req = this.queueRequest(requestUrl, RequestMethod.GET, SummonerDTO.class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public SummonerDTO getSummonerByPuuid(String puuid) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("summoners/by-puuid") + puuid);

        if (requestUrl == null) {
            return null;
        }

        try {
            EndpointRequest<SummonerDTO> req = this.queueRequest(requestUrl, RequestMethod.GET, SummonerDTO.class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public SummonerDTO getSummonerBySummonerId(String summonerId) {
        URL requestUrl = this.buildUrl(this.getBaseUrl("summoners") + summonerId);

        if (requestUrl == null) {
            return null;
        }

        try {
            EndpointRequest<SummonerDTO> req = this.queueRequest(requestUrl, RequestMethod.GET, SummonerDTO.class);
            return req.getResponse();
        } catch (InterruptedException e) {
            return null;
        }
    }
}
