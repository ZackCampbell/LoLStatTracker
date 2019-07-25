
package API.DTO;

import java.util.List;
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
    private Long teamId;
    @JsonProperty("ascendedType")
    private String ascendedType;
    @JsonProperty("killerId")
    private Long killerId;
    @JsonProperty("levelUpType")
    private String levelUpType;
    @JsonProperty("pointCaptured")
    private String pointCaptured;
    @JsonProperty("assistingParticipantIds")
    private List<Long> assistingParticipantIds = null;
    @JsonProperty("wardType")
    private String wardType;
    @JsonProperty("monsterType")
    private String monsterType;
    @JsonProperty("type")
    private String type;
    @JsonProperty("skillSlot")
    private Integer skillSlot;
    @JsonProperty("victimId")
    private Long victimId;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("afterId")
    private Long afterId;
    @JsonProperty("monsterSubType")
    private String monsterSubType;
    @JsonProperty("laneType")
    private String laneType;
    @JsonProperty("itemId")
    private Long itemId;
    @JsonProperty("participantId")
    private Long participantId;
    @JsonProperty("buildingType")
    private String buildingType;
    @JsonProperty("creatorId")
    private Long creatorId;
    @JsonProperty("position")
    private MatchPositionDTO position;
    @JsonProperty("beforeId")
    private Long beforeId;

}
