package Stats;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a time-varying stat of type T. The samples are populated from
 * multiple games, and keep track of patch version of the game.
 * @param <T>
 */
@Getter
public class TemporalStat<T> {

    private List<TimeSample<T>> samples;

    public TemporalStat() {
        this.samples = new ArrayList<>();
    }

    public void addSample(TimeSample<T> sample) {
        this.samples.add(sample);
    }

    public List<TimeSample<T>> getNeighboringSamples(long timestamp) {
        Collections.sort(this.samples);
        TimeSample<T> toTest = new TimeSample<>(timestamp);
        List<TimeSample<T>> neighbors = new ArrayList<>();

        int index = Collections.binarySearch(this.samples, toTest);
        if (index < 0) index = ~index;

        if (this.samples.size() == 0) {
            neighbors.add(0, null);
            neighbors.add(1, null);

            return neighbors;
        }

        if (index == 0) {
            neighbors.add(0, null);
            neighbors.add(0, this.samples.get(0));

            return neighbors;
        } else if (index == this.samples.size() - 1) {
            neighbors.add(0, this.samples.get(this.samples.size() - 1));
            neighbors.add(1, null);

            return neighbors;
        } else {
            neighbors.add(0, this.samples.get(index - 1));
            neighbors.add(1, this.samples.get(index + 1));

            return neighbors;
        }
    }

    public List<TimeSample<T>> getTimeSamplesBetween(long start, long end) {
        return this.samples.parallelStream()
                .filter(timeSample -> timeSample.getTimestamp() >= start &&
                        timeSample.getTimestamp() <= end)
                .collect(Collectors.toList());
    }

    public TimeSample<T> getLastSample() {
        Collections.sort(this.samples);
        return this.samples.get(this.samples.size() - 1);
    }

    public Long[] getTimestampRange() {
        Collections.sort(this.samples);

        return new Long[] {this.samples.get(0).getTimestamp(),
                           this.samples.get(this.samples.size() - 1).getTimestamp()};
    }

    public T getMode() {
        return null;
    }
}
