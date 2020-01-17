package br.com.andrebaroni.raspberryawards.domain.service;

import br.com.andrebaroni.raspberryawards.application.query.AwardQuery;
import br.com.andrebaroni.raspberryawards.application.query.AwardWinnerQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AwardService implements Serializable {

    private final ProducerService producerService;

    @Autowired
    public AwardService(ProducerService producerService) {
        super();
        this.producerService = producerService;
    }

    public AwardQuery findWinnerInterval() {
        Collection<AwardWinnerQuery> awardWinnerQueries = this.getAwardWinners();

        return new AwardQuery(this.getMinInterval(awardWinnerQueries),
                this.getMaxInterval(awardWinnerQueries));
    }

    private Collection<AwardWinnerQuery> getAwardWinners() {
        return this.producerService.findAllByMoviesWinner()
                .stream()
                .map(AwardWinnerQuery::new)
                .filter(AwardWinnerQuery::isAwardValid)
                .sorted(Comparator.comparingLong(AwardWinnerQuery::getInterval))
                .distinct()
                .collect(Collectors.toList());
    }

    private Collection<AwardWinnerQuery> getMinInterval(Collection<AwardWinnerQuery> awardWinners) {
        Optional<AwardWinnerQuery> minIntervalWinner = awardWinners
                .stream().min(Comparator.comparingLong(AwardWinnerQuery::getInterval));

        return this.filterIntervalWinner(awardWinners, minIntervalWinner);
    }

    private Collection<AwardWinnerQuery> getMaxInterval(Collection<AwardWinnerQuery> awardWinners) {
        Optional<AwardWinnerQuery> maxIntervalWinner = awardWinners
                .stream().max(Comparator.comparingLong(AwardWinnerQuery::getInterval));

        return this.filterIntervalWinner(awardWinners, maxIntervalWinner);
    }

    private Collection<AwardWinnerQuery> filterIntervalWinner(Collection<AwardWinnerQuery> awardWinners,
                                                              Optional<AwardWinnerQuery> intervalWinner) {
        return intervalWinner.<Collection<AwardWinnerQuery>>map(awardWinnerQuery1 -> awardWinners
                .stream()
                .filter(awardWinnerQuery -> awardWinnerQuery.getInterval().equals(awardWinnerQuery1.getInterval()))
                .collect(Collectors.toList())).orElseGet(ArrayList::new);

    }
}
