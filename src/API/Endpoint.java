package API;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

abstract class Endpoint {
    private final static String URL_PREFIX = ".api.riotgames.com/lol/";
    private final static String RIOT_TOKEN_VAR_NAME = "X-Riot-Token";
    private final String apiKey;

    // TODO: Make this an enum
    private final String regionName;

    Endpoint(String regionName, String apiKey) {
        this.regionName = regionName;
        this.apiKey = apiKey;
    }

    /**
     * @return The name for the endpoint. This equates to the specific endpoint's
     * main functionality, i.e "match", and is part of the url construction
     */
    abstract String getEndpointName();

    /**
     * @return The version of this endpoint, encoded as "v#" in the base URL
     */
    abstract int getVersion();

    // Example: /lol/match/v4/matchlists/by-account/
    String getBaseUrl(String subEndpointName) {
        return "https://" + this.regionName + URL_PREFIX + getEndpointName() + "/v" + getVersion() + "/" + subEndpointName + '/';
    }

    <T> T send(URL requestUrl, RequestMethod method, Class<T> expectedResponseClass) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
        this.setRequestHeaders(method, urlConnection);
        InputStream inputStream = urlConnection.getInputStream();

        return new ObjectMapper().readValue(inputStream, expectedResponseClass);
    }

    private void setRequestHeaders(RequestMethod method, HttpURLConnection urlConnection) throws ProtocolException {
        urlConnection.setRequestMethod(method.value());
        urlConnection.setRequestProperty(Endpoint.RIOT_TOKEN_VAR_NAME, this.apiKey);
    }

    // TODO: Consider breaking out into a separate class
    public enum RequestMethod {
        GET("GET");

        private final String value;

        RequestMethod(String value) {
            this.value = value;
        }

        String value() {
            return this.value;
        }
    }
}
