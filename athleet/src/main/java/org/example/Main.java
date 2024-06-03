package org.example;

import com.mongodb.client.*;
import org.bson.types.ObjectId;
import org.example.model.Athlete;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main {
//    private static final String URL = "mongodb://localhost:27017";
//    private static final String databaseName = "athleet";
//    private static MongoClient mongoClient = MongoClients.create(URL);
//    public static MongoDatabase db = mongoClient.getDatabase(databaseName);
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
        Athlete athlete = new Athlete();
        athlete.setFirstname("Max");
        athlete.setLastname("Verstappen");
        athlete.setId(new ObjectId());
        athlete.setCategory("senior");
        athlete.setNationality("Netherlands");
        entityManager.persist(athlete);
        entityManager.getTransaction().commit();

        String hql = "SELECT z FROM Zawodnik z";
        List<Athlete> athletes = entityManager.createQuery("SELECT a FROM Athlete a", Athlete.class).getResultList();
//        System.out.println(zawodnicy.size());
//
//        for (Zawodnik zawodnik: zawodnicy)
//            System.out.println(zawodnik.toString());

        for (Athlete athlete1: athletes)
            System.out.println(athlete1.toString());

        entityManager.close();

        HibernateUtil.shutdown();
    }
}