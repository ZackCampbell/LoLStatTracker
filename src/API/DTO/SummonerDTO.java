package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "profileIconId",
        "name",
        "puuid",
        "summonerLevel",
        "accountId",
        "id",
        "revisionDate"
})
public class SummonerDTO {

    @JsonProperty("profileIconId")
    public Long profileIconId;
    @JsonProperty("name")
    public String name;
    @JsonProperty("puuid")
    public String puuid;
    @JsonProperty("summonerLevel")
    public Long summonerLevel;
    @JsonProperty("accountId")
    public String accountId;
    @JsonProperty("id")
    public String id;
    @JsonProperty("revisionDate")
    public Long revisionDate;

}