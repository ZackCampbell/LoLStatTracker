
package API.DTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "eventType",
    "towerType",
    "teamId",
    "ascendedType",
    "killerId",
    "levelUpType",
    "pointCaptured",
    "assistingParticipantIds",
    "wardType",
    "monsterType",
    "type",
    "skillSlot",
    "victimId",
    "timestamp",
    "afterId",
    "monsterSubType",
    "laneType",
    "itemId",
    "participantId",
    "buildingType",
    "creatorId",
    "position",
    "beforeId"
})
@Getter
@ToString
public class MatchEventDTO {

    @JsonProperty("eventType")
    private String eventType;
    @JsonProperty("towerType")
    private String towerType;
    @JsonProperty("teamId")
    private Integer teamId;
    @JsonProperty("ascendedType")
    private String ascendedType;
    @JsonProperty("killerId")
    private Integer killerId;
    @JsonProperty("levelUpType")
    private String levelUpType;
    @JsonProperty("pointCaptured")
    private String pointCaptured;
    @JsonProperty("assistingParticipantIds")
    private List<Integer> assistingParticipantIds = null;
    @JsonProperty("wardType")
    private String wardType;
    @JsonProperty("monsterType")
    private String monsterType;
    @JsonProperty("type")
    private String type;
    @JsonProperty("skillSlot")
    private Integer skillSlot;
    @JsonProperty("victimId")
    private Integer victimId;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("afterId")
    private Integer afterId; // Item id that occurred after undoing
    @JsonProperty("monsterSubType")
    private String monsterSubType;
    @JsonProperty("laneType")
    private String laneType;
    @JsonProperty("itemId")
    private Integer itemId;
    @JsonProperty("participantId")
    private Integer participantId;
    @JsonProperty("buildingType")
    private String buildingType;
    @JsonProperty("creatorId")
    private Integer creatorId;
    @JsonProperty("position")
    private MatchPositionDTO position;
    @JsonProperty("beforeId")
    private Integer beforeId; // Item id prior to undoing (i.e., the item undone)

    @Getter
    public enum MatchEventType {
        ITEM_PURCHASED("ITEM_PURCHASED"),
        ITEM_SOLD("ITEM_SOLD"),
        ITEM_UNDO("ITEM_UNDO"),
        ITEM_DESTROYED("ITEM_DESTROYED"), // Consumed..?,
        SKILL_LEVEL_UP("SKILL_LEVEL_UP"),
        WARD_PLACED("WARD_PLACED"),
        WARD_KILL("WARD_KILL"),
        CHAMPION_KILL("CHAMPION_KILL"),
        ELITE_MONSTER_KILL("ELITE_MONSTER_KILL"),
        BUILDING_KILL("BUILDING_KILL"),
        UNKNOWN("UNKNOWN");

        private static Map<String, MatchEventType> reverseLookup = new HashMap<>();
        private String id;

        MatchEventType(String id) {
            this.id = id;
        }

        static {
            for (MatchEventType eventType : MatchEventType.values()) {
                reverseLookup.put(eventType.id, eventType);
            }
        }

        public static MatchEventType fromId(final String id) {
            return reverseLookup.getOrDefault(id, UNKNOWN);
        }
    }

}
