package br.com.andrebaroni.raspberryawards.application;

import java.io.Serializable;
import java.util.Collection;

public class AwardQuery implements Serializable {

    private Collection<AwardWinnerQuery> min;
    private Collection<AwardWinnerQuery> max;

    public AwardQuery(Collection<AwardWinnerQuery> min, Collection<AwardWinnerQuery> max) {
        this.min = min;
        this.max = max;
    }

    public Collection<AwardWinnerQuery> getMin() {
        return min;
    }

    public void setMin(Collection<AwardWinnerQuery> min) {
        this.min = min;
    }

    public Collection<AwardWinnerQuery> getMax() {
        return max;
    }

    public void setMax(Collection<AwardWinnerQuery> max) {
        this.max = max;
    }
}
