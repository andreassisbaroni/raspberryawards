package br.com.andrebaroni.raspberryawards.infra.importation.raspberryawards.cache;

import br.com.andrebaroni.raspberryawards.domain.entity.Producer;
import br.com.andrebaroni.raspberryawards.domain.service.ProducerService;
import br.com.andrebaroni.raspberryawards.infra.importation.EntityCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ProducerCache extends EntityCache<Producer> {

    private final ProducerService producerService;

    @Autowired
    public ProducerCache(ProducerService producerService) {
        super();
        this.producerService = producerService;
    }

    @Override
    public Producer findCache(Example<Producer> producerExample) {
        Optional<Producer> cachedProducer = super.getObjects()
                .stream()
                .filter(producer -> producer.getName().equals(producerExample.getProbe().getName()))
                .findFirst();

        if (cachedProducer.isPresent()) {
            return cachedProducer.get();
        } else {
            Producer persistedProducer = this.producerService.findFirstByName(producerExample.getProbe().getName())
                    .orElse(this.producerService.create(producerExample.getProbe()));

            if (Objects.nonNull(persistedProducer)) {
                super.addObject(persistedProducer);
            }

            return persistedProducer;
        }
    }
}
