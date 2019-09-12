
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dev.morphia.annotations.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@Getter
@ToString
@Embedded
@NoArgsConstructor
public class TimelineDTO {

    @JsonProperty("lane")
    private String lane;
    @JsonProperty("participantId")
    private Long participantId;
    @JsonProperty("csDiffPerMinDeltas")
    private StatDeltasDTO csDiffPerMinDeltas;
    @JsonProperty("goldPerMinDeltas")
    private StatDeltasDTO goldPerMinDeltas;
    @JsonProperty("xpDiffPerMinDeltas")
    private StatDeltasDTO xpDiffPerMinDeltas;
    @JsonProperty("creepsPerMinDeltas")
    private StatDeltasDTO creepsPerMinDeltas;
    @JsonProperty("xpPerMinDeltas")
    private StatDeltasDTO xpPerMinDeltas;
    @JsonProperty("role")
    private String role;
    @JsonProperty("damageTakenDiffPerMinDeltas")
    private StatDeltasDTO damageTakenDiffPerMinDeltas;
    @JsonProperty("damageTakenPerMinDeltas")
    private StatDeltasDTO damageTakenPerMinDeltas;

}
