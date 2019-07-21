
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "spell1Id",
    "participantId",
    "timeline",
    "spell2Id",
    "teamId",
    "stats",
    "championId",
    "highestAchievedSeasonTier"
})
public class ParticipantDTO {

    @JsonProperty("spell1Id")
    public Long spell1Id;
    @JsonProperty("participantId")
    public Long participantId;
    @JsonProperty("timeline")
    public TimelineDTO timeline;
    @JsonProperty("spell2Id")
    public Long spell2Id;
    @JsonProperty("teamId")
    public Long teamId;
    @JsonProperty("stats")
    public StatsDTO stats;
    @JsonProperty("championId")
    public Long championId;
    @JsonProperty("highestAchievedSeasonTier")
    public String highestAchievedSeasonTier;

}
