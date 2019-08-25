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
public class BuildingKillEvent extends MatchEvent {

    MapPosition position;
    BuildingType buildingType;
    Integer teamId;
    TowerType towerType;
    Match.Lane lane;

    public BuildingKillEvent(MatchEventDTO dto) {
        super(dto.getTimestamp(), dto.getKillerId(), dto.getAssistingParticipantIds());

        /// Building kill event specific properties
        this.position = new MapPosition(dto.getPosition().getX(), dto.getPosition().getY());
        this.buildingType = BuildingType.fromId(dto.getBuildingType());
        this.teamId = dto.getTeamId(); // Since the killer id can be 0 (killed by minions.. I think..)
        this.towerType = TowerType.fromId(dto.getTowerType());
        this.lane = Match.Lane.fromValue(dto.getLaneType());
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
    public enum TowerType {
        INNER_TURRET("INNER_TURRET"),
        OUTER_TURRET("OUTER_TURRET"),
        BASE_TURRET("BASE_TURRET"),
        NEXUS_TURRET("NEXUS_TURRET"),
        UNDEFINED_TURRET("UNDEFINED_TURRET"); // Inhibitor

        private static Map<String, TowerType> reverseLookup = new HashMap<>();
        private String id;

        TowerType(String id) {
            this.id = id;
        }

        static {
            for (TowerType type : TowerType.values()) {
                reverseLookup.put(type.id, type);
            }
        }

        public static TowerType fromId(final String id) {
            return reverseLookup.getOrDefault(id, UNDEFINED_TURRET);
        }
    }

}
