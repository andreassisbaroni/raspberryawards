package br.com.andrebaroni.raspberryawards.infra.importation.raspberryawards;

import br.com.andrebaroni.raspberryawards.domain.entity.Movie;
import br.com.andrebaroni.raspberryawards.domain.entity.Producer;
import br.com.andrebaroni.raspberryawards.domain.entity.Studio;
import br.com.andrebaroni.raspberryawards.domain.service.MovieService;
import br.com.andrebaroni.raspberryawards.infra.importation.CsvReader;
import br.com.andrebaroni.raspberryawards.infra.importation.raspberryawards.cache.ProducerCache;
import br.com.andrebaroni.raspberryawards.infra.importation.raspberryawards.cache.StudioCache;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RaspberryAwardsDataReader extends CsvReader<Movie> {

    private final MovieService movieService;
    private final StudioCache studioCache;
    private final ProducerCache producerCache;
    private final String filePath;

    @Autowired
    public RaspberryAwardsDataReader(MovieService movieService,
                                     ProducerCache producerCache,
                                     StudioCache studioCache,
                                     Environment environment) {
        super();
        this.movieService = movieService;
        this.producerCache = producerCache;
        this.studioCache = studioCache;
        this.filePath = environment.getProperty("raspberry-awards.data.filepath");
    }

    @Override
    protected String getFilePath() {
        return this.filePath;
    }

    @Override
    protected char getSeparator() {
        return ';';
    }

    @Override
    protected Movie convertObject(String[] stringMovie) {
        Movie movie = new Movie(stringMovie[TITLE_COLUMN], Long.parseLong(stringMovie[YEAR_COLUMN]));

        this.convertProducers(stringMovie[PRODUCERS_COLUMN])
                .forEach(producer -> movie.addProducer(this.producerCache.findCache(Example.of(producer))));

        this.convertStudios(stringMovie[STUDIOS_COLUMN])
                .forEach(studio -> movie.addStudio(this.studioCache.findCache(Example.of(studio))));

        if (Strings.isNotBlank(stringMovie[WINNER_COLUMN])) {
            movie.setWinner(Boolean.TRUE);
        }

        return movie;
    }

    @Override
    protected void processItem(Movie movie) {
        this.movieService.create(movie);
    }

    private Collection<Studio> convertStudios(String studios) {
        return Arrays.stream(studios.split(","))
                .map(Studio::new)
                .collect(Collectors.toList());
    }

    private Collection<Producer> convertProducers(String producers) {
        return Arrays.stream(producers.split(","))
                .map(Producer::new)
                .collect(Collectors.toList());
    }
}
