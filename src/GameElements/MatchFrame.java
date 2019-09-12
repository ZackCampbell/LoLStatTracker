package GameElements;

import API.DTO.MatchParticipantFrameDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MatchFrame {

    Long timestamp;
    Integer creatorId;
    private Long totalGold;
    private Long teamScore;
    private Long participantId;
    private Long level;
    private Long currentGold;
    private Long minionsKilled;
    private MapPosition position;
    private Long xp;
    private Long jungleMinionsKilled;

   MatchFrame(Long timestamp, Integer creatorId, MatchParticipantFrameDTO frameDTO) {
       this.timestamp = timestamp;
       this.creatorId = creatorId;
       this.totalGold = frameDTO.getTotalGold();
       this.teamScore = frameDTO.getTeamScore();
       this.participantId = frameDTO.getParticipantId();
       this.level = frameDTO.getLevel();
       this.currentGold = frameDTO.getCurrentGold();
       this.minionsKilled = frameDTO.getMinionsKilled();

       if (frameDTO.getPosition() != null) {
           /// Apparently this can end up being not recorded..
           this.position = new MapPosition(frameDTO.getPosition().getX(), frameDTO.getPosition().getY());
       } else {
           this.position = new MapPosition();
       }

       this.xp = frameDTO.getXp();
       this.jungleMinionsKilled = frameDTO.getJungleMinionsKilled();
   }
}
