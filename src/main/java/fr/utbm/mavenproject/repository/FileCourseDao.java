/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.repository;

import fr.utbm.mavenproject.entity.Course;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author qboudino
 */
public class FileCourseDao 
{
    private static final String JPA_UNIT_NAME = "fr.utbm_mavenproject_jar_1.0-SNAPSHOTPU";
    private EntityManager entityManager;

    protected EntityManager getEntityManager() 
    {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory(JPA_UNIT_NAME).createEntityManager();
        }
        return entityManager;
    }
    
    /**
    * L'opération Read
    * @return tous les cours présents dans la base de données.
    */
    public List<Course> selectAll() {
        List<Course> courses = getEntityManager().createQuery("select c from COURSE c").getResultList();
        return courses;
    }
    
    /**
    * L'opération Create
    * @param c Le cours à insérer dans la base de données.
    * @return Le cours inséré
    */
    public Course insert(Course c) {
           getEntityManager().getTransaction().begin();
           getEntityManager().persist(c);
           getEntityManager().getTransaction().commit();
           return c;
    }
    
    /**
    * L'opération Delete
    * @param c Le cours à supprimer de la base de données
    */
    public void delete(Course c) {
        getEntityManager().getTransaction().begin();
        c = getEntityManager().merge(c);//<-Important
        getEntityManager().remove(c);
        getEntityManager().getTransaction().commit();
    }
    
    /**
    * L'opération Update
    * @param c Le cours à mettre à jour dans la base de données.
    * @return Le cours mise à jour
    */
    public Course update(Course c) {
        getEntityManager().getTransaction().begin();
        c = getEntityManager().merge(c);
        getEntityManager().getTransaction().commit();
        return c;
    }
}