
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lane",
    "gameId",
    "champion",
    "platformId",
    "timestamp",
    "queue",
    "role",
    "season"
})
@Getter
@ToString
public class MatchReferenceDTO {

    @JsonProperty("lane")
    private String lane;
    @JsonProperty("gameId")
    private Long gameId;
    @JsonProperty("champion")
    private Long champion;
    @JsonProperty("platformId")
    private String platformId;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("queue")
    private Long queue;
    @JsonProperty("role")
    private String role;
    @JsonProperty("season")
    private Long season;

}
