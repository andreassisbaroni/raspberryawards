package br.com.andrebaroni.raspberryawards.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Producer extends Person {

    @ManyToMany(mappedBy = "producers")
    Collection<Movie> movies;

    private Producer() {
        super();
    }

    public Producer(String name) {
        super(name);
    }

    public Collection<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Collection<Movie> movies) {
        this.movies = movies;
    }
}
