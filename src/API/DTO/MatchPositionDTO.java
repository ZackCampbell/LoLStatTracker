
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "y",
    "x"
})
public class MatchPositionDTO {

    @JsonProperty("y")
    public Long y;
    @JsonProperty("x")
    public Long x;

}
