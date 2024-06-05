package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Zawody")
public class Meeting {
    @Getter
    @Id
    private ObjectId id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private Date date;
    @Getter
    @Setter
    @OneToMany
    private List<Competition> competitions = new ArrayList<>();

    public Meeting() {

    }

    public Meeting(String name, String city, Date date, List<Competition> competitions) {
        this.id = new ObjectId();
        this.name = name;
        this.city = city;
        this.date = date;
        this.competitions = competitions == null ? new ArrayList<>() : competitions;
    }

    public void addCompetition(@NotNull Competition competition) {
        this.competitions.add(competition);
    }

    public void removeCompetition(@NotNull Competition competition) {
        this.competitions.remove(competition);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", date=" + date +
                ", competitions=" + competitionsToString() +
                '}';
    }

    private String competitionsToString() {
        if (competitions == null)
            return "{}";
        String result = "";
        for (Competition competition: competitions) {
            result += competition.toString() + ", ";
        }
        return result;
    }
}
