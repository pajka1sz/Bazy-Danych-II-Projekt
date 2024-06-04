package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Zgloszenia")
public class Report {
    @Getter
    @Id
    private ObjectId id;
    @Getter
    @Setter
    @ManyToOne
    private Meeting meeting;
    @Getter
    @Setter
    @ManyToOne
    private Athlete athlete;
    @Getter
    @Setter
    @ManyToOne
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

    public Report(Meeting meeting, Athlete athlete, Coach coach, String discipline, boolean isConfirmed, Date date) {
        this.id = new ObjectId();
        this.meeting = meeting;
        this.athlete = athlete;
        this.coach = coach;
        this.discipline = discipline;
        this.status = isConfirmed ? "confirmed" : "reported";
        this.date = date;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", meeting=" + meeting +
                ", athlete=" + athlete +
                ", coach=" + coach +
                ", discipline='" + discipline + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
}
