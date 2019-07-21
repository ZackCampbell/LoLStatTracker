
package API.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "matches",
    "endIndex",
    "startIndex",
    "totalGames"
})
public class MatchListDTO {

    @JsonProperty("matches")
    public List<MatchReferenceDTO> matches = null;
    @JsonProperty("endIndex")
    public Long endIndex;
    @JsonProperty("startIndex")
    public Long startIndex;
    @JsonProperty("totalGames")
    public Long totalGames;

}
