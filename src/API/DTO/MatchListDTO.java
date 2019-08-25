
package API.DTO;

import java.util.List;
import java.util.stream.Collectors;

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
    private Integer endIndex;
    @JsonProperty("startIndex")
    private Integer startIndex;
    @JsonProperty("totalGames")
    private Integer totalGames;

    public List<Long> getGameIds() {
        return this.matches
                .stream()
                .map(MatchReferenceDTO::getGameId)
                .collect(Collectors.toList());
    }

}
