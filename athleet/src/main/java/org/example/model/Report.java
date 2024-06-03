package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Report {
    @Getter
    @Id
    private ObjectId id;
    @Getter
    @Setter
    private ObjectId meetingId;
    @Getter
    @Setter
    private ObjectId athleteId;
    @Getter
    @Setter
    private String discipline;
    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private Date date;

    public Report() {

    }

    public Report(ObjectId id, ObjectId meetingId, ObjectId athleteId, String discipline, String status, Date date) {
        this.id = id;
        this.meetingId = meetingId;
        this.athleteId = athleteId;
        this.discipline = discipline;
        this.status = status;
        this.date = date;
    }
}
