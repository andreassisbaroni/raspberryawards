package br.com.andrebaroni.raspberryawards.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class AwardService implements Serializable {

    private final MovieService movieService;

    @Autowired
    public AwardService(MovieService movieService) {
        super();
        this.movieService = movieService;
    }


}
