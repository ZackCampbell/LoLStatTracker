package GameElements;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class MapPosition {
    private Integer x;
    private Integer y;

    public MapPosition() {
        this.x = -1;
        this.y = -1;
    }

    public MapPosition(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public boolean isValid() {
        return this.x > 0 && this.y > 0;
    }
}
