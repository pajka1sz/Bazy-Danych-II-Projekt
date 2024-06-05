package org.example;

import org.example.crud.CrudCreate;
import org.example.crud.CrudDelete;
import org.example.crud.CrudRead;
import org.example.crud.CrudUpdate;
import org.example.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CrudRead crudRead = new CrudRead(entityManager);
        CrudCreate crudCreate = new CrudCreate(entityManager);
        CrudUpdate crudUpdate = new CrudUpdate(entityManager);
        CrudDelete crudDelete = new CrudDelete(entityManager);

        HibernateUtil.shutdown();
    }

    private static Athlete getAthlete(EntityManager entityManager, String firstname, String lastname) {
        Athlete athlete = null;
        try {
            athlete = (Athlete) entityManager.createQuery(
                            "FROM Athlete a WHERE a.firstname = :firstname AND a.lastname = :lastname")
                    .setParameter("firstname", firstname)
                    .setParameter("lastname", lastname)
                    .getSingleResult();
        } catch (NoResultException ignored) {

        }
        return athlete;
    }

    private static Meeting getMeeting(EntityManager entityManager, String meetingName) {
        Meeting meeting = null;
        try {
            meeting = (Meeting) entityManager.createQuery(
                            "FROM Meeting m WHERE m.name = :name")
                    .setParameter("name", meetingName)
                    .getSingleResult();
        } catch (NoResultException ignored) {

        }
        return meeting;
    }

    private static Report getReport(EntityManager entityManager, Meeting meeting, Athlete athlete,
                             Coach coach, String discipline) {
        Report result = null;
        try {
            result = (Report) entityManager.createQuery(
                            "FROM Report r WHERE r.meeting = :meeting AND r.athlete = :athlete AND " +
                                    "r.coach = :coach AND r.discipline = :discipline")
                    .setParameter("meeting", meeting)
                    .setParameter("athlete", athlete)
                    .setParameter("coach", coach)
                    .setParameter("discipline", discipline)
                    .getSingleResult();
        } catch (NoResultException ignored) {

        }
        return result;
    }

    private static Coach getCoach(EntityManager entityManager, String firstname, String lastname) {
        Coach coach = null;
        try {
            coach = (Coach) entityManager.createQuery(
                            "FROM Coach c WHERE c.firstname = :firstname AND c.lastname = :lastname")
                    .setParameter("firstname", firstname)
                    .setParameter("lastname", lastname)
                    .getSingleResult();
        } catch (NoResultException ignored) {

        }
        return coach;
    }

    private static Competition getCompetition(EntityManager entityManager, String discipline, int max_no_competitors) {
        Competition competition = null;
        try {
            competition = (Competition) entityManager.createQuery(
                    "FROM Competition c WHERE c.discipline = :discipline AND c.max_no_competitors = :max_no_competitors")
                    .setParameter("discipline", discipline)
                    .setParameter("max_no_competitors", max_no_competitors)
                    .getSingleResult();
        } catch (NoResultException ignored) {

        }
        return competition;
    }
}