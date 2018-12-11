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
import javax.persistence.TypedQuery;

/**
 *
 * @author qboudino
 */
public class LocationDao 
{
    private static final String JPA_UNIT_NAME = "fr.utbm_mavenproject_jar_1.0-SNAPSHOTPU";
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory(JPA_UNIT_NAME).createEntityManager();
        }
        return entityManager;
    }
    
    public Location getLocationById(int locationId) {        
        TypedQuery<Location> query = getEntityManager().createQuery("select l from Location l where l.id =:id", Location.class);
        Location result = query.setParameter("id", locationId).getSingleResult();
        return result;
    }
    
    public List<Location> getLocations() {
        List<Location> locations = getEntityManager().createQuery("select l from Location l").getResultList();
        return locations;
    }
    
    public Location insert(Location l) {
           getEntityManager().getTransaction().begin();
           getEntityManager().persist(l);
           getEntityManager().getTransaction().commit();
           return l;
    }
    
    public void delete(Location l) {
        getEntityManager().getTransaction().begin();
        l = getEntityManager().merge(l);//<-Important
        getEntityManager().remove(l);
        getEntityManager().getTransaction().commit();
    }
    
    public Location update(Location l) {
        getEntityManager().getTransaction().begin();
        l = getEntityManager().merge(l);
        getEntityManager().getTransaction().commit();
        return l;
    }
}