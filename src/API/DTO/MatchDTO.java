
package API.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
public class MatchDTO {

    @JsonProperty("seasonId")
    public Long seasonId;
    @JsonProperty("queueId")
    public Long queueId;
    @JsonProperty("gameId")
    public Long gameId;
    @JsonProperty("participantIdentities")
    public List<ParticipantIdentityDTO> participantIdentities = null;
    @JsonProperty("gameVersion")
    public String gameVersion;
    @JsonProperty("platformId")
    public String platformId;
    @JsonProperty("gameMode")
    public String gameMode;
    @JsonProperty("mapId")
    public Long mapId;
    @JsonProperty("gameType")
    public String gameType;
    @JsonProperty("teams")
    public List<TeamStatsDTO> teams = null;
    @JsonProperty("participants")
    public List<ParticipantDTO> participants = null;
    @JsonProperty("gameDuration")
    public Long gameDuration;
    @JsonProperty("gameCreation")
    public Long gameCreation;

}
