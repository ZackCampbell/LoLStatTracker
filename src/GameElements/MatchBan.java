package GameElements;

import API.DTO.BanDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class MatchBan {

    private int pickTurn; // Turn when the champion was banned
    private int championId;

    public MatchBan(BanDTO dto) {
        this.pickTurn = dto.getPickTurn();
        this.championId = dto.getChampionId();
    }
}
