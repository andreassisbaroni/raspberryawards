package br.com.andrebaroni.raspberryawards.domain.service;

import br.com.andrebaroni.raspberryawards.domain.entity.Producer;
import br.com.andrebaroni.raspberryawards.infra.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Optional;

@Service
public class ProducerService implements Serializable {

    private final ProducerRepository producerRepository;

    @Autowired
    public ProducerService(ProducerRepository producerRepository) {
        super();
        this.producerRepository = producerRepository;
    }

    @Transactional
    public Producer create(Producer producer) {
        return this.producerRepository.save(producer);
    }

    public Optional<Producer> findFirstByName(String name) {
        return this.producerRepository.findFirstByName(name);
    }
}
