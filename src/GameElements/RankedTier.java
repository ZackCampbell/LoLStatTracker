package GameElements;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum RankedTier {
    CHALLENGER("CHALLENGER", "Challenger"),
    MASTER("MASTER", "Master"),
    DIAMOND("DIAMOND", "Diamond"),
    PLATINUM("PLATINUM", "Platinum"),
    GOLD("GOLD", "Gold"),
    SILVER("SILVER", "Silver"),
    BRONZE("BRONZE", "Bronze"),
    UNRANKED("UNRANKED", "Unranked");

    private static Map<String, RankedTier> reverseLookup = new HashMap<>();
    private final String value;
    private final String prettyValue;

    RankedTier(String value, String prettyValue) {
        this.value = value;
        this.prettyValue = prettyValue;
    }

    static {
        for (RankedTier tier : RankedTier.values()) {
            reverseLookup.put(tier.value, tier);
        }
    }

    public static RankedTier fromValue(final String value) {
        return reverseLookup.getOrDefault(value, UNRANKED);
    }
}
