package br.com.andrebaroni.raspberryawards.infra.importation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public abstract class EntityCache<T> implements Serializable {

    private Collection<T> objects;
    private Boolean shoudSaveIfNotFound;

    public EntityCache() {
        super();
        this.shoudSaveIfNotFound = Boolean.FALSE;
        this.initializeCache();
    }

    private void initializeCache() {
        this.objects = new ArrayList<>();
    }

    protected void setShoudSaveIfNotFound(Boolean shoudSaveIfNotFound) {
        this.shoudSaveIfNotFound = shoudSaveIfNotFound;
    }

    protected Collection<T> getObjects() {
        return this.objects;
    }

    protected void addObject(T object) {
        this.getObjects().add(object);
    }

    public T findOne(T filter) {
        Optional<T> cachedObject = this.filterOneCache(filter);

        if (cachedObject.isPresent()) {
            return cachedObject.get();
        } else {
            T databaseObject = this.findOneWithoutCache(filter)
                    .orElseGet(() -> {
                        if (this.shoudSaveIfNotFound) {
                            return this.save(filter);
                        } else {
                            return null;
                        }
                    });

            if (Objects.nonNull(databaseObject)) {
                this.addObject(databaseObject);
            }

            return databaseObject;
        }
    }

    public abstract Boolean isEquals(T cache, T filter);

    protected Optional<T> filterOneCache(T filter) {
        return this.getObjects()
                .stream()
                .filter(t -> this.isEquals(t, filter))
                .findFirst();
    }

    protected abstract Optional<T> findOneWithoutCache(T filter);

    protected abstract T save(T object);
}
