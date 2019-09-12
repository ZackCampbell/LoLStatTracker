package GameElements.Events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public abstract class MatchEvent {

    private Long timestamp;
    private Integer creatorId;
    private List<Integer> assistingIds;

    MatchEvent(Long timestamp, Integer creatorId, List<Integer> assistingIds) {
        this.timestamp = timestamp;
        this.creatorId = creatorId;
        this.assistingIds = assistingIds;
    }

}
