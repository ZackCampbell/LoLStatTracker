
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

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
@Getter
@ToString
public class PlayerDTO {

    @JsonProperty("currentPlatformId")
    private String currentPlatformId;
    @JsonProperty("summonerName")
    private String summonerName;
    @JsonProperty("matchHistoryUri")
    private String matchHistoryUri;
    @JsonProperty("platformId")
    private String platformId;
    @JsonProperty("currentAccountId")
    private String currentAccountId;
    @JsonProperty("profileIcon")
    private Long profileIcon;
    @JsonProperty("summonerId")
    private String summonerId;
    @JsonProperty("accountId")
    private String accountId;

}
