
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "y",
    "x"
})
@Getter
@ToString
public class MatchPositionDTO {

    @JsonProperty("y")
    private Long y;
    @JsonProperty("x")
    private Long x;

}
