package br.com.andrebaroni.raspberryawards.infra.importation;

import org.springframework.data.domain.Example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public abstract class EntityCache<T> implements Serializable {

    private Collection<T> objects;

    public EntityCache() {
        super();
        this.initializeCache();
    }

    private void initializeCache() {
        this.objects = new ArrayList<>();
    }

    protected Collection<T> getObjects() {
        return this.objects;
    }

    protected void addObject(T object) {
        this.getObjects().add(object);
    }

    public abstract T findCache(Example<T> objectExample);

}
