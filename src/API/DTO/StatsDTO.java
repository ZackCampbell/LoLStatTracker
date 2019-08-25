
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
    private int neutralMinionsKilledTeamJungle;
    @JsonProperty("visionScore")
    private int visionScore;
    @JsonProperty("magicDamageDealtToChampions")
    private long magicDamageDealtToChampions;
    @JsonProperty("largestMultiKill")
    private int largestMultiKill;
    @JsonProperty("totalTimeCrowdControlDealt")
    private int totalTimeCrowdControlDealt;
    @JsonProperty("longestTimeSpentLiving")
    private int longestTimeSpentLiving;
    @JsonProperty("perk1Var1")
    private int perk1Var1;
    @JsonProperty("perk1Var3")
    private int perk1Var3;
    @JsonProperty("perk1Var2")
    private int perk1Var2;
    @JsonProperty("tripleKills")
    private int tripleKills;
    @JsonProperty("perk5")
    private int perk5;
    @JsonProperty("perk4")
    private int perk4;
    @JsonProperty("playerScore9")
    private int playerScore9;
    @JsonProperty("playerScore8")
    private int playerScore8;
    @JsonProperty("kills")
    private int kills;
    @JsonProperty("playerScore1")
    private int playerScore1;
    @JsonProperty("playerScore0")
    private int playerScore0;
    @JsonProperty("playerScore3")
    private int playerScore3;
    @JsonProperty("playerScore2")
    private int playerScore2;
    @JsonProperty("playerScore5")
    private int playerScore5;
    @JsonProperty("playerScore4")
    private int playerScore4;
    @JsonProperty("playerScore7")
    private int playerScore7;
    @JsonProperty("playerScore6")
    private int playerScore6;
    @JsonProperty("perk5Var1")
    private int perk5Var1;
    @JsonProperty("perk5Var3")
    private int perk5Var3;
    @JsonProperty("perk5Var2")
    private int perk5Var2;
    @JsonProperty("totalScoreRank")
    private int totalScoreRank;
    @JsonProperty("neutralMinionsKilled")
    private int neutralMinionsKilled;
    @JsonProperty("statPerk1")
    private int statPerk1;
    @JsonProperty("statPerk0")
    private int statPerk0;
    @JsonProperty("damageDealtToTurrets")
    private long damageDealtToTurrets;
    @JsonProperty("physicalDamageDealtToChampions")
    private long physicalDamageDealtToChampions;
    @JsonProperty("damageDealtToObjectives")
    private long damageDealtToObjectives;
    @JsonProperty("perk2Var2")
    private int perk2Var2;
    @JsonProperty("perk2Var3")
    private int perk2Var3;
    @JsonProperty("totalUnitsHealed")
    private int totalUnitsHealed;
    @JsonProperty("perk2Var1")
    private int perk2Var1;
    @JsonProperty("perk4Var1")
    private int perk4Var1;
    @JsonProperty("totalDamageTaken")
    private long totalDamageTaken;
    @JsonProperty("perk4Var3")
    private int perk4Var3;
    @JsonProperty("wardsKilled")
    private int wardsKilled;
    @JsonProperty("largestCriticalStrike")
    private int largestCriticalStrike;
    @JsonProperty("largestKillingSpree")
    private int largestKillingSpree;
    @JsonProperty("quadraKills")
    private int quadraKills;
    @JsonProperty("magicDamageDealt")
    private long magicDamageDealt;
    @JsonProperty("firstBloodAssist")
    private boolean firstBloodAssist;
    @JsonProperty("item2")
    private int item2;
    @JsonProperty("item3")
    private int item3;
    @JsonProperty("item0")
    private int item0;
    @JsonProperty("item1")
    private int item1;
    @JsonProperty("item6")
    private int item6;
    @JsonProperty("item4")
    private int item4;
    @JsonProperty("item5")
    private int item5;
    @JsonProperty("perk1")
    private int perk1;
    @JsonProperty("perk0")
    private int perk0;
    @JsonProperty("perk3")
    private int perk3;
    @JsonProperty("perk2")
    private int perk2;
    @JsonProperty("perk3Var3")
    private int perk3Var3;
    @JsonProperty("perk3Var2")
    private int perk3Var2;
    @JsonProperty("perk3Var1")
    private int perk3Var1;
    @JsonProperty("damageSelfMitigated")
    private long damageSelfMitigated;
    @JsonProperty("magicalDamageTaken")
    private long magicalDamageTaken;
    @JsonProperty("perk0Var2")
    private int perk0Var2;
    @JsonProperty("firstInhibitorKill")
    private boolean firstInhibitorKill;
    @JsonProperty("trueDamageTaken")
    private long trueDamageTaken;
    @JsonProperty("assists")
    private int assists;
    @JsonProperty("perk4Var2")
    private int perk4Var2;
    @JsonProperty("goldSpent")
    private int goldSpent;
    @JsonProperty("trueDamageDealt")
    private long trueDamageDealt;
    @JsonProperty("participantId")
    private int participantId;
    @JsonProperty("physicalDamageDealt")
    private long physicalDamageDealt;
    @JsonProperty("sightWardsBoughtInGame")
    private int sightWardsBoughtInGame;
    @JsonProperty("totalDamageDealtToChampions")
    private long totalDamageDealtToChampions;
    @JsonProperty("physicalDamageTaken")
    private long physicalDamageTaken;
    @JsonProperty("totalPlayerScore")
    private int totalPlayerScore;
    @JsonProperty("win")
    private boolean win;
    @JsonProperty("objectivePlayerScore")
    private int objectivePlayerScore;
    @JsonProperty("totalDamageDealt")
    private long totalDamageDealt;
    @JsonProperty("neutralMinionsKilledEnemyJungle")
    private int neutralMinionsKilledEnemyJungle;
    @JsonProperty("deaths")
    private int deaths;
    @JsonProperty("wardsPlaced")
    private int wardsPlaced;
    @JsonProperty("perkPrimaryStyle")
    private int perkPrimaryStyle;
    @JsonProperty("perkSubStyle")
    private int perkSubStyle;
    @JsonProperty("turretKills")
    private int turretKills;
    @JsonProperty("firstBloodKill")
    private boolean firstBloodKill;
    @JsonProperty("trueDamageDealtToChampions")
    private long trueDamageDealtToChampions;
    @JsonProperty("goldEarned")
    private int goldEarned;
    @JsonProperty("killingSprees")
    private int killingSprees;
    @JsonProperty("unrealKills")
    private int unrealKills;
    @JsonProperty("firstTowerAssist")
    private boolean firstTowerAssist;
    @JsonProperty("firstTowerKill")
    private boolean firstTowerKill;
    @JsonProperty("champLevel")
    private int champLevel;
    @JsonProperty("doubleKills")
    private int doubleKills;
    @JsonProperty("inhibitorKills")
    private int inhibitorKills;
    @JsonProperty("firstInhibitorAssist")
    private boolean firstInhibitorAssist;
    @JsonProperty("perk0Var1")
    private int perk0Var1;
    @JsonProperty("combatPlayerScore")
    private int combatPlayerScore;
    @JsonProperty("perk0Var3")
    private int perk0Var3;
    @JsonProperty("visionWardsBoughtInGame")
    private int visionWardsBoughtInGame;
    @JsonProperty("pentaKills")
    private int pentaKills;
    @JsonProperty("totalHeal")
    private long totalHeal;
    @JsonProperty("totalMinionsKilled")
    private int totalMinionsKilled;
    @JsonProperty("timeCCingOthers")
    private int timeCCingOthers;
    @JsonProperty("statPerk2")
    private int statPerk2;

}
