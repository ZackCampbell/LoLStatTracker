
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "neutralMinionsKilledTeamJungle",
    "visionScore",
    "magicDamageDealtToChampions",
    "largestMultiKill",
    "totalTimeCrowdControlDealt",
    "longestTimeSpentLiving",
    "perk1Var1",
    "perk1Var3",
    "perk1Var2",
    "tripleKills",
    "perk5",
    "perk4",
    "playerScore9",
    "playerScore8",
    "kills",
    "playerScore1",
    "playerScore0",
    "playerScore3",
    "playerScore2",
    "playerScore5",
    "playerScore4",
    "playerScore7",
    "playerScore6",
    "perk5Var1",
    "perk5Var3",
    "perk5Var2",
    "totalScoreRank",
    "neutralMinionsKilled",
    "statPerk1",
    "statPerk0",
    "damageDealtToTurrets",
    "physicalDamageDealtToChampions",
    "damageDealtToObjectives",
    "perk2Var2",
    "perk2Var3",
    "totalUnitsHealed",
    "perk2Var1",
    "perk4Var1",
    "totalDamageTaken",
    "perk4Var3",
    "wardsKilled",
    "largestCriticalStrike",
    "largestKillingSpree",
    "quadraKills",
    "magicDamageDealt",
    "firstBloodAssist",
    "item2",
    "item3",
    "item0",
    "item1",
    "item6",
    "item4",
    "item5",
    "perk1",
    "perk0",
    "perk3",
    "perk2",
    "perk3Var3",
    "perk3Var2",
    "perk3Var1",
    "damageSelfMitigated",
    "magicalDamageTaken",
    "perk0Var2",
    "firstInhibitorKill",
    "trueDamageTaken",
    "assists",
    "perk4Var2",
    "goldSpent",
    "trueDamageDealt",
    "participantId",
    "physicalDamageDealt",
    "sightWardsBoughtInGame",
    "totalDamageDealtToChampions",
    "physicalDamageTaken",
    "totalPlayerScore",
    "win",
    "objectivePlayerScore",
    "totalDamageDealt",
    "neutralMinionsKilledEnemyJungle",
    "deaths",
    "wardsPlaced",
    "perkPrimaryStyle",
    "perkSubStyle",
    "turretKills",
    "firstBloodKill",
    "trueDamageDealtToChampions",
    "goldEarned",
    "killingSprees",
    "unrealKills",
    "firstTowerAssist",
    "firstTowerKill",
    "champLevel",
    "doubleKills",
    "inhibitorKills",
    "firstInhibitorAssist",
    "perk0Var1",
    "combatPlayerScore",
    "perk0Var3",
    "visionWardsBoughtInGame",
    "pentaKills",
    "totalHeal",
    "totalMinionsKilled",
    "timeCCingOthers",
    "statPerk2"
})
@Getter
@ToString
public class StatsDTO {

    @JsonProperty("neutralMinionsKilledTeamJungle")
    private Long neutralMinionsKilledTeamJungle;
    @JsonProperty("visionScore")
    private Long visionScore;
    @JsonProperty("magicDamageDealtToChampions")
    private Long magicDamageDealtToChampions;
    @JsonProperty("largestMultiKill")
    private Long largestMultiKill;
    @JsonProperty("totalTimeCrowdControlDealt")
    private Long totalTimeCrowdControlDealt;
    @JsonProperty("longestTimeSpentLiving")
    private Long longestTimeSpentLiving;
    @JsonProperty("perk1Var1")
    private Long perk1Var1;
    @JsonProperty("perk1Var3")
    private Long perk1Var3;
    @JsonProperty("perk1Var2")
    private Long perk1Var2;
    @JsonProperty("tripleKills")
    private Long tripleKills;
    @JsonProperty("perk5")
    private Long perk5;
    @JsonProperty("perk4")
    private Long perk4;
    @JsonProperty("playerScore9")
    private Long playerScore9;
    @JsonProperty("playerScore8")
    private Long playerScore8;
    @JsonProperty("kills")
    private Long kills;
    @JsonProperty("playerScore1")
    private Long playerScore1;
    @JsonProperty("playerScore0")
    private Long playerScore0;
    @JsonProperty("playerScore3")
    private Long playerScore3;
    @JsonProperty("playerScore2")
    private Long playerScore2;
    @JsonProperty("playerScore5")
    private Long playerScore5;
    @JsonProperty("playerScore4")
    private Long playerScore4;
    @JsonProperty("playerScore7")
    private Long playerScore7;
    @JsonProperty("playerScore6")
    private Long playerScore6;
    @JsonProperty("perk5Var1")
    private Long perk5Var1;
    @JsonProperty("perk5Var3")
    private Long perk5Var3;
    @JsonProperty("perk5Var2")
    private Long perk5Var2;
    @JsonProperty("totalScoreRank")
    private Long totalScoreRank;
    @JsonProperty("neutralMinionsKilled")
    private Long neutralMinionsKilled;
    @JsonProperty("statPerk1")
    private Long statPerk1;
    @JsonProperty("statPerk0")
    private Long statPerk0;
    @JsonProperty("damageDealtToTurrets")
    private Long damageDealtToTurrets;
    @JsonProperty("physicalDamageDealtToChampions")
    private Long physicalDamageDealtToChampions;
    @JsonProperty("damageDealtToObjectives")
    private Long damageDealtToObjectives;
    @JsonProperty("perk2Var2")
    private Long perk2Var2;
    @JsonProperty("perk2Var3")
    private Long perk2Var3;
    @JsonProperty("totalUnitsHealed")
    private Long totalUnitsHealed;
    @JsonProperty("perk2Var1")
    private Long perk2Var1;
    @JsonProperty("perk4Var1")
    private Long perk4Var1;
    @JsonProperty("totalDamageTaken")
    private Long totalDamageTaken;
    @JsonProperty("perk4Var3")
    private Long perk4Var3;
    @JsonProperty("wardsKilled")
    private Long wardsKilled;
    @JsonProperty("largestCriticalStrike")
    private Long largestCriticalStrike;
    @JsonProperty("largestKillingSpree")
    private Long largestKillingSpree;
    @JsonProperty("quadraKills")
    private Long quadraKills;
    @JsonProperty("magicDamageDealt")
    private Long magicDamageDealt;
    @JsonProperty("firstBloodAssist")
    private Boolean firstBloodAssist;
    @JsonProperty("item2")
    private Long item2;
    @JsonProperty("item3")
    private Long item3;
    @JsonProperty("item0")
    private Long item0;
    @JsonProperty("item1")
    private Long item1;
    @JsonProperty("item6")
    private Long item6;
    @JsonProperty("item4")
    private Long item4;
    @JsonProperty("item5")
    private Long item5;
    @JsonProperty("perk1")
    private Long perk1;
    @JsonProperty("perk0")
    private Long perk0;
    @JsonProperty("perk3")
    private Long perk3;
    @JsonProperty("perk2")
    private Long perk2;
    @JsonProperty("perk3Var3")
    private Long perk3Var3;
    @JsonProperty("perk3Var2")
    private Long perk3Var2;
    @JsonProperty("perk3Var1")
    private Long perk3Var1;
    @JsonProperty("damageSelfMitigated")
    private Long damageSelfMitigated;
    @JsonProperty("magicalDamageTaken")
    private Long magicalDamageTaken;
    @JsonProperty("perk0Var2")
    private Long perk0Var2;
    @JsonProperty("firstInhibitorKill")
    private Boolean firstInhibitorKill;
    @JsonProperty("trueDamageTaken")
    private Long trueDamageTaken;
    @JsonProperty("assists")
    private Long assists;
    @JsonProperty("perk4Var2")
    private Long perk4Var2;
    @JsonProperty("goldSpent")
    private Long goldSpent;
    @JsonProperty("trueDamageDealt")
    private Long trueDamageDealt;
    @JsonProperty("participantId")
    private Long participantId;
    @JsonProperty("physicalDamageDealt")
    private Long physicalDamageDealt;
    @JsonProperty("sightWardsBoughtInGame")
    private Long sightWardsBoughtInGame;
    @JsonProperty("totalDamageDealtToChampions")
    private Long totalDamageDealtToChampions;
    @JsonProperty("physicalDamageTaken")
    private Long physicalDamageTaken;
    @JsonProperty("totalPlayerScore")
    private Long totalPlayerScore;
    @JsonProperty("win")
    private Boolean win;
    @JsonProperty("objectivePlayerScore")
    private Long objectivePlayerScore;
    @JsonProperty("totalDamageDealt")
    private Long totalDamageDealt;
    @JsonProperty("neutralMinionsKilledEnemyJungle")
    private Long neutralMinionsKilledEnemyJungle;
    @JsonProperty("deaths")
    private Long deaths;
    @JsonProperty("wardsPlaced")
    private Long wardsPlaced;
    @JsonProperty("perkPrimaryStyle")
    private Long perkPrimaryStyle;
    @JsonProperty("perkSubStyle")
    private Long perkSubStyle;
    @JsonProperty("turretKills")
    private Long turretKills;
    @JsonProperty("firstBloodKill")
    private Boolean firstBloodKill;
    @JsonProperty("trueDamageDealtToChampions")
    private Long trueDamageDealtToChampions;
    @JsonProperty("goldEarned")
    private Long goldEarned;
    @JsonProperty("killingSprees")
    private Long killingSprees;
    @JsonProperty("unrealKills")
    private Long unrealKills;
    @JsonProperty("firstTowerAssist")
    private Boolean firstTowerAssist;
    @JsonProperty("firstTowerKill")
    private Boolean firstTowerKill;
    @JsonProperty("champLevel")
    private Long champLevel;
    @JsonProperty("doubleKills")
    private Long doubleKills;
    @JsonProperty("inhibitorKills")
    private Long inhibitorKills;
    @JsonProperty("firstInhibitorAssist")
    private Boolean firstInhibitorAssist;
    @JsonProperty("perk0Var1")
    private Long perk0Var1;
    @JsonProperty("combatPlayerScore")
    private Long combatPlayerScore;
    @JsonProperty("perk0Var3")
    private Long perk0Var3;
    @JsonProperty("visionWardsBoughtInGame")
    private Long visionWardsBoughtInGame;
    @JsonProperty("pentaKills")
    private Long pentaKills;
    @JsonProperty("totalHeal")
    private Long totalHeal;
    @JsonProperty("totalMinionsKilled")
    private Long totalMinionsKilled;
    @JsonProperty("timeCCingOthers")
    private Long timeCCingOthers;
    @JsonProperty("statPerk2")
    private Long statPerk2;

}
