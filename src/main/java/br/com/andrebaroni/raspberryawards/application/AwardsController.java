package br.com.andrebaroni.raspberryawards.application;

import br.com.andrebaroni.raspberryawards.domain.entity.Movie;
import br.com.andrebaroni.raspberryawards.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Collection;

@RestController
@RequestMapping("/api/awards")
public class AwardsController implements Serializable {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<Collection<Movie>> findAll() {
        return new ResponseEntity<>(this.movieService.findAll(), HttpStatus.OK);
    }
}
