
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lane",
    "participantId",
    "csDiffPerMinDeltas",
    "goldPerMinDeltas",
    "xpDiffPerMinDeltas",
    "creepsPerMinDeltas",
    "xpPerMinDeltas",
    "role",
    "damageTakenDiffPerMinDeltas",
    "damageTakenPerMinDeltas"
})
public class TimelineDTO {

    @JsonProperty("lane")
    public String lane;
    @JsonProperty("participantId")
    public Long participantId;
    @JsonProperty("csDiffPerMinDeltas")
    public StatDeltasDTO csDiffPerMinDeltas;
    @JsonProperty("goldPerMinDeltas")
    public StatDeltasDTO goldPerMinDeltas;
    @JsonProperty("xpDiffPerMinDeltas")
    public StatDeltasDTO xpDiffPerMinDeltas;
    @JsonProperty("creepsPerMinDeltas")
    public StatDeltasDTO creepsPerMinDeltas;
    @JsonProperty("xpPerMinDeltas")
    public StatDeltasDTO xpPerMinDeltas;
    @JsonProperty("role")
    public String role;
    @JsonProperty("damageTakenDiffPerMinDeltas")
    public StatDeltasDTO damageTakenDiffPerMinDeltas;
    @JsonProperty("damageTakenPerMinDeltas")
    public StatDeltasDTO damageTakenPerMinDeltas;

}
