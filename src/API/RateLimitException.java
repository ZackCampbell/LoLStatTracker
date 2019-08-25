package API;

public class RateLimitException extends Exception {
    final int retryAfterXSeconds;

    public RateLimitException(int retryAfterXSeconds) {
        this.retryAfterXSeconds = retryAfterXSeconds;
    }
}
