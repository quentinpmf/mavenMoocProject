/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.repository;

import fr.utbm.mavenproject.entity.Location;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author qboudino
 */
public class FileLocationDao 
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
    * @return toutes les villes présents dans la base de données.
    */
    public List<Location> selectAll() {
        List<Location> locations = getEntityManager().createQuery("select l from LOCATION l").getResultList();
        return locations;
    }
    
    /**
    * L'opération Create
    * @param l La ville à insérer dans la base de données.
    * @return La ville insérée
    */
    public Location insert(Location l) {
           getEntityManager().getTransaction().begin();
           getEntityManager().persist(l);
           getEntityManager().getTransaction().commit();
           return l;
    }
    
    /**
    * L'opération Delete
    * @param l La ville à supprimer de la base de données
    */
    public void delete(Location l) {
        getEntityManager().getTransaction().begin();
        l = getEntityManager().merge(l);//<-Important
        getEntityManager().remove(l);
        getEntityManager().getTransaction().commit();
    }
    
    /**
    * L'opération Update
    * @param l La ville à mettre à jour dans la base de données.
    * @return La ville mise à jour
    */
    public Location update(Location l) {
        getEntityManager().getTransaction().begin();
        l = getEntityManager().merge(l);
        getEntityManager().getTransaction().commit();
        return l;
    }
}