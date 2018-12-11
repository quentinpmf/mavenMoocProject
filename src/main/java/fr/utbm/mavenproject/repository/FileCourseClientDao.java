/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.repository;

import fr.utbm.mavenproject.entity.Client;
import fr.utbm.mavenproject.entity.CourseClient;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author qboudino
 */
public class FileCourseClientDao 
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
    * @return tous les sessions de cours présentes dans la base de données.
    */
    public List<CourseClient> selectAll() {
        List<CourseClient> courseSessionClients = getEntityManager().createQuery("select c from CourseClient c", CourseClient.class).getResultList();
        return courseSessionClients;
    }
    
    public boolean checkIfClientIsInCs(String clientId, String csId)
    {        
        System.out.println("AZAZAZAZAZAZAZ");
        Query query = getEntityManager().createNativeQuery("SELECT COUNT(*) FROM CourseClient WHERE clientId=14 AND csId=4", CourseClient.class);
        System.out.println("BLABLABLABLA");
        int count = (int) query.getSingleResult();
        
        if(count == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
    * L'opération Create
    * @param c La session de cours à insérer dans la base de données.
    * @return La session de cours insérée
    */
    public CourseClient insert(CourseClient c) {
           getEntityManager().getTransaction().begin();
           getEntityManager().persist(c);
           getEntityManager().getTransaction().commit();
           return c;
    }
    
    /**
    * L'opération Delete
    * @param c La session de cours à supprimer de la base de données
    */
    public void delete(CourseClient c) {
        getEntityManager().getTransaction().begin();
        c = getEntityManager().merge(c);//<-Important
        getEntityManager().remove(c);
        getEntityManager().getTransaction().commit();
    }
    
    /**
    * L'opération Update
    * @param c La session de cours à mettre à jour dans la base de données.
    * @return La session de cours mise à jour
    */
    public CourseClient update(CourseClient c) {
        getEntityManager().getTransaction().begin();
        c = getEntityManager().merge(c);
        getEntityManager().getTransaction().commit();
        return c;
    }
}