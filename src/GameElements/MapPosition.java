package GameElements;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MapPosition {
    private Integer x;
    private Integer y;

    public MapPosition(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
