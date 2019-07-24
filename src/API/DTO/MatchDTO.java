
package API.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "seasonId",
    "queueId",
    "gameId",
    "participantIdentities",
    "gameVersion",
    "platformId",
    "gameMode",
    "mapId",
    "gameType",
    "teams",
    "participants",
    "gameDuration",
    "gameCreation"
})
@Getter
@ToString
public class MatchDTO {

    @JsonProperty("seasonId")
    private Long seasonId;
    @JsonProperty("queueId")
    private Long queueId;
    @JsonProperty("gameId")
    private Long gameId;
    @JsonProperty("participantIdentities")
    private List<ParticipantIdentityDTO> participantIdentities = null;
    @JsonProperty("gameVersion")
    private String gameVersion;
    @JsonProperty("platformId")
    private String platformId;
    @JsonProperty("gameMode")
    private String gameMode;
    @JsonProperty("mapId")
    private Long mapId;
    @JsonProperty("gameType")
    private String gameType;
    @JsonProperty("teams")
    private List<TeamStatsDTO> teams = null;
    @JsonProperty("participants")
    private List<ParticipantDTO> participants = null;
    @JsonProperty("gameDuration")
    private Long gameDuration;
    @JsonProperty("gameCreation")
    private Long gameCreation;

}
