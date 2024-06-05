package org.example.crud;

import org.example.model.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CrudCreate {
    private final EntityManager entityManager;

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
        List<Meeting> allMeetings = new CrudRead(entityManager).getAllMeetings();
        for (Meeting meeting: allMeetings) {
            if (meeting.getName().equals(name))
                return meeting;
        }
        Meeting meeting = new Meeting(name, city, date, competitions);
        for (Competition competition : competitions) {
            if (competition.getId() == null) {
                entityManager.persist(competition);
            } else {
                entityManager.merge(competition);
            }
        }
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
        CrudRead crudRead = new CrudRead(entityManager);
        List<Meeting> meetings = crudRead.getAllMeetings();
        if (!meetings.contains(meeting)) {
            System.out.println("There is no such meeting in the database!");
            return null;
        }
        if (meeting.getDate().before(new Date())) {
            System.out.println("You cannot report an athlete for this meeting, because this meeting has already taken place!");
            return null;
        }
        List<Competition> competitions = crudRead.getAllMeetingCompetitions(meeting);
        int max_no_participants = -1;
        for (Competition competition: competitions) {
            if (competition.getDiscipline().equals(discipline)) {
                max_no_participants = competition.getMax_no_competitors();
                break;
            }
        }
        if (max_no_participants == -1) {
            System.out.println("There is no such discipline in this meeting!");
            return null;
        }
        if (crudRead.getReportsOfAllNotCancelledAthletesParticipatingInMeetingInDiscipline(meeting, discipline).size()
                >= max_no_participants) {
            System.out.println("There are no places left for this competition!");
            return null;
        }
        Report check = null;
        try {
            check = (Report) entityManager.createQuery(
                            "FROM Report r WHERE r.meeting = :meeting AND r.athlete = :athlete AND " +
                                    "r.coach = :coach AND r.discipline = :discipline")
                    .setParameter("meeting", meeting)
                    .setParameter("athlete", athlete)
                    .setParameter("coach", coach)
                    .setParameter("discipline", discipline)
                    .getSingleResult();
        } catch (NoResultException ignored) {

        }
        if (check != null) {
            System.out.println("The report of this athlete to this competition already exists!");
            return check;
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
         * Creates competition, if it is not already created, and returns it.
         */
        List<Competition> competitions = entityManager.createQuery("FROM Competition", Competition.class).getResultList();
        for (Competition competition: competitions) {
            if (competition.getDiscipline().equals(discipline) && competition.getMax_no_competitors() == max_no_competitors)
                return competition;
        }
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
