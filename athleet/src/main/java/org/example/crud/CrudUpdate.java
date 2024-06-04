package org.example.crud;

import org.example.Main;
import org.example.model.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class CrudUpdate {
    private final EntityManager entityManager;

    public CrudUpdate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addAthleteToCoach(Athlete athlete, Coach coach) {
        /**
         * @param athlete
         * @param coach
         * Adds athlete to specified coach.
         */
        entityManager.getTransaction().begin();
        coach.addAthlete(athlete);
        athlete.setCoach(coach);
        entityManager.merge(coach);
        entityManager.merge(athlete);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void changeReportStatus(Report report, String newStatus) {
        /**
         * @param report
         * @param newStatus
         * Sets report status to new status.
         */
        if (report.getStatus().equals(newStatus)) {
            System.out.println("The status of report is already " + report.getStatus());
            return;
        }
        if (!newStatus.equals("reported") && !newStatus.equals("confirmed") && !newStatus.equals("cancelled")) {
            System.out.println("Wrong status type! The available ones are: reported, confirmed, cancelled");
            return;
        }
        entityManager.getTransaction().begin();
        report.setStatus(newStatus);
        entityManager.merge(report);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void addCompetitionToMeeting(Meeting meeting, Competition competition) {
        /**
         * @param meeting
         * @param competition
         * Adds competition to specified meeting.
         */
        entityManager.getTransaction().begin();
        meeting.addCompetition(competition);
        entityManager.merge(meeting);
        entityManager.getTransaction().commit();
    }
}
