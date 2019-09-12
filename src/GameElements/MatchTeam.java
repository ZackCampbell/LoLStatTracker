package GameElements;

import API.DTO.TeamStatsDTO;
import dev.morphia.annotations.Embedded;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@ToString
@Embedded
@NoArgsConstructor
@EqualsAndHashCode
public class MatchTeam {

    @Embedded
    private List<MatchParticipant> participants;

    private TeamSide teamId;
    private boolean isWinner;
    private MatchBan[] bans;
    private boolean firstTower;
    private boolean firstInhibitor;
    private boolean firstDragon;
    private boolean firstRiftHerald;
    private boolean firstBaron;
    private boolean firstBlood;
    private int numTowerKills;
    private int numInhibitorKills;
    private int numDragonKills;
    private int numRiftHeraldKills;
    private int numBaronKills;
    private int numVilemawKills;
    private int dominionVictoryScore;

    public MatchTeam(TeamStatsDTO dto) {
        this.teamId = TeamSide.fromId(dto.getTeamId());
        this.participants = new ArrayList<>();
        this.isWinner = dto.getWin().equals("Win");
        this.bans = dto.getBans().stream().map(MatchBan::new).toArray(MatchBan[]::new);
        this.firstTower = dto.getFirstTower();
        this.firstInhibitor = dto.getFirstInhibitor();
        this.firstDragon = dto.getFirstDragon();
        this.firstRiftHerald = dto.getFirstRiftHerald();
        this.firstBaron = dto.getFirstBaron();
        this.firstBlood = dto.getFirstBlood();
        this.numTowerKills = dto.getTowerKills();
        this.numInhibitorKills = dto.getInhibitorKills();
        this.numDragonKills = dto.getDragonKills();
        this.numRiftHeraldKills = dto.getRiftHeraldKills();
        this.numBaronKills = dto.getBaronKills();
        this.numVilemawKills = dto.getVilemawKills();
        this.dominionVictoryScore = dto.getDominionVictoryScore();
    }

    public void addMatchingParticipants(List<MatchParticipant> participants) {
        this.participants.addAll(
            participants
                    .stream()
                    .filter(matchParticipant -> matchParticipant.getTeamId() == this.teamId.getId())
                    .collect(Collectors.toList())
        );
    }

    @Getter
    public enum TeamSide {
        BLUE(100),
        RED(200),
        UNKNOWN(0);

        private static Map<Integer, TeamSide> reverseLookup = new HashMap<>();
        private final int id;

        TeamSide(int id) {
            this.id = id;
        }

        static {
            for (TeamSide side : TeamSide.values()) {
                reverseLookup.put(side.id, side);
            }
        }

        public static TeamSide fromId(final int id) {
            return reverseLookup.getOrDefault(id, UNKNOWN);
        }
    }
}
