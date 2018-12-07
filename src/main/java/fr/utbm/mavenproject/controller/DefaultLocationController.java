/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.Location;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author qboudino
 */
public class DefaultLocationController 
{
    private static EntityManager em;
    
    public void createLocation(String city) 
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fr.utbm_mavenproject_jar_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Location loc = new Location();
        loc.setCity(city);
        em.persist(loc);
        em.getTransaction().commit();
    }
    
}
