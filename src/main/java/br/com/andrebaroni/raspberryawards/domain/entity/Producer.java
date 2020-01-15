package br.com.andrebaroni.raspberryawards.domain.entity;

import javax.persistence.Entity;

@Entity
public class Producer extends Person {

    private Producer() {
        super();
    }

    public Producer(String name) {
        super(name);
    }
}
