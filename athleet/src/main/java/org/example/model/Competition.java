package org.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
public class Competition {
    @Getter
    @Setter
    private String discipline;
    @Getter
    @Setter
    private int max_no_competitors;

    public Competition() {

    }

    public Competition(String discipline, int max_no_competitors) {
        this.discipline = discipline;
        this.max_no_competitors = max_no_competitors;
    }
}
