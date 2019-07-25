
package API.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "matches",
    "endIndex",
    "startIndex",
    "totalGames"
})
@Getter
@ToString
public class MatchListDTO {

    @JsonProperty("matches")
    private List<MatchReferenceDTO> matches = null;
    @JsonProperty("endIndex")
    private Long endIndex;
    @JsonProperty("startIndex")
    private Long startIndex;
    @JsonProperty("totalGames")
    private Long totalGames;

}
