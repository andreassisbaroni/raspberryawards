package br.com.andrebaroni.raspberryawards.infra.importation.cache;

import br.com.andrebaroni.raspberryawards.domain.entity.Studio;
import br.com.andrebaroni.raspberryawards.domain.service.StudioService;
import br.com.andrebaroni.raspberryawards.infra.importation.EntityCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudioCache extends EntityCache<Studio> {

    private final StudioService studioService;

    @Autowired
    public StudioCache(StudioService studioService) {
        super();
        super.setShoudSaveIfNotFound(Boolean.TRUE);
        this.studioService = studioService;
    }

    @Override
    public Boolean isEquals(Studio cache, Studio filter) {
        return cache.getName().equals(filter.getName());
    }

    @Override
    protected Optional<Studio> findOneWithoutCache(Studio filter) {
        return this.studioService.findFirstByName(filter.getName());
    }

    @Override
    protected Studio save(Studio object) {
        return this.studioService.create(object);
    }
}
