package GameElements;

import API.DTO.StatsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MatchRuneSetup {

    private int primaryType;
    private MatchRune[] primaryTree; // 4 runes from primaryType tree
    private int secondaryType;
    private MatchRune[] secondaryTree; // 2 runes from secondaryType tree
    private int statPerk0;
    private int statPerk1;
    private int statPerk2;

    public MatchRuneSetup(StatsDTO dto) {
        this.primaryType = dto.getPerkPrimaryStyle();
        this.secondaryType = dto.getPerkSubStyle();
        this.primaryTree = new MatchRune[] {
                new MatchRune(dto.getPerk0(), dto.getPerk0Var1(), dto.getPerk0Var2(), dto.getPerk0Var3()), // Keystone
                new MatchRune(dto.getPerk1(), dto.getPerk1Var1(), dto.getPerk1Var2(), dto.getPerk1Var3()),
                new MatchRune(dto.getPerk2(), dto.getPerk2Var1(), dto.getPerk2Var2(), dto.getPerk2Var3()),
                new MatchRune(dto.getPerk3(), dto.getPerk3Var1(), dto.getPerk3Var2(), dto.getPerk3Var3())
        };
        this.secondaryTree = new MatchRune[] {
                new MatchRune(dto.getPerk4(), dto.getPerk4Var1(), dto.getPerk4Var2(), dto.getPerk4Var3()),
                new MatchRune(dto.getPerk5(), dto.getPerk5Var1(), dto.getPerk5Var2(), dto.getPerk5Var3())
        };

        this.statPerk0 = dto.getStatPerk0();
        this.statPerk1 = dto.getStatPerk1();
        this.statPerk2 = dto.getStatPerk2();
    }

}
