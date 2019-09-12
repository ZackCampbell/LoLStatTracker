package GameElements;

import API.DTO.ItemDTO;
import GameElements.Events.ItemEvent;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@ToString
public class ItemWrapper implements Comparable<ItemWrapper> {

    private ItemDTO item;
    private Integer id;
    private Integer beforeId;
    private Integer afterId;
    private ItemEvent.ItemEventType state;
    private Long timestamp;

    public ItemWrapper(Integer id, Integer beforeId, Integer afterId, ItemEvent.ItemEventType itemState, Long timestamp) {
        this.item = ItemDTO.getById(id);
        this.id = id;
        this.beforeId = beforeId;
        this.afterId = afterId;
        this.state = itemState;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(ItemWrapper o) {
        return Long.compare(this.timestamp, o.timestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemWrapper that = (ItemWrapper) o;
        return id.equals(that.id) &&
                state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state);
    }
}
