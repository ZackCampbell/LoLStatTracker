package GameElements;

import GameElements.Events.MatchEvent;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class MatchParticipantTimeline {

    private List<MatchEvent> events;

    public MatchParticipantTimeline() {
        this.events = new ArrayList<>();
    }

    public void addEvent(MatchEvent event) {
        this.events.add(event);
    }

}
