
package API.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
public class MatchEventDTO {

    @JsonProperty("eventType")
    public String eventType;
    @JsonProperty("towerType")
    public String towerType;
    @JsonProperty("teamId")
    public Long teamId;
    @JsonProperty("ascendedType")
    public String ascendedType;
    @JsonProperty("killerId")
    public Long killerId;
    @JsonProperty("levelUpType")
    public String levelUpType;
    @JsonProperty("pointCaptured")
    public String pointCaptured;
    @JsonProperty("assistingParticipantIds")
    public List<Long> assistingParticipantIds = null;
    @JsonProperty("wardType")
    public String wardType;
    @JsonProperty("monsterType")
    public String monsterType;
    @JsonProperty("type")
    public String type;
    @JsonProperty("skillSlot")
    public Integer skillSlot;
    @JsonProperty("victimId")
    public Long victimId;
    @JsonProperty("timestamp")
    public Long timestamp;
    @JsonProperty("afterId")
    public Long afterId;
    @JsonProperty("monsterSubType")
    public String monsterSubType;
    @JsonProperty("laneType")
    public String laneType;
    @JsonProperty("itemId")
    public Long itemId;
    @JsonProperty("participantId")
    public Long participantId;
    @JsonProperty("buildingType")
    public String buildingType;
    @JsonProperty("creatorId")
    public Long creatorId;
    @JsonProperty("position")
    public MatchPositionDTO position;
    @JsonProperty("beforeId")
    public Long beforeId;

}
