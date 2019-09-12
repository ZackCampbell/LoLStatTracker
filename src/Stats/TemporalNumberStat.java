package Stats;

import java.util.Comparator;
import java.util.Optional;

public class TemporalNumberStat<T extends Number> extends TemporalStat<T> {

    public T getAverage() {
        T sum = (T) (Number) 0;
        return this.samples.stream().map(TimeSample::getData).reduce(sum, TimeSample::sum);
    }

    public Number[] getValueRange() {
        this.samples.sort(Comparator.comparingDouble(ts -> ts.getData().doubleValue()));

        return new Number[] {
                this.samples.get(0).getData(),
                this.samples.get(this.samples.size() - 1).getData()
        };
    }
}
