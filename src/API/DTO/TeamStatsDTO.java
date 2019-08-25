
package API.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

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
@Getter
@ToString
public class TeamStatsDTO {

    @JsonProperty("firstDragon")
    private Boolean firstDragon;
    @JsonProperty("bans")
    private List<BanDTO> bans = null;
    @JsonProperty("firstInhibitor")
    private Boolean firstInhibitor;
    @JsonProperty("win")
    private String win;
    @JsonProperty("firstRiftHerald")
    private Boolean firstRiftHerald;
    @JsonProperty("firstBaron")
    private Boolean firstBaron;
    @JsonProperty("baronKills")
    private Integer baronKills;
    @JsonProperty("riftHeraldKills")
    private Integer riftHeraldKills;
    @JsonProperty("firstBlood")
    private Boolean firstBlood;
    @JsonProperty("teamId")
    private Integer teamId;
    @JsonProperty("firstTower")
    private Boolean firstTower;
    @JsonProperty("vilemawKills")
    private Integer vilemawKills;
    @JsonProperty("inhibitorKills")
    private Integer inhibitorKills;
    @JsonProperty("towerKills")
    private Integer towerKills;
    @JsonProperty("dominionVictoryScore")
    private Integer dominionVictoryScore;
    @JsonProperty("dragonKills")
    private Integer dragonKills;

}
