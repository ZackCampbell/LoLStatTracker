
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "player",
    "participantId"
})
public class ParticipantIdentityDTO {

    @JsonProperty("player")
    public PlayerDTO player;
    @JsonProperty("participantId")
    public Long participantId;

}
