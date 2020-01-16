package br.com.andrebaroni.raspberryawards.application;

import br.com.andrebaroni.raspberryawards.domain.entity.Movie;
import br.com.andrebaroni.raspberryawards.domain.entity.Producer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

public class AwardWinnerQuery implements Serializable {

    private String producer;
    private Long interval;
    private Long previousWin;
    private Long followingWin;

    private AwardWinnerQuery() {
        super();
        this.setInterval(0L);
    }

    public AwardWinnerQuery(String producer) {
        this();
        this.setProducer(producer);
    }

    public AwardWinnerQuery(Producer producer) {
        this();
        this.setProducer(producer.getName());
        producer.getMovies().forEach(this::computeMovie);
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public Long getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(Long previousWin) {
        this.previousWin = previousWin;
    }

    public Long getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Long followingWin) {
        this.followingWin = followingWin;
    }

    @JsonIgnore
    public Boolean isAwardValid() {
        return Objects.nonNull(this.getInterval()) && this.getInterval() > 0;
    }

    public void computeMovie(Movie movie) {
        if (movie.getWinner()) {
            if (Objects.isNull(this.getPreviousWin())) {
                this.setPreviousWin(movie.getYear());
            } else if (movie.getYear() < this.getPreviousWin()) {
                this.setPreviousWin(movie.getYear());
            }

            if (Objects.isNull(this.getFollowingWin())) {
                this.setFollowingWin(movie.getYear());
            } else if (movie.getYear() > this.getFollowingWin()) {
                this.setFollowingWin(movie.getYear());
            }

            this.setInterval(this.getFollowingWin() - this.getPreviousWin());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AwardWinnerQuery)) return false;
        AwardWinnerQuery that = (AwardWinnerQuery) o;
        return Objects.equals(getProducer(), that.getProducer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProducer());
    }
}
