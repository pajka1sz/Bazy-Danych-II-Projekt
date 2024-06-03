package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
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
    @ElementCollection
    private List<Competition> competitions;

    public Meeting() {

    }

    public Meeting(ObjectId id, String name, String city, Date date, List<Competition> competitions) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.date = date;
        this.competitions = competitions;
    }

    public void addCompetition(Competition competition) {
        this.competitions.add(competition);
    }
}
