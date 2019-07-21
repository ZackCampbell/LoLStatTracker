
package API.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "firstDragon",
    "bans",
    "firstInhibitor",
    "win",
    "firstRiftHerald",
    "firstBaron",
    "baronKills",
    "riftHeraldKills",
    "firstBlood",
    "teamId",
    "firstTower",
    "vilemawKills",
    "inhibitorKills",
    "towerKills",
    "dominionVictoryScore",
    "dragonKills"
})
public class TeamStatsDTO {

    @JsonProperty("firstDragon")
    public Boolean firstDragon;
    @JsonProperty("bans")
    public List<BanDTO> bans = null;
    @JsonProperty("firstInhibitor")
    public Boolean firstInhibitor;
    @JsonProperty("win")
    public String win;
    @JsonProperty("firstRiftHerald")
    public Boolean firstRiftHerald;
    @JsonProperty("firstBaron")
    public Boolean firstBaron;
    @JsonProperty("baronKills")
    public Long baronKills;
    @JsonProperty("riftHeraldKills")
    public Long riftHeraldKills;
    @JsonProperty("firstBlood")
    public Boolean firstBlood;
    @JsonProperty("teamId")
    public Long teamId;
    @JsonProperty("firstTower")
    public Boolean firstTower;
    @JsonProperty("vilemawKills")
    public Long vilemawKills;
    @JsonProperty("inhibitorKills")
    public Long inhibitorKills;
    @JsonProperty("towerKills")
    public Long towerKills;
    @JsonProperty("dominionVictoryScore")
    public Long dominionVictoryScore;
    @JsonProperty("dragonKills")
    public Long dragonKills;

}
