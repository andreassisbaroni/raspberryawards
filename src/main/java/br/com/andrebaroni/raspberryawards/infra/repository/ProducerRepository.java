package br.com.andrebaroni.raspberryawards.infra.repository;

import br.com.andrebaroni.raspberryawards.domain.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, UUID> {

    Optional<Producer> findFirstByName(String name);

}
