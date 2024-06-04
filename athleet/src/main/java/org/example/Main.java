package org.example;

import org.example.crud.CrudCreate;
import org.example.crud.CrudRead;
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

//        CrudRead crudRead = new CrudRead();
//        List<Athlete> athletes = crudRead.getAthletesFromClub("CWKS Resovia Rzeszow");
////        System.out.println(zawodnicy.size());
////
////        for (Zawodnik zawodnik: zawodnicy)
////            System.out.println(zawodnik.toString());
//
////        List pr = entityManager.createQuery("FROM PersonalRecordsOutdoor").getResultList();
////        for (Object o: pr) {
////            System.out.println(o.toString());
////        }
////        for (Athlete athlete1: athletes)
////            System.out.println(athlete1.toString());
//        CrudCreate crudCreate = new CrudCreate(entityManager);
//        Report report = crudCreate.createReport(entityManager.createQuery("FROM Meeting m WHERE m.name = :name")
//                .setParameter("name", "20. Otwarte Mistrzostwa Przemysla").getResultList().get(0), )

        HibernateUtil.shutdown();
    }
}