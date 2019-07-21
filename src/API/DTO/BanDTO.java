
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pickTurn",
    "championId"
})
public class BanDTO {

    @JsonProperty("pickTurn")
    public Long pickTurn;
    @JsonProperty("championId")
    public Long championId;

}
