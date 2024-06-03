package org.example;

import com.mongodb.client.*;
import org.bson.types.ObjectId;

public class Main {
    private static final String URL = "mongodb://localhost:27017";
    private static final String databaseName = "athleet";
    private static MongoClient mongoClient = MongoClients.create(URL);
    public static MongoDatabase db = mongoClient.getDatabase(databaseName);
    public static void main(String[] args) {
        try {
            CrudRead.printResults(CrudRead.getAthletesInThisMeetingInThisCompetition(new ObjectId("66169321f5eb4896aa16c9b6"), "1500m M"));
            CrudRead.printResults(CrudRead.getAthletesInThisMeetingInThisCompetition(new ObjectId("66169321f5eb4896aa16c9b6"), "100m M"));
            CrudRead.printResults(CrudRead.getAllAthletesInThisMeeting(new ObjectId("66169321f5eb4896aa16c9b6")));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mongoClient != null)
                mongoClient.close();
        }
        //MongoDatabase db =

//        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        entityManager.getTransaction().begin();
//
//        String hql = "SELECT z FROM Zawodnik z";
//        List<Zawodnik> zawodnicy = entityManager.createQuery(hql, Zawodnik.class).getResultList();
//        System.out.println(zawodnicy.size());
//
//        for (Zawodnik zawodnik: zawodnicy)
//            System.out.println(zawodnik.toString());
//
//        entityManager.getTransaction().commit();
//        entityManager.close();
//
//        HibernateUtil.shutdown();
    }
}