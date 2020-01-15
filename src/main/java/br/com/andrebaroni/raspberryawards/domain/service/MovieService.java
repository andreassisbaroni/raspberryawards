package br.com.andrebaroni.raspberryawards.domain.service;

import br.com.andrebaroni.raspberryawards.domain.entity.Movie;
import br.com.andrebaroni.raspberryawards.infra.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;

@Service
public class MovieService implements Serializable {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        super();
        this.movieRepository = movieRepository;
    }

    @Transactional
    public Movie create (Movie movie) {
        return this.movieRepository.save(movie);
    }

    public Collection<Movie> findAll() {
        return this.movieRepository.findAll();
    }
}
