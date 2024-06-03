package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.persistence.*;

import java.util.*;

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
    private Date birthDate;
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

    public Athlete(String firstname, String lastname, Date birthDate, String gender, String nationality, String club, List<String> specialities, Map<String, Double> personalRecordsOutdoor, Map<String, Double> personalRecordsShortTrack, Coach coach) {
        this.id = new ObjectId();
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nationality = nationality;
        this.category = convertBirthDateToCategory(birthDate);
        this.club = club;
        this.specialities = specialities == null ? new ArrayList<>() : specialities;
        this.personalRecordsOutdoor = personalRecordsOutdoor == null ? new HashMap<>() : personalRecordsOutdoor;
        this.personalRecordsShortTrack = personalRecordsShortTrack == null ? new HashMap<>() : personalRecordsShortTrack;
        this.coach = coach;
    }

    public Athlete(String firstname, String lastname, String gender, String nationality, Coach coach) {
        this.id = new ObjectId();
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

    private String convertBirthDateToCategory(Date birthDate) {
        /**
         * @param birthDate
         * Function assign category to athlete depending on its birthdate.
         */
        Date currentDate = new Date();
        int differenceInYears = birthDate.getYear() - currentDate.getYear();
        if (differenceInYears < 16)
            return "Youngster";
        if (differenceInYears < 18)
            return "Younger junior";
        if (differenceInYears < 20)
            return "Junior";
        if (differenceInYears < 23)
            return "Youth";
        return "Senior";
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birth_date=" + birthDate +
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
