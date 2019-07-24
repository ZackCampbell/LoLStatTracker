
package API.DTO;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "timestamp",
    "participantFrames",
    "events"
})
@Getter
@ToString
public class MatchFrameDTO {

    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("participantFrames")
    private Map<String, MatchParticipantFrameDTO> participantFrames;
    @JsonProperty("events")
    private List<MatchEventDTO> events = null;

}
