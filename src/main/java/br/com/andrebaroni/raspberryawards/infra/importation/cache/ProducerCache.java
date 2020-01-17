package br.com.andrebaroni.raspberryawards.infra.importation.cache;

import br.com.andrebaroni.raspberryawards.domain.entity.Producer;
import br.com.andrebaroni.raspberryawards.domain.service.ProducerService;
import br.com.andrebaroni.raspberryawards.infra.importation.EntityCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProducerCache extends EntityCache<Producer> {

    private final ProducerService producerService;

    @Autowired
    public ProducerCache(ProducerService producerService) {
        super();
        super.setShoudSaveIfNotFound(Boolean.TRUE);
        this.producerService = producerService;
    }

    @Override
    public Boolean isEquals(Producer cache, Producer filter) {
        return cache.getName().equals(filter.getName());
    }

    @Override
    protected Optional<Producer> findOneWithoutCache(Producer filter) {
        return this.producerService.findFirstByName(filter.getName());
    }

    @Override
    protected Producer save(Producer object) {
        return this.producerService.create(object);
    }


}
