package GameElements;

import API.DTO.TimelineDTO;
import GameElements.Events.MatchEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
@Getter
public class MatchParticipantTimeline {

    private List<MatchEvent> events;
    private List<MatchFrame> frames;

    public MatchParticipantTimeline() {
        this.events = new ArrayList<>();
        this.frames = new ArrayList<>();
    }

    public void addEvent(MatchEvent event) {
        this.events.add(event);
    }

    public void addFrame(MatchFrame frame) {
        this.frames.add(frame);
    }

}
