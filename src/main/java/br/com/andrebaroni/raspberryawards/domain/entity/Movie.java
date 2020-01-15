package br.com.andrebaroni.raspberryawards.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(nullable = false)
    private Long year;

    @Column(nullable = false)
    private String title;

    @Column
    private Boolean winner;

    @ManyToMany
    @JoinTable(name = "movie_studio",
            joinColumns = @JoinColumn(name = "fk_movie"),
            inverseJoinColumns = @JoinColumn(name = "fk_studio"))
    private Collection<Studio> studios;

    @ManyToMany
    @JoinTable(name = "movie_producer",
            joinColumns = @JoinColumn(name = "fk_movie"),
            inverseJoinColumns = @JoinColumn(name = "fk_producer"))
    private Collection<Producer> producers;

    private Movie() {
        super();
        this.setStudios(new ArrayList<>());
        this.setProducers(new ArrayList<>());
        this.setWinner(Boolean.FALSE);
    }

    public Movie(String title, Long year) {
        this();
        this.setTitle(title);
        this.setYear(year);

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    public Collection<Studio> getStudios() {
        return studios;
    }

    public void setStudios(Collection<Studio> studios) {
        this.studios = studios;
    }

    public Collection<Producer> getProducers() {
        return producers;
    }

    public void setProducers(Collection<Producer> producers) {
        this.producers = producers;
    }

    public void addProducer(Producer producer) {
        this.getProducers().add(producer);
    }

    public void addStudio(Studio studio) {
        this.getStudios().add(studio);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", year=" + year +
                ", winner=" + winner +
                ", studios=" + studios +
                ", producers=" + producers +
                '}';
    }
}
