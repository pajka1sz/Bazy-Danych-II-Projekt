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
        /**
         * Creates athlete and returns it.
         */
        Athlete athlete = new Athlete(firstname, lastname, birthDate, gender, nationality, club, specialities, personalRecordsOutdoor,
                personalRecordsShortTrack, coach);
        entityManager.persist(athlete);
        return athlete;
    }

    @Transactional
    public Coach createCoach(@NotNull String firstname, @NotNull String lastname, String nationality, @NotNull String club,
                             List<String> coaching, List<Athlete> athletes) {
        /**
         * Creates coach and returns it.
         */
        Coach coach = new Coach(firstname, lastname, nationality, club, coaching, athletes);
        entityManager.persist(coach);
        return coach;
    }

    @Transactional
    public Meeting createMeeting(@NotNull String name, @NotNull String city, @NotNull Date date, List<Competition> competitions) {
        /**
         * Creates meeting and returns it.
         */
        Meeting meeting = new Meeting(name, city, date, competitions);
        entityManager.persist(meeting);
        return meeting;
    }

    @Transactional
    public Report createReport(@NotNull Meeting meeting, @NotNull Athlete athlete, @NotNull Coach coach, @NotNull String discipline,
                               @NotNull String status) {
        /**
         * Returns report if it is possible to create, that is:
         * * status of the report is reported or confirmed (it cannot be cancelled right on the start),
         * * an athlete is being reported to the competition which is held for its gender,
         * * there is a place for an athlete in the competition
         *   (that is there are less than max_no_competitors athletes reported or confirmed).
         */
        if (status.equals("cancelled")) {
            System.out.println("You cannot create cancelled report!");
            return null;
        }
        if (!status.equals("reported") && !status.equals("confirmed")) {
            System.out.println("It is not a valid status! Available values are: reported, confirmed, cancelled.");
            return null;
        }
        if (!compareGenderAndCompetition(athlete, discipline)) {
            System.out.println("You cannot assign an athlete to discipline specified for another gender!");
            return null;
        }
        CrudRead crudRead = new CrudRead();
        List<Competition> competitions = crudRead.getAllMeetingCompetitions(meeting);
        int max_no_participants = 0;
        for (Competition competition: competitions) {
            if (competition.getDiscipline().equals(discipline)) {
                max_no_participants = competition.getMax_no_competitors();
                break;
            }
        }
        if (crudRead.getReportsOfAllNotCancelledAthletesParticipatingInMeetingInDiscipline(meeting, discipline).size()
                >= max_no_participants) {
            System.out.println("There are no places left for this competition!");
            return null;
        }
        Report report = new Report(meeting, athlete, coach, discipline, status, new Date());
        entityManager.persist(report);
        return report;
    }

    @Transactional
    public Competition createCompetition(@NotNull String discipline, int max_no_competitors) {
        /**
         * Creates competition and returns it. .
         */
        Competition competition = new Competition(discipline, max_no_competitors);
        entityManager.persist(competition);
        return competition;
    }

    private boolean compareGenderAndCompetition(Athlete athlete, String discipline) {
        return (athlete.getGender().equals("male") && discipline.endsWith("M"))
                || (athlete.getGender().equals("female") && discipline.endsWith("W"));
    }
}
