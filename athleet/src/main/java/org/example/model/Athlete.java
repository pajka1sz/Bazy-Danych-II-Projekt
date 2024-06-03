package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Zawodnicy")
public class Athlete {
    @Getter
    @Id
    private ObjectId id;
    @Getter
    @Setter
    private String firstname;
    @Getter
    @Setter
    private String lastname;
    @Getter
    @Setter
    private Date birth_date;
    @Getter
    @Setter
    private String gender;
    @Getter
    @Setter
    private String nationality;
    @Getter
    @Setter
    private String category;
    @Getter
    @Setter
    private String club;
    @Getter
    @Setter
    @ElementCollection
    private List<String> specialities;
    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "PersonalRecordsOutdoor", joinColumns = @JoinColumn(name = "id"))
    @MapKeyColumn(name = "discipline")
    @Column(name = "record")
    private Map<String, Double> personalRecordsOutdoor;
    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "PersonalRecordsShortTrack", joinColumns = @JoinColumn(name = "id"))
    @MapKeyColumn(name = "discipline")
    @Column(name = "record")
    private Map<String, Double> personalRecordsShortTrack;

    @Getter
    @Setter
    @ManyToOne
    private Coach coach;

    public Athlete() {

    }

    public Athlete(ObjectId id, String firstname, String lastname, Date birth_date, String gender, String nationality, String category, String club, List<String> specialities, Map<String, Double> personalRecordsOutdoor, Map<String, Double> personalRecordsShortTrack, Coach coach) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth_date = birth_date;
        this.gender = gender;
        this.nationality = nationality;
        this.category = category;
        this.club = club;
        this.specialities = specialities;
        this.personalRecordsOutdoor = personalRecordsOutdoor;
        this.personalRecordsShortTrack = personalRecordsShortTrack;
        this.coach = coach;
    }

    public Athlete(ObjectId id, String firstname, String lastname, String gender, String nationality, Coach coach) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.nationality = nationality;
        this.coach = coach;
    }

    private String personalRecordsOutdoorToString() {
        if (personalRecordsOutdoor == null)
            return "{}";
        String result = "{";
        for (String discipline: personalRecordsOutdoor.keySet())
            result += discipline + ": " + personalRecordsOutdoor.get(discipline);
        result += "}";
        return result;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birth_date=" + birth_date +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", category='" + category + '\'' +
                ", club='" + club + '\'' +
                ", specialities=" + specialities +
                ", personalRecordsOutdoor=" + this.personalRecordsOutdoorToString() +
                ", personalRecordsShortTrack=" + personalRecordsShortTrack +
                ", coach=" + coach.toStringWithoutAthletes() +
                '}';
    }
}
