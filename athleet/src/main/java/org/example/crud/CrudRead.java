package org.example.crud;

import org.example.Main;
import org.example.model.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CrudRead {
    private final EntityManager entityManager = Main.entityManager;
    public List<Athlete> getAllAthletes() {
        /**
         * Returns list of all athletes.
         */
        String query = "FROM Athlete";
        return entityManager.createQuery(query, Athlete.class).getResultList();
    }

    public List<Coach> getAllCoaches() {
        /**
         * Returns list of all coaches.
         */
        String query = "FROM Coach";
        return entityManager.createQuery(query, Coach.class).getResultList();
    }

    public List<Meeting> getAllMeetings() {
        /**
         * Returns list of all meetings.
         */
        String query = "FROM Meeting";
        return entityManager.createQuery(query, Meeting.class).getResultList();
    }

    public List<Report> getAllReports() {
        /**
         * Returns list of all reports.
         */
        String query = "FROM Report";
        return entityManager.createQuery(query, Report.class).getResultList();
    }

    public List<Competition> getAllMeetingCompetitions(@NotNull Meeting meeting) {
        /**
         * @param meeting
         * Returns list of all competitions in the meeting.
         */
        return meeting.getCompetitions();
    }

    public List<Athlete> getAthletesFromClub(@NotNull String club) {
        /**
         * @param club
         * Returns list of all athletes belonging to specified club.
         */
        List<Athlete> athletes = getAllAthletes();
        List<Athlete> results = athletes.stream().filter(a -> a.getClub() != null && a.getClub().equals(club)).toList();
        return results;
    }

    public List<Athlete> getCoachesAthletes(@NotNull Coach coach) {
        /**
         * @param coach
         * Returns list of all athletes who train with specified coach.
         */
        List<Athlete> athletes = getAllAthletes();
        List<Athlete> results = athletes.stream().filter(a -> a.getCoach() != null && a.getCoach().equals(coach)).toList();
        return results;
    }

    public List<Report> getReportsOfAllAthletesParticipatingInMeeting(@NotNull Meeting meeting) {
        /**
         * @param meeting
         * Returns all reports of athletes participating in the meeting.
         */
        List<Report> allReports = getAllReports();
        List<Report> results = new ArrayList<>();
        for (Report report: allReports) {
            if (report.getMeeting().equals(meeting))
                results.add(report);
        }
        return results;
    }

    public List<Report> getReportsOfAllAthletesParticipatingInMeetingInDiscipline(@NotNull Meeting meeting,
                                                                                  @NotNull Competition competition) {
        /**
         * @param meeting
         * @param competition
         * Returns all reports of athletes participating in the meeting in provided competition.
         */
        List<Report> allReportsFromThisMeeting = getReportsOfAllAthletesParticipatingInMeeting(meeting);
        List<Report> results = new ArrayList<>();
        for (Report report: allReportsFromThisMeeting) {
            if (report.getDiscipline().equals(competition.getDiscipline()))
                results.add(report);
        }
        return results;
    }
}