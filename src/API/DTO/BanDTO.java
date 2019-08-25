
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pickTurn",
    "championId"
})
@Getter
@ToString
public class BanDTO {

    @JsonProperty("pickTurn")
    private Integer pickTurn;
    @JsonProperty("championId")
    private Integer championId;

}
