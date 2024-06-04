package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class Competition {
    @Getter
    @Id
    private ObjectId id;
    @Getter
    @Setter
    private String discipline;
    @Getter
    @Setter
    private int max_no_competitors;

    public Competition() {

    }

    public Competition(String discipline, int max_no_competitors) {
        this.id = new ObjectId();
        this.discipline = discipline;
        this.max_no_competitors = max_no_competitors;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "discipline='" + discipline + '\'' +
                ", max_no_competitors=" + max_no_competitors +
                '}';
    }
}
