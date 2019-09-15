package Stats;

import java.util.Comparator;

public class TemporalNumberStat<T extends Number> extends TemporalStat<T> {

    public T getAverage() {
        T sum = (T) (Number) 0;
        return this.getSamples().stream().map(TimeSample::getData).reduce(sum, TimeSample::sum);
    }

    public Number[] getValueRange() {
        this.getSamples().sort(Comparator.comparingDouble(ts -> ts.getData().doubleValue()));

        return new Number[] {
                this.getSamples().get(0).getData(),
                this.getSamples().get(this.getSamples().size() - 1).getData()
        };
    }
}
