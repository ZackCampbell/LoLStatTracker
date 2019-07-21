
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
public class MatchReferenceDTO {

    @JsonProperty("lane")
    public String lane;
    @JsonProperty("gameId")
    public Long gameId;
    @JsonProperty("champion")
    public Long champion;
    @JsonProperty("platformId")
    public String platformId;
    @JsonProperty("timestamp")
    public Long timestamp;
    @JsonProperty("queue")
    public Long queue;
    @JsonProperty("role")
    public String role;
    @JsonProperty("season")
    public Long season;

}
