package org.example;

import org.example.crud.CrudCreate;
import org.example.crud.CrudRead;
import org.example.crud.CrudUpdate;
import org.example.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
    public static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CrudRead crudRead = new CrudRead(entityManager);
        List<Athlete> athletes = crudRead.getAthletesFromClub("CWKS Resovia Rzeszow");
//        System.out.println(zawodnicy.size());
//
//        for (Zawodnik zawodnik: zawodnicy)
//            System.out.println(zawodnik.toString());

//        List pr = entityManager.createQuery("FROM PersonalRecordsOutdoor").getResultList();
//        for (Object o: pr) {
//            System.out.println(o.toString());
//        }
//        for (Athlete athlete1: athletes)
//            System.out.println(athlete1.toString());
        CrudCreate crudCreate = new CrudCreate(entityManager);
        Meeting meeting = (Meeting) entityManager.createQuery("FROM Meeting m WHERE m.name = :name").setParameter("name", "20. Otwarte Mistrzostwa Przemysla").getResultList().get(0);
        Athlete athlete1 = (Athlete) entityManager.createQuery("FROM Athlete a WHERE a.firstname = :firstname AND a.lastname = :lastname")
                .setParameter("firstname", "Tomasz")
                .setParameter("lastname", "Paja").getResultList().get(0);
        Athlete athlete2 = (Athlete) entityManager.createQuery("FROM Athlete a WHERE a.firstname = :firstname AND a.lastname = :lastname")
                .setParameter("firstname", "Szymon")
                .setParameter("lastname", "Paja").getResultList().get(0);
        Athlete athlete3 = (Athlete) entityManager.createQuery("FROM Athlete a WHERE a.firstname = :firstname AND a.lastname = :lastname")
                .setParameter("firstname", "Michal")
                .setParameter("lastname", "Bosy").getResultList().get(0);
        Athlete athlete4 = (Athlete) entityManager.createQuery("FROM Athlete a WHERE a.firstname = :firstname AND a.lastname = :lastname")
                .setParameter("firstname", "Noah")
                .setParameter("lastname", "Lyles").getResultList().get(0);
        Athlete athlete5 = (Athlete) entityManager.createQuery("FROM Athlete a WHERE a.firstname = :firstname AND a.lastname = :lastname")
                .setParameter("firstname", "Usain")
                .setParameter("lastname", "Bolt").getResultList().get(0);
        Athlete athlete6 = (Athlete) entityManager.createQuery("FROM Athlete a WHERE a.firstname = :firstname AND a.lastname = :lastname")
                .setParameter("firstname", "Ferdinand")
                .setParameter("lastname", "Omanyala").getResultList().get(0);
        Coach coach = (Coach) entityManager.createQuery("FROM Coach c WHERE c.firstname = :firstname AND c.lastname = :lastname")
                .setParameter("firstname", "Piotr")
                .setParameter("lastname", "Kowalski").getResultList().get(0);
        Coach coach1 = (Coach) entityManager.createQuery("FROM Coach c WHERE c.firstname = :firstname AND c.lastname = :lastname")
                .setParameter("firstname", "Janusz")
                .setParameter("lastname", "Mazurczak").getResultList().get(0);

        Coach coach3 = (Coach) entityManager.createQuery("FROM Coach c WHERE c.firstname = :firstname AND c.lastname = :lastname")
                .setParameter("firstname", "Stanislaw")
                .setParameter("lastname", "Wazki").getResultList().get(0);

//        Athlete athlete = crudCreate.createAthlete("Oliwer", "Wdowiak", new Date(102, Calendar.NOVEMBER, 10),
//                "male", "Poland", "CWKS Resovia Rzeszow", List.of("sprints"), Map.of("100m", 10.23, "200m", 20.96),
//                Map.of("60m", 6.60), coach1);
//        System.out.println(meeting.toString());
//        System.out.println(athlete1.toString());
//        System.out.println(athlete2.toString());
//        System.out.println(coach.toString());
//        Report report = crudCreate.createReport(meeting, athlete1, coach, "800m W", false);
//        Report report1 = crudCreate.createReport(meeting, athlete1, coach, "800m M", false);
//        Report report2 = crudCreate.createReport(meeting, athlete2, coach, "800m M", true);
//        Report report3 = crudCreate.createReport(meeting, athlete2, coach, "200m M", false);
//        List<Report> reports = crudRead.getReportsOfAllAthletesParticipatingInMeeting(meeting);
//        System.out.println(reports.size());
//        for (Report report: reports)
//            System.out.println(report.toString());

//        CrudUpdate crudUpdate = new CrudUpdate(entityManager);
//        crudUpdate.addAthleteToCoach(athlete1, coach);
//        crudUpdate.addAthleteToCoach(athlete2, coach);
//        crudUpdate.addAthleteToCoach(athlete3, coach3);
//        crudUpdate.addAthleteToCoach(athlete4, coach2);
//        crudUpdate.addAthleteToCoach(athlete5, coach2);
//        crudUpdate.addAthleteToCoach(athlete6, coach2);

        System.out.println("Resovia athletes:");
        List<Athlete> athletesResovia = crudRead.getAthletesFromClub("CWKS Resovia Rzeszow");
        for (Athlete competition: athletesResovia)
            System.out.println(competition.toString());

        Coach coach2 = (Coach) entityManager.createQuery("FROM Coach c WHERE c.firstname = :firstname AND c.lastname = :lastname")
                .setParameter("firstname", "Bob")
                .setParameter("lastname", "Beamon").getResultList().get(0);

        System.out.println("\nBob Beamon's athletes:");
        List<Athlete> athletesBobBeamon = crudRead.getCoachesAthletes(coach2);
        for (Athlete competition: athletesBobBeamon)
            System.out.println(competition.toString());

        HibernateUtil.shutdown();
    }
}