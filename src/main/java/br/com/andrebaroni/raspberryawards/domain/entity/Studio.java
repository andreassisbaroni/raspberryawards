package br.com.andrebaroni.raspberryawards.domain.entity;

import javax.persistence.Entity;

@Entity
public class Studio extends Person {

    private Studio() {
        super();
    }

    public Studio(String name) {
        super(name);
    }
}
