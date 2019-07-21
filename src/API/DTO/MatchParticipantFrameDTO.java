
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
public class MatchParticipantFrameDTO {

    @JsonProperty("totalGold")
    public Long totalGold;
    @JsonProperty("teamScore")
    public Long teamScore;
    @JsonProperty("participantId")
    public Long participantId;
    @JsonProperty("level")
    public Long level;
    @JsonProperty("currentGold")
    public Long currentGold;
    @JsonProperty("minionsKilled")
    public Long minionsKilled;
    @JsonProperty("dominionScore")
    public Long dominionScore;
    @JsonProperty("position")
    public MatchPositionDTO position;
    @JsonProperty("xp")
    public Long xp;
    @JsonProperty("jungleMinionsKilled")
    public Long jungleMinionsKilled;

}
