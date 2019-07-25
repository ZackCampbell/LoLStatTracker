package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

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
@Getter
@ToString
public class SummonerDTO {

    @JsonProperty("profileIconId")
    private Long profileIconId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("puuid")
    private String puuid;
    @JsonProperty("summonerLevel")
    private Long summonerLevel;
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("revisionDate")
    private Long revisionDate;

}