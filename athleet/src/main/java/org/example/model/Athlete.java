package org.example.model;

import org.bson.types.ObjectId;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Zawodnicy")
public class Athlete {
    @Id
    private ObjectId id;
    private String firstname;
    private String lastname;
    private Date birth_date;
    private String gender;
    private String nationality;
    private String category;
    @ElementCollection
    private List<String> specialities;
    @ElementCollection
    @CollectionTable(name = "PersonalRecordsOutdoor", joinColumns = @JoinColumn(name = "_id"))
    @MapKeyColumn(name = "discipline")
    @Column(name = "record")
    private Map<String, Double> personalRecordsOutdoor;
    @ElementCollection
    @CollectionTable(name = "PersonalRecordsShortTrack", joinColumns = @JoinColumn(name = "_id"))
    @MapKeyColumn(name = "discipline")
    @Column(name = "record")
    private Map<String, Double> personalRecordsShortTrack;

    public Athlete() {

    }

    public Athlete(ObjectId id, String firstname, String lastname, Date birth_date, String gender, String nationality, String category, List<String> specialities, Map<String, Double> personalRecordsOutdoor, Map<String, Double> personalRecordsShortTrack) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth_date = birth_date;
        this.gender = gender;
        this.nationality = nationality;
        this.category = category;
        this.specialities = specialities;
        this.personalRecordsOutdoor = personalRecordsOutdoor;
        this.personalRecordsShortTrack = personalRecordsShortTrack;
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

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<String> specialities) {
        this.specialities = specialities;
    }

    public Map<String, Double> getPersonalRecordsOutdoor() {
        return personalRecordsOutdoor;
    }

    public void setPersonalRecordsOutdoor(Map<String, Double> personalRecordsOutdoor) {
        this.personalRecordsOutdoor = personalRecordsOutdoor;
    }

    public Map<String, Double> getPersonalRecordsShortTrack() {
        return personalRecordsShortTrack;
    }

    public void setPersonalRecordsShortTrack(Map<String, Double> personalRecordsShortTrack) {
        this.personalRecordsShortTrack = personalRecordsShortTrack;
    }
}
