package org.example.model;

import javax.persistence.Embeddable;

@Embeddable
public class Competition {
    private String discipline;
    private int max_no_competitors;

    public Competition() {

    }

    public Competition(String discipline, int max_no_competitors) {
        this.discipline = discipline;
        this.max_no_competitors = max_no_competitors;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public int getMax_no_competitors() {
        return max_no_competitors;
    }

    public void setMax_no_competitors(int max_no_competitors) {
        this.max_no_competitors = max_no_competitors;
    }
}
