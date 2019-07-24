
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "totalGold",
    "teamScore",
    "participantId",
    "level",
    "currentGold",
    "minionsKilled",
    "dominionScore",
    "position",
    "xp",
    "jungleMinionsKilled"
})
@Getter
@ToString
public class MatchParticipantFrameDTO {

    @JsonProperty("totalGold")
    private Long totalGold;
    @JsonProperty("teamScore")
    private Long teamScore;
    @JsonProperty("participantId")
    private Long participantId;
    @JsonProperty("level")
    private Long level;
    @JsonProperty("currentGold")
    private Long currentGold;
    @JsonProperty("minionsKilled")
    private Long minionsKilled;
    @JsonProperty("dominionScore")
    private Long dominionScore;
    @JsonProperty("position")
    private MatchPositionDTO position;
    @JsonProperty("xp")
    private Long xp;
    @JsonProperty("jungleMinionsKilled")
    private Long jungleMinionsKilled;

}
