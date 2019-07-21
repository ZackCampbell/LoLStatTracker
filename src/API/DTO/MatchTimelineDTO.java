
package API.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "frames",
    "frameInterval"
})
public class MatchTimelineDTO {

    @JsonProperty("frames")
    public List<MatchFrameDTO> frames = null;
    @JsonProperty("frameInterval")
    public Long frameInterval;

}
