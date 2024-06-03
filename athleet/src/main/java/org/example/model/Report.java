package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Report {
    @Getter
    @Id
    private ObjectId id;
    @Getter
    @Setter
    private Meeting meeting;
    @Getter
    @Setter
    private Athlete athlete;
    @Getter
    @Setter
    private Coach coach;
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

    public Report(Meeting meeting, Athlete athlete, Coach coach, String discipline, String status, Date date) {
        this.id = new ObjectId();
        this.meeting = meeting;
        this.athlete = athlete;
        this.coach = coach;
        this.discipline = discipline;
        this.status = status;
        this.date = date;
    }
}
