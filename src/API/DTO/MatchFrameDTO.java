
package API.DTO;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "timestamp",
    "participantFrames",
    "events"
})
public class MatchFrameDTO {

    @JsonProperty("timestamp")
    public Long timestamp;
    @JsonProperty("participantFrames")
    public Map<String, MatchParticipantFrameDTO> participantFrames;
    @JsonProperty("events")
    public List<MatchEventDTO> events = null;

}
