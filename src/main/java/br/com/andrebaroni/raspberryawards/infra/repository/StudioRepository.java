package br.com.andrebaroni.raspberryawards.infra.repository;

import br.com.andrebaroni.raspberryawards.domain.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudioRepository extends JpaRepository<Studio, UUID> {

    Optional<Studio> findFirstByName(String name);
}
