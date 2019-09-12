package GameElements.Events;

import API.DTO.MatchEventDTO;
import GameElements.Match;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class WardEvent extends MatchEvent {

    WardEventType type;
    WardType wardType;

    public WardEvent(WardEventType type, MatchEventDTO dto) {
        super(dto.getTimestamp(),
              type == WardEventType.PLACED ? dto.getCreatorId() : dto.getKillerId(),
              new ArrayList<>());

        /// Ward event specific properties
        this.type = type;
        this.wardType = WardType.fromId(dto.getWardType());
    }

    @Getter
    public enum WardEventType {
        PLACED("PLACED"),
        KILLED("KILLED"),
        UNKNOWN("UNKNOWN");

        private static Map<String, WardEventType> reverseLookup = new HashMap<>();
        private String id;

        WardEventType(String id) {
            this.id = id;
        }

        static {
            for (WardEventType type : WardEventType.values()) {
                reverseLookup.put(type.id, type);
            }
        }

        public static WardEventType fromId(final String id) {
            return reverseLookup.getOrDefault(id, UNKNOWN);
        }
    }

    @Getter
    public enum WardType {
        CONTROL_WARD("CONTROL_WARD"),
        SIGHT_WARD("SIGHT_WARD"),
        YELLOW_TRINKET("YELLOW_TRINKET"),
        UNDEFINED("UNDEFINED");
        // TODO: What's scrying orb?

        private static Map<String, WardType> reverseLookup = new HashMap<>();
        private String id;

        WardType(String id) {
            this.id = id;
        }

        static {
            for (WardType type : WardType.values()) {
                reverseLookup.put(type.id, type);
            }
        }

        public static WardType fromId(final String id) {
            return reverseLookup.getOrDefault(id, UNDEFINED);
        }
    }
}
