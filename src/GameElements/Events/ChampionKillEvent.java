package GameElements.Events;

import API.DTO.MatchEventDTO;
import GameElements.MapPosition;
import GameElements.Match;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ChampionKillEvent extends MatchEvent {

    MapPosition position;
    Integer victimId;

    public ChampionKillEvent(MatchEventDTO dto) {
        super(dto.getTimestamp(), dto.getKillerId(), dto.getAssistingParticipantIds());

        /// Champion kill event specific properties
        this.position = new MapPosition(dto.getPosition().getX(), dto.getPosition().getY());
        this.victimId = dto.getVictimId();
    }

}
