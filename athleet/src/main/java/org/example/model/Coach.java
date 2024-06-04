package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Trenerzy")
public class Coach {
    @Id
    @Getter
    private ObjectId id;
    @Getter
    @Setter
    private String firstname;
    @Getter
    @Setter
    private String lastname;
    @Getter
    @Setter
    private String nationality;
    @Getter
    @Setter
    private String club;
    @Getter
    @Setter
    @ElementCollection
    private List<String> coaching;

    @Getter
    @Setter
    @OneToMany
    private List<Athlete> athletes = new ArrayList<>();

    public Coach() {

    }

    public Coach(String firstname, String lastname, String nationality, String club, List<String> coaching, List<Athlete> athletes) {
        this.id = new ObjectId();
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationality = nationality;
        this.club = club;
        this.coaching = coaching == null ? new ArrayList<>() : coaching;
        this.athletes = athletes == null ? new ArrayList<>() : athletes;
    }

    public void addAthlete(Athlete athlete) {
        this.athletes.add(athlete);
    }

    @Override
    public String toString() {
        return "Coach{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nationality='" + nationality + '\'' +
                ", club='" + club + '\'' +
                ", coaching=" + coaching +
                ", athletes=" + athletes +
                '}';
    }

    public String toStringWithoutAthletes() {
        return "Coach{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nationality='" + nationality + '\'' +
                ", club='" + club + '\'' +
                ", coaching=" + coaching +
                '}';
    }
}
