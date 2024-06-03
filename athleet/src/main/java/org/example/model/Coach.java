package org.example.model;

import org.bson.types.ObjectId;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Coach {
    @Id
    private ObjectId id;
    private String firstname;
    private String lastname;
    private String nationality;
    private String club;
    @ElementCollection
    private List<String> coaching;

    public Coach() {

    }

    public Coach(ObjectId id, String firstname, String lastname, String nationality, String club, List<String> coaching) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationality = nationality;
        this.club = club;
        this.coaching = coaching;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public List<String> getCoaching() {
        return coaching;
    }

    public void setCoaching(List<String> coaching) {
        this.coaching = coaching;
    }
}
