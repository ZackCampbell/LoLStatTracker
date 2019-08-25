package GameElements;

import API.DTO.ItemDTO;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ItemWrapper {

    private ItemDTO item;
    private ItemState state;

    public ItemWrapper(ItemDTO item, ItemState state) {
        this.item = item;
        this.state = state;
    }

    @Getter
    public enum ItemState {
        PURCHASED("PURCHASED"),
        SOLD("SOLD"),
        UNDONE("UNDONE"),
        DESTROYED("DESTROYED"), // Consumed..?,
        UNKNOWN("UNKNOWN");

        private static Map<String, ItemState> reverseLookup = new HashMap<>();
        private String id;

        ItemState(String id) {
            this.id = id;
        }

        static {
            for (ItemState itemState : ItemState.values()) {
                reverseLookup.put(itemState.id, itemState);
            }
        }

        public static ItemState fromId(final String id) {
            return reverseLookup.getOrDefault(id, UNKNOWN);
        }
    }
}
