package org.example;

import org.example.crud.CrudRead;
import org.example.model.Athlete;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {
    private static EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
    public static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CrudRead crudRead = new CrudRead();
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

        entityManager.close();

        HibernateUtil.shutdown();
    }
}