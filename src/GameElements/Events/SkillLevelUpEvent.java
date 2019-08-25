package GameElements.Events;

import API.DTO.MatchEventDTO;
import GameElements.MapPosition;
import GameElements.Match;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
@NoArgsConstructor
public class SkillLevelUpEvent extends MatchEvent {

    SkillSlot skillSlot;
    SkillLevelUpType levelUpType;

    public SkillLevelUpEvent(MatchEventDTO dto) {
        super(dto.getTimestamp(), dto.getParticipantId(), new ArrayList<>());

        /// Skill level up event specific properties
        this.skillSlot = SkillSlot.fromId(dto.getSkillSlot());
        this.levelUpType = SkillLevelUpType.fromId(dto.getLevelUpType());
    }

    @Getter
    public enum SkillSlot {
        Q(1),
        W(2),
        E(3),
        R(4),
        UNKNOWN(-1);

        private static Map<Integer, SkillSlot> reverseLookup = new HashMap<>();
        private Integer id;

        SkillSlot(Integer id) {
            this.id = id;
        }

        static {
            for (SkillSlot type : SkillSlot.values()) {
                reverseLookup.put(type.id, type);
            }
        }

        public static SkillSlot fromId(final Integer id) {
            return reverseLookup.getOrDefault(id, UNKNOWN);
        }
    }

    @Getter
    public enum SkillLevelUpType {
        NORMAL("NORMAL"),
        EVOLVE("EVOLVE"),
        UNKNOWN("UNKNOWN");

        private static Map<String, SkillLevelUpType> reverseLookup = new HashMap<>();
        private String id;

        SkillLevelUpType(String id) {
            this.id = id;
        }

        static {
            for (SkillLevelUpType type : SkillLevelUpType.values()) {
                reverseLookup.put(type.id, type);
            }
        }

        public static SkillLevelUpType fromId(final String id) {
            return reverseLookup.getOrDefault(id, UNKNOWN);
        }
    }

}
