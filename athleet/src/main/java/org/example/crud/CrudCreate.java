package org.example.crud;

import org.example.model.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CrudCreate {
    private EntityManager entityManager;

    public CrudCreate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Athlete createAthlete(@NotNull String firstname, @NotNull String lastname, @NotNull Date birthDate, @NotNull String gender,
                                 String nationality, String club, List<String> specialities, Map<String, Double> personalRecordsOutdoor,
                              Map<String, Double> personalRecordsShortTrack, @NotNull Coach coach) {
        /**
         * Creates athlete and returns it.
         */
        Athlete athlete = new Athlete(firstname, lastname, birthDate, gender, nationality, club, specialities, personalRecordsOutdoor,
                personalRecordsShortTrack, coach);
        coach.addAthlete(athlete);
        entityManager.getTransaction().begin();
        entityManager.persist(athlete);
        entityManager.merge(coach);
        entityManager.getTransaction().commit();
        return athlete;
    }

    @Transactional
    public Coach createCoach(@NotNull String firstname, @NotNull String lastname, String nationality, @NotNull String club,
                             List<String> coaching, List<Athlete> athletes) {
        /**
         * Creates coach and returns it.
         */
        Coach coach = new Coach(firstname, lastname, nationality, club, coaching, athletes);
        entityManager.getTransaction().begin();
        entityManager.persist(coach);
        entityManager.getTransaction().commit();
        return coach;
    }

    @Transactional
    public Meeting createMeeting(@NotNull String name, @NotNull String city, @NotNull Date date, List<Competition> competitions) {
        /**
         * Creates meeting and returns it.
         */
        Meeting meeting = new Meeting(name, city, date, competitions);
        entityManager.getTransaction().begin();
        entityManager.persist(meeting);
        entityManager.getTransaction().commit();
        return meeting;
    }

    @Transactional
    public Report createReport(@NotNull Meeting meeting, @NotNull Athlete athlete, @NotNull Coach coach, @NotNull String discipline,
                               @NotNull boolean isConfirmed) {
        /**
         * Returns report if it is possible to create, that is:
         * * status of the report is reported or confirmed (it cannot be cancelled right on the start),
         * * an athlete is being reported to the competition which is held for its gender,
         * * there is a place for an athlete in the competition
         *   (that is there are less than max_no_competitors athletes reported or confirmed).
         */
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
        Report report = new Report(meeting, athlete, coach, discipline, isConfirmed, new Date());
        entityManager.getTransaction().begin();
        entityManager.persist(report);
        entityManager.getTransaction().commit();
        return report;
    }

    @Transactional
    public Competition createCompetition(@NotNull String discipline, int max_no_competitors) {
        /**
         * Creates competition and returns it. .
         */
        Competition competition = new Competition(discipline, max_no_competitors);
        entityManager.getTransaction().begin();
        entityManager.persist(competition);
        entityManager.getTransaction().commit();
        return competition;
    }

    private boolean compareGenderAndCompetition(Athlete athlete, String discipline) {
        return (athlete.getGender().equals("male") && discipline.endsWith("M"))
                || (athlete.getGender().equals("female") && discipline.endsWith("W"));
    }
}
