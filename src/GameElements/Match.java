package GameElements;

import API.DTO.*;
import API.MatchEndpoint;
import API.RiotAPIHandler;
import Main.Session;
import dev.morphia.annotations.*;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Getter
@ToString
@Entity("matches")
@Indexes({
    @Index(
            fields = @Field("timestamp"),
            options = @IndexOptions(name = "match_timestamp")
    ),
    @Index(
            fields = @Field("participantIds"),
            options = @IndexOptions(name = "match_participants")
    ),
    @Index(
            fields = @Field("participatingChampionIds"),
            options = @IndexOptions(name = "match_champions")
    )
})
public class Match {
    private static RiotAPIHandler handler = new RiotAPIHandler();
    private static MatchEndpoint endpoint = new MatchEndpoint(Session.getInstance().getCurrentRegion(), handler.getApi_key());

    @Id
    private long id;

    @Embedded
    private List<MatchTeam> teams;

    @Embedded
    private MatchTimeline timeline;

    private Date timestamp; // Timestamp when champ select ended
    private long length; // Game length, in seconds
    private int seasonId; // Riot doesn't actually update their static data with this.. As of writing, we are on "13" (Season 2019)
    private QueueType queue;
    private MatchMap map;
    private MatchType type;
    private String version;
    private String platformId;
    private String gameVersion;
    private String gameMode;
    private List<String> participantIds;
    private List<Integer> participatingChampionIds;

    public Match(long id) {
        /// Do we hit the endpoint right away or make this cheap to construct? Probably the latter..
        MatchDTO dto = endpoint.getMatchById(id);

        if (dto == null) {
            // Invalid game, probably
            this.id = -1;
            return;
        }

        List<MatchParticipant> participants = new ArrayList<>();

        this.id = id;
        this.timestamp = new Date(dto.getGameCreation());
        this.length = dto.getGameDuration();
        this.seasonId = dto.getSeasonId();
        this.queue = QueueType.fromId(dto.getQueueId());
        this.map = MatchMap.fromId(dto.getMapId());
        this.type = MatchType.fromValue(dto.getGameType());
        this.version = dto.getGameVersion();
        this.platformId = dto.getPlatformId();
        this.gameVersion = dto.getGameVersion();
        this.gameMode = dto.getGameMode();
        this.participantIds = new ArrayList<>();
        this.participatingChampionIds = new ArrayList<>();

        for (ParticipantDTO participantDTO : dto.getParticipants()) {
            for (ParticipantIdentityDTO participantIdentityDTO : dto.getParticipantIdentities()) {
                if (participantDTO.getParticipantId().equals(participantIdentityDTO.getParticipantId())) {
                    this.participantIds.add(participantIdentityDTO.getPlayer().getAccountId());
                    MatchParticipant participant = new MatchParticipant(
                            participantIdentityDTO.getPlayer(),
                            participantDTO
                    );
                    participants.add(participant);
                    this.participatingChampionIds.add(participant.getChampionId());
                }
            }
        }

        this.teams = new ArrayList<>();

        for (TeamStatsDTO teamStatsDTO : dto.getTeams()) {
            MatchTeam team = new MatchTeam(teamStatsDTO);
            team.addMatchingParticipants(participants);
            this.teams.add(team);
        }

        MatchTimelineDTO timelineDTO = endpoint.getMatchTimelineById(this.id);

        if (timelineDTO != null) {
            this.timeline = new MatchTimeline(timelineDTO);
        } else {
            this.timeline = null;
        }
    }

    public Integer getTeamIdOfParticipant(Integer participantId) {
        for (MatchTeam team : this.teams) {
            for (MatchParticipant participant : team.getParticipants()) {
                if (participant.getId() == participantId) {
                    return participant.getTeamId();
                }
            }
        }

        return -1;
    }

    public Match() {
        this.id = -1;
    }

    /// Example: 9.16.284.8446
    public boolean isFromPatch(String patchToMatch, PatchMatchMode mode) {
        String[] partsToMatch = patchToMatch.split("\\.");
        String[] partsOther = this.gameVersion.split("\\.");

        if (partsToMatch.length >= 1 && partsOther.length >= 1) {
            if (mode == PatchMatchMode.MAJOR_VERSION) {
                return partsToMatch[0].equals(partsOther[0]);
            }
        }

        if (partsToMatch.length >= 2 && partsOther.length >= 2) {
            if (mode == PatchMatchMode.MINOR_VERSION) {
                return partsToMatch[0].equals(partsOther[0]) &&
                       partsToMatch[1].equals(partsOther[1]);
            }
        }

        return false;
    }

    public boolean isValid() {
        return this.id > 0;
    }

    @Getter
    public enum MatchMap {
        SR_SUMMER(1, "Summoner's Rift (Summer Variant)"),
        SR_AUTUMN(2, "Summoner's Rift (Autumn Variant)"),
        PROVING_GROUND(3, "Proving Grounds (Tutorial)"),
        TT_ORIGINAL(4, "Twisted Treeline (Legacy)"),
        CRYSTAL_SCAR(8, "The Crystal Scar (Dominion)"),
        TT(10, "Twisted Treeline"),
        SR(11, "Summoner's Rift"),
        HA(12, "Howling Abyss"),
        BUTCHERS_BRIDGE(14, "Butcher's Bridge"),
        COSMIC_RUINS(16, "Cosmic Ruins (Dark Star: Singularity)"),
        VALORAN_CITY_PARK(18, "Valoran City Park (Star Guardian invasion)"),
        SUBSTRUCTURE_43(19, "PROJECT: Hunters"),
        CRASH_SITE(20, "Odyssey: Extraction"),
        NEXUS_BLITZ(21, "Nexus Blitz"),
        UNDEFINED(-1, "Undefined Map");

        private static Map<String, MatchMap> reverseLookup = new HashMap<>();
        private int id;
        private String value;

        MatchMap(int id, String value) {
            this.id = id;
            this.value = value;
        }

        static {
            for (MatchMap map : MatchMap.values()) {
                reverseLookup.put(String.valueOf(map.id), map);
            }
        }

        public static MatchMap fromId(final int id) {
            return reverseLookup.getOrDefault(String.valueOf(id), UNDEFINED);
        }
    }

    @Getter
    public enum QueueType {
        CUSTOM_GAME(0, "Custom Game"),
        HA_SNOWDOWN_1V1(72, "Snowdown 1v1"),
        HA_SNOWDOWN_2V2(73, "Snowdown 2v2"),
        SR_HEXAKILL_6V6(75, "Hexakill 6v6"),
        SR_URF(76, "URF"),
        HA_OFA_MIRROR_MODE(78, "One for All Mirror Mode"),
        SR_URF_COOP_VS_AI(83, "Coop vs. AI URF"),
        TT_HEXAKILL_6V6(98, "Hexakill 6v6"),
        BUTCHERS_BRIDGE_ARAM(100, "ARAM"),
        SR_5V5_DRAFT(400, "Draft Pick"),
        SR_RANKED_SOLO(420, "Ranked Solo/Duo"),
        SR_BLIND_PICK(430, "Blind Pick"),
        SR_RANKED_FLEX(440, "Ranked Flex");

        // TODO: Add other queue ids

        private static Map<String, QueueType> reverseLookup = new HashMap<>();
        private int id;
        private String value;

        QueueType(int id, String prettyName) {
            this.id = id;
            this.value = prettyName;
        }

        static {
            for (QueueType queue : QueueType.values()) {
                reverseLookup.put(String.valueOf(queue.id), queue);
            }
        }

        public static QueueType fromId(final int id) {
            return reverseLookup.getOrDefault(String.valueOf(id), CUSTOM_GAME);
        }
    }

    @Getter
    public enum MatchType {
        CUSTOM_GAME("CUSTOM_GAME", "Custom"),
        TUTORIAL_GAME("TUTORIAL GAME", "Tutorial"),
        MATCHED_GAME("MATCHED_GAME", "Matched");

        private static Map<String, MatchType> reverseLookup = new HashMap<>();
        String value;
        String prettyValue;

        MatchType(String value, String prettyValue) {
            this.value = value;
            this.prettyValue = prettyValue;
        }

        static {
            for (MatchType t : MatchType.values()) {
                reverseLookup.put(String.valueOf(t.value), t);
            }
        }

        public static MatchType fromValue(final String value) {
            return reverseLookup.getOrDefault(value, MATCHED_GAME);
        }
    }

    @Getter
    public enum Lane {
        TOP("TOP", "Top", "top"),
        JUNGLE("JUNGLE", "Jungle", "jg"),
        MIDDLE("MIDDLE", "Middle", "mid"),
        BOTTOM("BOTTOM", "Bottom", "bot"),
        UNKNOWN("UNKNOWN", "Unknown", "unknown");

        private static Map<String, Lane> reverseLookup = new HashMap<>();
        String value;
        String prettyValue;
        String shorthand;

        Lane(String value, String prettyValue, String shorthand) {
            this.value = value;
            this.prettyValue = prettyValue;
            this.shorthand = shorthand;
        }

        static {
            for (Lane l : Lane.values()) {
                reverseLookup.put(String.valueOf(l.value), l);
                reverseLookup.put(String.valueOf(l.shorthand).toUpperCase() + "_LANE", l);
            }
        }

        public static Lane fromValue(final String value) {
            return reverseLookup.getOrDefault(value, UNKNOWN);
        }
    }

    public enum PatchMatchMode {
        MAJOR_VERSION,
        MINOR_VERSION
    }
}
