
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hp",
    "hpperlevel",
    "mp",
    "mpperlevel",
    "movespeed",
    "armor",
    "armorperlevel",
    "spellblock",
    "spellblockperlevel",
    "attackrange",
    "hpregen",
    "hpregenperlevel",
    "mpregen",
    "mpregenperlevel",
    "crit",
    "critperlevel",
    "attackdamage",
    "attackdamageperlevel",
    "attackspeedperlevel",
    "attackspeed"
})
@Getter
@ToString
public class ChampionStatsDTO {

    @JsonProperty("hp")
    private Long hp;
    @JsonProperty("hpperlevel")
    private Long hpperlevel;
    @JsonProperty("mp")
    private Long mp;
    @JsonProperty("mpperlevel")
    private Long mpperlevel;
    @JsonProperty("movespeed")
    private Long movespeed;
    @JsonProperty("armor")
    private Long armor;
    @JsonProperty("armorperlevel")
    private Double armorperlevel;
    @JsonProperty("spellblock")
    private Double spellblock;
    @JsonProperty("spellblockperlevel")
    private Double spellblockperlevel;
    @JsonProperty("attackrange")
    private Long attackrange;
    @JsonProperty("hpregen")
    private Long hpregen;
    @JsonProperty("hpregenperlevel")
    private Long hpregenperlevel;
    @JsonProperty("mpregen")
    private Long mpregen;
    @JsonProperty("mpregenperlevel")
    private Long mpregenperlevel;
    @JsonProperty("crit")
    private Long crit;
    @JsonProperty("critperlevel")
    private Long critperlevel;
    @JsonProperty("attackdamage")
    private Long attackdamage;
    @JsonProperty("attackdamageperlevel")
    private Long attackdamageperlevel;
    @JsonProperty("attackspeedperlevel")
    private Double attackspeedperlevel;
    @JsonProperty("attackspeed")
    private Double attackspeed;

}
