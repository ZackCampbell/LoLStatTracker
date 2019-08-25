
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "player",
    "participantId"
})
@Getter
@ToString
public class ParticipantIdentityDTO {

    @JsonProperty("player")
    private PlayerDTO player;
    @JsonProperty("participantId")
    private Integer participantId;

}
