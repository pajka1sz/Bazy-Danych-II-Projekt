package org.example.model;

import org.bson.types.ObjectId;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.List;

@Entity
public class Meeting {
    @Id
    private ObjectId id;
    private String name;
    private String city;
    private Date date;
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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }
}
