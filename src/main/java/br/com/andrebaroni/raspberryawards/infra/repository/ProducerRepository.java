package br.com.andrebaroni.raspberryawards.infra.repository;

import br.com.andrebaroni.raspberryawards.domain.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, UUID> {

    Optional<Producer> findFirstByName(String name);

    @Query("select p from Producer p join p.movies m where m.winner = true order by p.name, m.year")
    Collection<Producer> findAllByMoviesWinner();

}
