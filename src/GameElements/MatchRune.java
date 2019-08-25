package GameElements;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MatchRune {

    private int runeId;
    private int var1;
    private int var2;
    private int var3;

    public MatchRune(int id, int var1, int var2, int var3) {
        this.runeId = id;
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
    }

}