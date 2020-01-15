package br.com.andrebaroni.raspberryawards.domain.service;

import br.com.andrebaroni.raspberryawards.domain.entity.Studio;
import br.com.andrebaroni.raspberryawards.infra.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Optional;

@Service
public class StudioService implements Serializable {

    private final StudioRepository studioRepository;

    @Autowired
    public StudioService(StudioRepository studioRepository) {
        super();
        this.studioRepository = studioRepository;
    }

    @Transactional
    public Studio create(Studio studio) {
        return this.studioRepository.save(studio);
    }

    public Optional<Studio> findFirstByName(String name) {
        return this.studioRepository.findFirstByName(name);
    }
}
