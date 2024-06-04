package org.example;

import org.example.crud.CrudCreate;
import org.example.crud.CrudDelete;
import org.example.crud.CrudRead;
import org.example.crud.CrudUpdate;
import org.example.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

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
}