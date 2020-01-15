package br.com.andrebaroni.raspberryawards.infra.importation.cache;

import br.com.andrebaroni.raspberryawards.domain.entity.Studio;
import br.com.andrebaroni.raspberryawards.domain.service.StudioService;
import br.com.andrebaroni.raspberryawards.infra.importation.EntityCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class StudioCache extends EntityCache<Studio> {

    private final StudioService studioService;

    @Autowired
    public StudioCache(StudioService studioService) {
        super();
        this.studioService = studioService;
    }

    @Override
    public Studio findCache(Example<Studio> producerExample) {
        Optional<Studio> cachedProducer = super.getObjects()
                .stream()
                .filter(producer -> producer.getName().equals(producerExample.getProbe().getName()))
                .findFirst();

        if (cachedProducer.isPresent()) {
            return cachedProducer.get();
        } else {
            Studio persistedProducer = this.studioService.findFirstByName(producerExample.getProbe().getName())
                    .orElse(this.studioService.create(producerExample.getProbe()));

            if (Objects.nonNull(persistedProducer)) {
                super.addObject(persistedProducer);
            }

            return persistedProducer;
        }
    }
}
