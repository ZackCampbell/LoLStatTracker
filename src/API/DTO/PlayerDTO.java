
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "currentPlatformId",
    "summonerName",
    "matchHistoryUri",
    "platformId",
    "currentAccountId",
    "profileIcon",
    "summonerId",
    "accountId"
})
public class PlayerDTO {

    @JsonProperty("currentPlatformId")
    public String currentPlatformId;
    @JsonProperty("summonerName")
    public String summonerName;
    @JsonProperty("matchHistoryUri")
    public String matchHistoryUri;
    @JsonProperty("platformId")
    public String platformId;
    @JsonProperty("currentAccountId")
    public String currentAccountId;
    @JsonProperty("profileIcon")
    public Long profileIcon;
    @JsonProperty("summonerId")
    public String summonerId;
    @JsonProperty("accountId")
    public String accountId;

}
