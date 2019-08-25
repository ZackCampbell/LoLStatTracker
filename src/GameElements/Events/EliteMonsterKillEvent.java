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
public class EliteMonsterKillEvent extends MatchEvent {

    MapPosition position;
    MonsterType monsterType;

    public EliteMonsterKillEvent(MatchEventDTO dto) {
        super(dto.getTimestamp(), dto.getKillerId(), new ArrayList<>());

        /// Elite monster kill event specific properties
        this.position = new MapPosition(dto.getPosition().getX(), dto.getPosition().getY());
        this.monsterType = MonsterType.fromId(
                dto.getMonsterSubType() != null ?
                dto.getMonsterSubType() : dto.getMonsterType());
    }

    @Getter
    public enum BuildingType {
        TOWER_BUILDING("TOWER_BUILDING"),
        INHIBITOR_BUILDING("INHIBITOR_BUILDING"),
        UNKNOWN("UNKNOWN");

        private static Map<String, BuildingType> reverseLookup = new HashMap<>();
        private String id;

        BuildingType(String id) {
            this.id = id;
        }

        static {
            for (BuildingType type : BuildingType.values()) {
                reverseLookup.put(type.id, type);
            }
        }

        public static BuildingType fromId(final String id) {
            return reverseLookup.getOrDefault(id, UNKNOWN);
        }
    }

    @Getter
    public enum MonsterType {
        FIRE_DRAGON("FIRE_DRAGON"),
        WATER_DRAGON( "WATER_DRAGON"),
        EARTH_DRAGON("EARTH_DRAGON"),
        AIR_DRAGON("AIR_DRAGON"),
        BARON("BARON_NASHOR"),
        RIFT_HERALD("RIFTHERALD"),
        UKNNOWN("UKNNOWN");

        private static Map<String, MonsterType> reverseLookup = new HashMap<>();
        private String id;

        MonsterType(String id) {
            this.id = id;
        }

        static {
            for (MonsterType type : MonsterType.values()) {
                reverseLookup.put(type.id, type);
            }
        }

        public static MonsterType fromId(final String id) {
            return reverseLookup.getOrDefault(id, UKNNOWN);
        }
    }

}
