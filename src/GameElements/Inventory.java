package GameElements;

import API.DTO.ItemDTO;

import java.util.HashMap;

public class Inventory {
    HashMap<Long, ItemWrapper> itemTimings;
    HashMap<Long, Integer> gold;

    public Inventory() {
        this.itemTimings = new HashMap<>();
    }

    public void addItem(Long itemId, ItemWrapper.ItemState state, Long timestamp) {
        itemTimings.put(timestamp, new ItemWrapper(ItemDTO.getById(itemId), state));
    }
}
