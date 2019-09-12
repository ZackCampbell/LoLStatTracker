package GameElements;

import API.DTO.*;
import dev.morphia.annotations.Embedded;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Embedded
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MatchParticipant {

    private int id;
    private String summonerName;
    private String currentAccountId;
    private int championId;
    private int teamId;
    private int spell1Id;
    private int spell2Id;
    private RankedTier highestAchievedSeasonTier;

    // TODO: Refactor this into a stats class? Not sure if necessary..
    private boolean firstBloodAssist;
    private long visionScore;
    private long magicDamageDealtToChampions;
    private long physicalDamageDealtToChampions;
    private long trueDamageDealtToChampions;
    private long damageDealtToObjectives;
    private long damageDealtToTurrets;
    private long magicDamageDealt;
    private long physicalDamageDealt;
    private long trueDamageDealt;
    private long totalDamageDealt;
    private long damageSelfMitigated;
    private long magicDamageTaken;
    private long trueDamageTaken;
    private long totalDamageTaken;
    private int totalCCTimeDealt; // Seconds..?
    private long timeCCingOthers; // Seconds..?
    private int longestTimeSpentLiving; // Seconds..?
    private int kills;
    private int deaths;
    private int assists;
    private int doubleKills;
    private int tripleKills;
    private int quadraKills;
    private int pentaKills;
    private int unrealKills;
    private int largestMultiKill;
    private int largestKillingSpree;
    private int killingSprees;
    private int playerScore0;
    private int playerScore1;
    private int playerScore2;
    private int playerScore3;
    private int playerScore4;
    private int playerScore5;
    private int playerScore6;
    private int playerScore7;
    private int playerScore8;
    private int playerScore9;
    private int totalScoreRank;
    private int neutralMinionsKilled;
    private int neutralMinionsKilledTeamJungle;
    private int neutralMinionsKilledEnemyJungle;
    private int totalUnitsHealed;
    private int wardsKilled;
    private int largestCriticalStrike;
    private boolean firstInhibitorKill;
    private int combatPlayerScore; // ???
    private int objectivePlayerScore; // ???
    private int totalPlayerScore; // ???
    private int goldSpent;
    private int goldEarned;
    private int wardsPlaced;
    private int turretKills;
    private boolean firstBloodKill;
    private boolean firstTowerAssist;
    private boolean firstTowerKill;
    private int champLevel;
    private int inhibitorKills;
    private boolean firstInhibitorAssist;
    private int visionWardsBought;
    private long totalHeal;
    private int totalMinionsKilled;
    private MatchRuneSetup runes;
    private Match.Lane lane;
    private Match.Role role;
    private StatDeltasDTO csDiffPerMinDeltas;
    private StatDeltasDTO goldPerMinDeltas;
    private StatDeltasDTO xpDiffPerMinDeltas;
    private StatDeltasDTO creepsPerMinDeltas;
    private StatDeltasDTO xpPerMinDeltas;
    private StatDeltasDTO damageTakenDiffPerMinDeltas;
    private StatDeltasDTO damageTakenPerMinDeltas;

    public MatchParticipant(PlayerDTO player, ParticipantDTO participant) {
        this.id = participant.getParticipantId();
        this.summonerName = player.getSummonerName();
        this.currentAccountId = player.getCurrentAccountId();
        this.championId = participant.getChampionId();
        this.teamId = participant.getTeamId();
        this.spell1Id = participant.getSpell1Id();
        this.spell2Id = participant.getSpell2Id();
        this.highestAchievedSeasonTier = RankedTier.fromValue(participant.getHighestAchievedSeasonTier());

        StatsDTO statDto = participant.getStats();

        this.firstBloodAssist = statDto.isFirstBloodAssist();
        this.visionScore = statDto.getVisionScore();
        this.magicDamageDealtToChampions = statDto.getMagicDamageDealtToChampions();
        this.physicalDamageDealtToChampions = statDto.getPhysicalDamageDealtToChampions();
        this.trueDamageDealtToChampions = statDto.getTrueDamageDealtToChampions();
        this.damageDealtToObjectives = statDto.getDamageDealtToObjectives();
        this.damageDealtToTurrets = statDto.getDamageDealtToTurrets();
        this.magicDamageDealt = statDto.getMagicDamageDealt();
        this.physicalDamageDealt = statDto.getPhysicalDamageDealt();
        this.trueDamageDealt = statDto.getTrueDamageDealt();
        this.totalDamageDealt = statDto.getTotalDamageDealt();
        this.damageSelfMitigated = statDto.getDamageSelfMitigated();
        this.magicDamageTaken = statDto.getMagicalDamageTaken();
        this.trueDamageTaken = statDto.getTrueDamageTaken();
        this.totalDamageTaken = statDto.getTotalDamageTaken();
        this.totalCCTimeDealt = statDto.getTotalTimeCrowdControlDealt();
        this.timeCCingOthers = statDto.getTimeCCingOthers();
        this.longestTimeSpentLiving = statDto.getLongestTimeSpentLiving();
        this.kills = statDto.getKills();
        this.deaths = statDto.getDeaths();
        this.assists = statDto.getAssists();
        this.doubleKills = statDto.getDoubleKills();
        this.tripleKills = statDto.getTripleKills();
        this.quadraKills = statDto.getQuadraKills();
        this.pentaKills = statDto.getPentaKills();
        this.unrealKills = statDto.getUnrealKills();
        this.largestMultiKill = statDto.getLargestMultiKill();
        this.largestKillingSpree = statDto.getLargestKillingSpree();
        this.killingSprees = statDto.getKillingSprees();
        this.playerScore0 = statDto.getPlayerScore0();
        this.playerScore1 = statDto.getPlayerScore1();
        this.playerScore2 = statDto.getPlayerScore2();
        this.playerScore3 = statDto.getPlayerScore3();
        this.playerScore4 = statDto.getPlayerScore4();
        this.playerScore5 = statDto.getPlayerScore5();
        this.playerScore6 = statDto.getPlayerScore6();
        this.playerScore7 = statDto.getPlayerScore7();
        this.playerScore8 = statDto.getPlayerScore8();
        this.playerScore9 = statDto.getPlayerScore9();
        this.totalScoreRank = statDto.getTotalScoreRank();
        this.neutralMinionsKilled = statDto.getNeutralMinionsKilled();
        this.neutralMinionsKilledTeamJungle = statDto.getNeutralMinionsKilledTeamJungle();
        this.neutralMinionsKilledEnemyJungle = statDto.getNeutralMinionsKilledEnemyJungle();
        this.totalUnitsHealed = statDto.getTotalUnitsHealed();
        this.wardsKilled = statDto.getWardsKilled();
        this.largestCriticalStrike = statDto.getLargestCriticalStrike();
        this.firstInhibitorKill = statDto.isFirstInhibitorKill();
        this.combatPlayerScore = statDto.getCombatPlayerScore();
        this.objectivePlayerScore = statDto.getObjectivePlayerScore();
        this.totalPlayerScore = statDto.getTotalPlayerScore();
        this.goldSpent = statDto.getGoldSpent();
        this.goldEarned = statDto.getGoldEarned();
        this.wardsPlaced = statDto.getWardsPlaced();
        this.turretKills = statDto.getTurretKills();
        this.firstBloodKill = statDto.isFirstBloodKill();
        this.firstTowerAssist = statDto.isFirstTowerAssist();
        this.firstTowerKill = statDto.isFirstTowerKill();
        this.champLevel = statDto.getChampLevel();
        this.inhibitorKills = statDto.getInhibitorKills();
        this.firstInhibitorAssist = statDto.isFirstInhibitorAssist();
        this.visionWardsBought = statDto.getVisionWardsBoughtInGame();
        this.totalHeal = statDto.getTotalHeal();
        this.totalMinionsKilled = statDto.getTotalMinionsKilled();
        this.runes = new MatchRuneSetup(statDto);

        TimelineDTO timelineDTO = participant.getTimeline();

        this.lane = Match.Lane.fromValue(timelineDTO.getLane());
        this.role = Match.Role.fromValue(timelineDTO.getRole());

        /// I'm not convinced these will actually be intelligible/reliable
        /// Main reason is each entry is a 10 MINUTE period. That's.. way too coarse..
        this.csDiffPerMinDeltas = timelineDTO.getCsDiffPerMinDeltas();
        this.goldPerMinDeltas = timelineDTO.getGoldPerMinDeltas();
        this.xpDiffPerMinDeltas = timelineDTO.getXpDiffPerMinDeltas();
        this.creepsPerMinDeltas = timelineDTO.getCreepsPerMinDeltas();
        this.xpPerMinDeltas = timelineDTO.getXpPerMinDeltas();
        this.damageTakenDiffPerMinDeltas = timelineDTO.getDamageTakenDiffPerMinDeltas();
        this.damageTakenPerMinDeltas = timelineDTO.getDamageTakenPerMinDeltas();

    }

}
