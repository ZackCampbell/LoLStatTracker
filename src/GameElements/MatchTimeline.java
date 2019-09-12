package GameElements;

import API.DTO.*;
import GameElements.Events.*;
import dev.morphia.annotations.Embedded;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Embedded
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class MatchTimeline {

    private HashMap<Integer, MatchParticipantTimeline> participantTimelines;

    public MatchTimeline(MatchTimelineDTO matchTimelineDTO) {
        this.participantTimelines = new HashMap<>();

        List<MatchEventDTO> events = matchTimelineDTO.getFrames().stream()
                .flatMap(frame -> frame.getEvents().stream())
                .collect(Collectors.toList());

        for (MatchEventDTO matchEvent : events) {
            MatchEvent event = null;

            switch (MatchEventDTO.MatchEventType.fromId(matchEvent.getType())) {
                case ITEM_PURCHASED:
                    event = new ItemEvent(ItemEvent.ItemEventType.PURCHASED, matchEvent);
                    break;
                case ITEM_SOLD:
                    event = new ItemEvent(ItemEvent.ItemEventType.SOLD, matchEvent);
                    break;
                case ITEM_UNDO:
                    event = new ItemEvent(ItemEvent.ItemEventType.UNDO, matchEvent);
                    break;
                case ITEM_DESTROYED:
                    event = new ItemEvent(ItemEvent.ItemEventType.DESTROYED, matchEvent);
                    break;
                case WARD_PLACED:
                    event = new WardEvent(WardEvent.WardEventType.PLACED, matchEvent);
                    break;
                case WARD_KILL:
                    event = new WardEvent(WardEvent.WardEventType.KILLED, matchEvent);
                    break;
                case BUILDING_KILL:
                    event = new BuildingKillEvent(matchEvent);
                    break;
                case CHAMPION_KILL:
                    event = new ChampionKillEvent(matchEvent);
                    break;
                case SKILL_LEVEL_UP:
                    event = new SkillLevelUpEvent(matchEvent);
                    break;
                case ELITE_MONSTER_KILL:
                    event = new EliteMonsterKillEvent(matchEvent);
                    break;
                case UNKNOWN:
                    break;
            }

            if (event != null) {
                MatchParticipantTimeline timeline = this.participantTimelines.getOrDefault(
                        event.getCreatorId(), new MatchParticipantTimeline()
                );

                timeline.addEvent(event);
                this.participantTimelines.put(event.getCreatorId(), timeline);
            }
        }

        for (MatchFrameDTO frameDTO : matchTimelineDTO.getFrames()) {
            for (Map.Entry<String, MatchParticipantFrameDTO> participantFrame : frameDTO.getParticipantFrames ().entrySet()) {
                int participantId = Integer.parseInt(participantFrame.getKey());
                MatchParticipantTimeline timeline = this.participantTimelines.getOrDefault(
                        participantId, new MatchParticipantTimeline()
                );

                timeline.addFrame(
                        new MatchFrame(
                                frameDTO.getTimestamp(),
                                participantId,
                                participantFrame.getValue())
                );

                this.participantTimelines.put(participantId, timeline);
            }
        }

    }
}
