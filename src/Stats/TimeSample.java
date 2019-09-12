package Stats;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Represents a single time sample of data type T. The time sample represents a date
 * and a patch version of the game.
 * @param <T>
 */
@ToString
@Getter
@NoArgsConstructor
public class TimeSample<T> implements Comparable<TimeSample<T>> {

    private T data;
    private long timestamp;
    private String patch;

    public TimeSample(T data, long timestamp, String patch) {
        this.data = data;
        this.timestamp = timestamp;
        this.patch = patch;
    }

    public TimeSample(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(TimeSample<T> o) {
        if (o.data.getClass() != this.data.getClass()) {
            throw new ClassCastException("TimeSample data types do not match for comparison");
        }

        return Long.compare(this.timestamp, o.timestamp);
    }

    public static <T extends Number> T sum(T a, T b) {
        return (T) (Number) (a.doubleValue() + b.doubleValue());
    }
}
