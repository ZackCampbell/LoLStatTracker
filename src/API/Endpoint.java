package API;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

abstract class Endpoint {
    private final static String URL_PREFIX = ".api.riotgames.com/lol/";
    private final static String RIOT_TOKEN_VAR_NAME = "X-Riot-Token";
    private final String apiKey;

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

    URL buildUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    <T> T send(URL requestUrl, RequestMethod method, Class<T> expectedResponseClass) throws RateLimitException, IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
        this.setRequestHeaders(method, urlConnection);

        int code = urlConnection.getResponseCode();

        if (code == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = urlConnection.getInputStream();
            return new ObjectMapper().readValue(inputStream, expectedResponseClass);
        } else if (code == HttpURLConnection.HTTP_BAD_REQUEST) {

        } else if (code == 429) {
            // Rate limit exceed, checkout Retry-After in header
            String retryAfter = urlConnection.getHeaderField("Retry-After");
            throw new RateLimitException(Integer.parseInt(retryAfter));
        }

        return null;
    }

    <T> EndpointRequest<T> queueRequest(URL requestUrl, RequestMethod method, Class<T> responseClass) throws InterruptedException {
        EndpointRequest<T> req = new EndpointRequest<>(this, requestUrl, method, responseClass);
        EndpointThrottler.getInstance().queue(req);

        return req;
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
