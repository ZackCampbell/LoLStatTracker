package GameElements.Events;

import API.DTO.MatchEventDTO;
import GameElements.Match;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ItemEvent extends MatchEvent {

    ItemEventType type;
    Integer itemId;
    Integer beforeId;
    Integer afterId;

    public ItemEvent(ItemEventType type, MatchEventDTO dto) {
        super(dto.getTimestamp(), dto.getParticipantId(), new ArrayList<>());

        /// Item event specific properties
        this.type = type;
        this.itemId = dto.getItemId();
        this.beforeId = dto.getBeforeId();
        this.afterId = dto.getAfterId();
    }

    @Getter
    public enum ItemEventType {
        PURCHASED("PURCHASED"),
        SOLD("SOLD"),
        UNDO("UNDO"),
        DESTROYED("DESTROYED"), // Consumed..?,
        UNKNOWN("UNKNOWN");

        private static Map<String, ItemEventType> reverseLookup = new HashMap<>();
        private String id;

        ItemEventType(String id) {
            this.id = id;
        }

        static {
            for (ItemEventType type : ItemEventType.values()) {
                reverseLookup.put(type.id, type);
            }
        }

        public static ItemEventType fromId(final String id) {
            return reverseLookup.getOrDefault(id, UNKNOWN);
        }
    }
}
