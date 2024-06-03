package org.example;

import org.example.crud.CrudRead;
import org.example.model.Athlete;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Objects;

public class Main {
//    private static final String URL = "mongodb://localhost:27017";
//    private static final String databaseName = "athleet";
//    private static MongoClient mongoClient = MongoClients.create(URL);
//    public static MongoDatabase db = mongoClient.getDatabase(databaseName);
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
    public static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {
//        try {
//            CrudRead.printResults(CrudRead.getAthletesInThisMeetingInThisCompetition(new ObjectId("66169321f5eb4896aa16c9b6"), "1500m M"));
//            CrudRead.printResults(CrudRead.getAthletesInThisMeetingInThisCompetition(new ObjectId("66169321f5eb4896aa16c9b6"), "100m M"));
//            CrudRead.printResults(CrudRead.getAllAthletesInThisMeeting(new ObjectId("66169321f5eb4896aa16c9b6")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (mongoClient != null)
//                mongoClient.close();
//        }
        //MongoDatabase db =

        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
//        Coach coach = new Coach(new ObjectId(), "Szymon", "Grabowski", "Poland", "CWKS Resovia Rzeszów", List.of("football"));
//        Athlete athlete = new Athlete(new ObjectId(), "Kamil", "Kopcio", "male", "Poland", coach);
//        coach.addAthlete(athlete);

        //        Athlete athlete = new Athlete();
//        athlete.setFirstname("Max");
//        athlete.setLastname("Verstappen");
//        athlete.setId(new ObjectId());
//        athlete.setCategory("senior");
//        athlete.setNationality("Netherlands");
        //entityManager.persist(athlete);
//        System.out.println(coach.toString());
//        entityManager.persist(coach);
        entityManager.getTransaction().commit();

        CrudRead crudRead = new CrudRead();
        List<Athlete> athletes = crudRead.getAthletesFromClub("CWKS Resovia Rzeszow");
//        System.out.println(zawodnicy.size());
//
//        for (Zawodnik zawodnik: zawodnicy)
//            System.out.println(zawodnik.toString());

        List pr = entityManager.createQuery("FROM PersonalRecordsOutdoor").getResultList();
        for (Object o: pr) {
            System.out.println(o.toString());
        }
//        for (Athlete athlete1: athletes)
//            System.out.println(athlete1.toString());

        entityManager.close();

        HibernateUtil.shutdown();
    }
}