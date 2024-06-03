package org.example.model;

import org.bson.types.ObjectId;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Report {
    @Id
    private ObjectId id;
    private String meetingId;
    private String athleteId;
    private String discipline;
    private String status;
    private Date date;

    public Report() {

    }

    public Report(ObjectId id, String meetingId, String athleteId, String discipline, String status, Date date) {
        this.id = id;
        this.meetingId = meetingId;
        this.athleteId = athleteId;
        this.discipline = discipline;
        this.status = status;
        this.date = date;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
