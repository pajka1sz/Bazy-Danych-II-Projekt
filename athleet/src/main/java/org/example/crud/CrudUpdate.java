package org.example.crud;

import org.example.Main;
import org.example.model.*;

import javax.persistence.EntityManager;

public class CrudUpdate {
    private final EntityManager entityManager = Main.entityManager;

    public void addAthleteToCoach(Athlete athlete, Coach coach) {
        /**
         * @param athlete
         * @param coach
         * Adds athlete to specified coach.
         */
        coach.addAthlete(athlete);
    }

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
        report.setStatus(newStatus);
    }

    public void addCompetitionToMeeting(Meeting meeting, Competition competition) {
        /**
         * @param meeting
         * @param competition
         * Adds competition to specified meeting.
         */
        meeting.addCompetition(competition);
    }
}
