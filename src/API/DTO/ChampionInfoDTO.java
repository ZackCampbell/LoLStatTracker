
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "attack",
    "defense",
    "magic",
    "difficulty"
})
@Getter
@ToString
public class ChampionInfoDTO {

    @JsonProperty("attack")
    private Long attack;
    @JsonProperty("defense")
    private Long defense;
    @JsonProperty("magic")
    private Long magic;
    @JsonProperty("difficulty")
    private Long difficulty;

}
