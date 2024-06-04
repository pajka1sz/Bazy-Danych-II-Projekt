package org.example.crud;

import org.example.Main;
import org.example.model.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class CrudDelete {
    private final EntityManager entityManager;

    public CrudDelete(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void deleteReport(@NotNull Report report) {
        Report foundReport = entityManager.find(Report.class, report.getId());
        if (foundReport != null) {
            System.out.println("There is no such report in the database!");
            entityManager.getTransaction().begin();
            entityManager.remove(foundReport);
            entityManager.getTransaction().commit();
        }
    }

    @Transactional
    public void deleteMeeting(@NotNull Meeting meeting) {
        Meeting foundMeeting = entityManager.find(Meeting.class, meeting.getId());
        if (foundMeeting != null) {
            System.out.println("There is no such meeting in the database!");
            entityManager.getTransaction().begin();
            entityManager.remove(foundMeeting);
            entityManager.getTransaction().commit();
        }
    }

    @Transactional
    public void deleteAthlete(@NotNull Athlete athlete) {
        Athlete foundAthlete = entityManager.find(Athlete.class, athlete.getId());
        if (foundAthlete != null) {
            entityManager.getTransaction().begin();
            athlete.getCoach().getAthletes().remove(athlete);
            entityManager.remove(foundAthlete);
            entityManager.getTransaction().commit();
        }
        else
            System.out.println("There is no such athlete in the database!");
    }

    @Transactional
    public void deleteCoach(@NotNull Coach coach) {
        Coach foundCoach = entityManager.find(Coach.class, coach.getId());
        if (foundCoach != null) {
            entityManager.getTransaction().begin();
            for (Athlete athlete: coach.getAthletes()) {
                athlete.setCoach(null);
                entityManager.merge(athlete);
            }
            entityManager.remove(foundCoach);
            entityManager.getTransaction().commit();
        }
        else
            System.out.println("There is no such coach in the database!");
    }

    @Transactional
    public void removeCompetitionFromMeeting(@NotNull Meeting meeting, @NotNull Competition competition) {
        Meeting foundMeeting = entityManager.find(Meeting.class, meeting.getId());
        if (foundMeeting == null) {
            System.out.println("There is no such meeting in the database!");
            return;
        }

        if (meeting.getCompetitions().contains(competition)) {
            entityManager.getTransaction().begin();
            meeting.removeCompetition(competition);
            entityManager.merge(meeting);
            entityManager.getTransaction().commit();
        }
        else
            System.out.println("There is no such competition planned in provided meeting.");
    }
}
