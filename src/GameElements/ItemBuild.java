package GameElements;

import API.DTO.ItemDTO;
import GameElements.Events.ItemEvent;
import GameElements.Events.MatchEvent;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@ToString
public class ItemBuild {

    List<ItemWrapper> itemTimings;
    public boolean isWinningBuild = false;

    public ItemBuild(MatchParticipantTimeline timeline) {
        this.itemTimings = new ArrayList<>();

        for (MatchEvent event : timeline.getEvents()) {
            if (event instanceof ItemEvent) {
                ItemEvent ie = (ItemEvent) event;
                this.addItem(ie.getItemId(), ie.getBeforeId(), ie.getBeforeId(), ie.getType(), ie.getTimestamp());
            }
        }
    }

    private void addItem(Integer itemId, Integer beforeId, Integer afterId, ItemEvent.ItemEventType eventType, Long timestamp) {
        if (eventType == ItemEvent.ItemEventType.UNDO) {
            itemId = beforeId;
        }

        this.itemTimings.add(new ItemWrapper(itemId, beforeId, afterId, eventType, timestamp));
    }

    public List<ItemWrapper> getAllPurchases() {
        return this.itemTimings
                .stream()
                .filter(itemWrapper -> itemWrapper.getState() == ItemEvent.ItemEventType.PURCHASED)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<ItemWrapper> getCoreItemPurchases() {
        List<ItemWrapper> allPurchases = this.getAllPurchases();
        List<ItemWrapper> coreItems =  allPurchases.stream()
                .filter(itemWrapper -> itemWrapper.getItem().getDepth() > 0)
                .sorted()
                .collect(Collectors.toList());
        List<ItemWrapper> undoneAndSold = this.itemTimings
                .stream()
                .filter(itemWrapper -> itemWrapper.getState() == ItemEvent.ItemEventType.SOLD || itemWrapper.getState() == ItemEvent.ItemEventType.UNDO)
                .collect(Collectors.toList());

        /// Filter out core items that have been undone or sold
        for (ItemWrapper item : coreItems) {
            for (ItemWrapper otherItem : undoneAndSold) {
                if (item.equals(otherItem)) {
                    coreItems.remove(item);
                }
            }
        }

        return coreItems;
    }

    // Collapses all purchases that have been purchased then undone
    public List<ItemWrapper> getCondensed() {
        List<ItemWrapper> condensed = new ArrayList<>(this.itemTimings);

        for (ItemWrapper item : this.itemTimings) {
            if (item.getState() != ItemEvent.ItemEventType.PURCHASED) {
                continue;
            }

            for (ItemWrapper item2 : this.itemTimings) {
                if (item2.getState() != ItemEvent.ItemEventType.UNDO) {
                    continue;
                }

                if (!item2.getId().equals(item.getId())) {
                    continue;
                }

                if (item2.getTimestamp() < item.getTimestamp()) {
                    continue;
                }

                condensed.remove(item);
                condensed.remove(item2);
            }
        }

        return condensed;
    }

    public int checksumCoreItems() {
        List<ItemWrapper> items = this.getCoreItemPurchases();
        items.sort(Comparator.comparingInt(ItemWrapper::getId));

        StringBuilder checksum = new StringBuilder(0);

        for (ItemWrapper item : items) {
         checksum.append(item.getId()).append(item.getState()).append(":");
        }

        return checksum.toString().hashCode();
    }

    public static Map<Integer, List<ItemBuild>> groupByCoreItems(List<ItemBuild> builds) {
        return builds.stream()
                .collect(Collectors.groupingBy(ItemBuild::checksumCoreItems));
    }
}
