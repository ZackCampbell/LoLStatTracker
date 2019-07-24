
package API.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "frames",
    "frameInterval"
})
@Getter
@ToString
public class MatchTimelineDTO {

    @JsonProperty("frames")
    private List<MatchFrameDTO> frames = null;
    @JsonProperty("frameInterval")
    private Long frameInterval;

}
