package org.example.crud;

import org.example.Main;
import org.example.model.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CrudCreate {
    private EntityManager entityManager = Main.entityManager;

    @Transactional
    public Athlete createAthlete(@NotNull String firstname, @NotNull String lastname, @NotNull Date birthDate, @NotNull String gender,
                                 String nationality, String club, List<String> specialities, Map<String, Double> personalRecordsOutdoor,
                              Map<String, Double> personalRecordsShortTrack, @NotNull Coach coach) {
        Athlete athlete = new Athlete(firstname, lastname, birthDate, gender, nationality, club, specialities, personalRecordsOutdoor,
                personalRecordsShortTrack, coach);
        entityManager.persist(athlete);
        return athlete;
    }

    @Transactional
    public Coach createCoach(@NotNull String firstname, @NotNull String lastname, String nationality, @NotNull String club,
                             List<String> coaching, List<Athlete> athletes) {
        Coach coach = new Coach(firstname, lastname, nationality, club, coaching, athletes);
        entityManager.persist(coach);
        return coach;
    }

    @Transactional
    public Meeting createMeeting(@NotNull String name, @NotNull String city, @NotNull Date date, List<Competition> competitions) {
        Meeting meeting = new Meeting(name, city, date, competitions);
        entityManager.persist(meeting);
        return meeting;
    }

    @Transactional
    public Report createReport(@NotNull Meeting meeting, @NotNull Athlete athlete, @NotNull Coach coach, @NotNull String discipline, @NotNull String status) {
        if (!status.equals("reported") && !status.equals("confirmed") && !status.equals("cancelled")) {
            System.out.println("It is not a valid status! Available values are: reported, confirmed, cancelled.");
            return null;
        }
        Report report = new Report(meeting, athlete, coach, discipline, status, new Date());
        entityManager.persist(report);
        return report;
    }

    @Transactional
    public Competition createCompetition(@NotNull String discipline, int max_no_competitors) {
        Competition competition = new Competition(discipline, max_no_competitors);
        entityManager.persist(competition);
        return competition;
    }
}
