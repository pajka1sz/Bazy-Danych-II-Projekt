package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Meeting {
    @Getter
    @Id
    private ObjectId id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private Date date;
    @Getter
    @Setter
    @Embedded
    @OneToMany
    private List<Competition> competitions;

    public Meeting() {

    }

    public Meeting(String name, String city, Date date, List<Competition> competitions) {
        this.id = new ObjectId();
        this.name = name;
        this.city = city;
        this.date = date;
        this.competitions = competitions == null ? new ArrayList<>() : competitions;
    }

    public void addCompetition(Competition competition) {
        this.competitions.add(competition);
    }

    public void removeCompetition(Competition competition) {
        this.competitions.remove(competition);
    }
}
