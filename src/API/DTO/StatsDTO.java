
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
public class StatsDTO {

    @JsonProperty("neutralMinionsKilledTeamJungle")
    public Long neutralMinionsKilledTeamJungle;
    @JsonProperty("visionScore")
    public Long visionScore;
    @JsonProperty("magicDamageDealtToChampions")
    public Long magicDamageDealtToChampions;
    @JsonProperty("largestMultiKill")
    public Long largestMultiKill;
    @JsonProperty("totalTimeCrowdControlDealt")
    public Long totalTimeCrowdControlDealt;
    @JsonProperty("longestTimeSpentLiving")
    public Long longestTimeSpentLiving;
    @JsonProperty("perk1Var1")
    public Long perk1Var1;
    @JsonProperty("perk1Var3")
    public Long perk1Var3;
    @JsonProperty("perk1Var2")
    public Long perk1Var2;
    @JsonProperty("tripleKills")
    public Long tripleKills;
    @JsonProperty("perk5")
    public Long perk5;
    @JsonProperty("perk4")
    public Long perk4;
    @JsonProperty("playerScore9")
    public Long playerScore9;
    @JsonProperty("playerScore8")
    public Long playerScore8;
    @JsonProperty("kills")
    public Long kills;
    @JsonProperty("playerScore1")
    public Long playerScore1;
    @JsonProperty("playerScore0")
    public Long playerScore0;
    @JsonProperty("playerScore3")
    public Long playerScore3;
    @JsonProperty("playerScore2")
    public Long playerScore2;
    @JsonProperty("playerScore5")
    public Long playerScore5;
    @JsonProperty("playerScore4")
    public Long playerScore4;
    @JsonProperty("playerScore7")
    public Long playerScore7;
    @JsonProperty("playerScore6")
    public Long playerScore6;
    @JsonProperty("perk5Var1")
    public Long perk5Var1;
    @JsonProperty("perk5Var3")
    public Long perk5Var3;
    @JsonProperty("perk5Var2")
    public Long perk5Var2;
    @JsonProperty("totalScoreRank")
    public Long totalScoreRank;
    @JsonProperty("neutralMinionsKilled")
    public Long neutralMinionsKilled;
    @JsonProperty("statPerk1")
    public Long statPerk1;
    @JsonProperty("statPerk0")
    public Long statPerk0;
    @JsonProperty("damageDealtToTurrets")
    public Long damageDealtToTurrets;
    @JsonProperty("physicalDamageDealtToChampions")
    public Long physicalDamageDealtToChampions;
    @JsonProperty("damageDealtToObjectives")
    public Long damageDealtToObjectives;
    @JsonProperty("perk2Var2")
    public Long perk2Var2;
    @JsonProperty("perk2Var3")
    public Long perk2Var3;
    @JsonProperty("totalUnitsHealed")
    public Long totalUnitsHealed;
    @JsonProperty("perk2Var1")
    public Long perk2Var1;
    @JsonProperty("perk4Var1")
    public Long perk4Var1;
    @JsonProperty("totalDamageTaken")
    public Long totalDamageTaken;
    @JsonProperty("perk4Var3")
    public Long perk4Var3;
    @JsonProperty("wardsKilled")
    public Long wardsKilled;
    @JsonProperty("largestCriticalStrike")
    public Long largestCriticalStrike;
    @JsonProperty("largestKillingSpree")
    public Long largestKillingSpree;
    @JsonProperty("quadraKills")
    public Long quadraKills;
    @JsonProperty("magicDamageDealt")
    public Long magicDamageDealt;
    @JsonProperty("firstBloodAssist")
    public Boolean firstBloodAssist;
    @JsonProperty("item2")
    public Long item2;
    @JsonProperty("item3")
    public Long item3;
    @JsonProperty("item0")
    public Long item0;
    @JsonProperty("item1")
    public Long item1;
    @JsonProperty("item6")
    public Long item6;
    @JsonProperty("item4")
    public Long item4;
    @JsonProperty("item5")
    public Long item5;
    @JsonProperty("perk1")
    public Long perk1;
    @JsonProperty("perk0")
    public Long perk0;
    @JsonProperty("perk3")
    public Long perk3;
    @JsonProperty("perk2")
    public Long perk2;
    @JsonProperty("perk3Var3")
    public Long perk3Var3;
    @JsonProperty("perk3Var2")
    public Long perk3Var2;
    @JsonProperty("perk3Var1")
    public Long perk3Var1;
    @JsonProperty("damageSelfMitigated")
    public Long damageSelfMitigated;
    @JsonProperty("magicalDamageTaken")
    public Long magicalDamageTaken;
    @JsonProperty("perk0Var2")
    public Long perk0Var2;
    @JsonProperty("firstInhibitorKill")
    public Boolean firstInhibitorKill;
    @JsonProperty("trueDamageTaken")
    public Long trueDamageTaken;
    @JsonProperty("assists")
    public Long assists;
    @JsonProperty("perk4Var2")
    public Long perk4Var2;
    @JsonProperty("goldSpent")
    public Long goldSpent;
    @JsonProperty("trueDamageDealt")
    public Long trueDamageDealt;
    @JsonProperty("participantId")
    public Long participantId;
    @JsonProperty("physicalDamageDealt")
    public Long physicalDamageDealt;
    @JsonProperty("sightWardsBoughtInGame")
    public Long sightWardsBoughtInGame;
    @JsonProperty("totalDamageDealtToChampions")
    public Long totalDamageDealtToChampions;
    @JsonProperty("physicalDamageTaken")
    public Long physicalDamageTaken;
    @JsonProperty("totalPlayerScore")
    public Long totalPlayerScore;
    @JsonProperty("win")
    public Boolean win;
    @JsonProperty("objectivePlayerScore")
    public Long objectivePlayerScore;
    @JsonProperty("totalDamageDealt")
    public Long totalDamageDealt;
    @JsonProperty("neutralMinionsKilledEnemyJungle")
    public Long neutralMinionsKilledEnemyJungle;
    @JsonProperty("deaths")
    public Long deaths;
    @JsonProperty("wardsPlaced")
    public Long wardsPlaced;
    @JsonProperty("perkPrimaryStyle")
    public Long perkPrimaryStyle;
    @JsonProperty("perkSubStyle")
    public Long perkSubStyle;
    @JsonProperty("turretKills")
    public Long turretKills;
    @JsonProperty("firstBloodKill")
    public Boolean firstBloodKill;
    @JsonProperty("trueDamageDealtToChampions")
    public Long trueDamageDealtToChampions;
    @JsonProperty("goldEarned")
    public Long goldEarned;
    @JsonProperty("killingSprees")
    public Long killingSprees;
    @JsonProperty("unrealKills")
    public Long unrealKills;
    @JsonProperty("firstTowerAssist")
    public Boolean firstTowerAssist;
    @JsonProperty("firstTowerKill")
    public Boolean firstTowerKill;
    @JsonProperty("champLevel")
    public Long champLevel;
    @JsonProperty("doubleKills")
    public Long doubleKills;
    @JsonProperty("inhibitorKills")
    public Long inhibitorKills;
    @JsonProperty("firstInhibitorAssist")
    public Boolean firstInhibitorAssist;
    @JsonProperty("perk0Var1")
    public Long perk0Var1;
    @JsonProperty("combatPlayerScore")
    public Long combatPlayerScore;
    @JsonProperty("perk0Var3")
    public Long perk0Var3;
    @JsonProperty("visionWardsBoughtInGame")
    public Long visionWardsBoughtInGame;
    @JsonProperty("pentaKills")
    public Long pentaKills;
    @JsonProperty("totalHeal")
    public Long totalHeal;
    @JsonProperty("totalMinionsKilled")
    public Long totalMinionsKilled;
    @JsonProperty("timeCCingOthers")
    public Long timeCCingOthers;
    @JsonProperty("statPerk2")
    public Long statPerk2;

}
