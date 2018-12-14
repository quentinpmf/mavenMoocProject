package fr.utbm.mavenproject.repository;

import fr.utbm.mavenproject.entity.Client;
import fr.utbm.mavenproject.entity.CourseSession;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author quentinboudinot
 */
public class CourseSessionDao 
{
    private static final String JPA_UNIT_NAME = "fr.utbm_mavenproject_jar_1.0-SNAPSHOTPU";
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory(JPA_UNIT_NAME).createEntityManager();
        }
        return entityManager;
    }
    
    public CourseSession getCsById(int id) {
        TypedQuery<CourseSession> query = getEntityManager().createQuery("select c from CourseSession c where c.id= :id", CourseSession.class);
        CourseSession result = query.setParameter("id", id).getSingleResult();
        return result;
    }
    
    public List<CourseSession> getAllCs() {
        List<CourseSession> courseSessions = getEntityManager().createQuery("select c from CourseSession c ORDER BY c.id DESC").getResultList();
        return courseSessions;
    }
    
    public List<CourseSession> getFourFirstCs() {
        Query query = getEntityManager().createQuery("select c from CourseSession c ORDER BY c.id DESC", CourseSession.class); 
        query.setFirstResult(0);
        query.setMaxResults(4);
        List<CourseSession> courseSessions = query.getResultList();
        return courseSessions;
    }
    
    public List<CourseSession> getAllCsWithFilters(String whereClause) {
        List<CourseSession> courseSessions = getEntityManager().createQuery("from CourseSession cs, Course c "+whereClause).getResultList();
        return courseSessions; //TODO Quentin : à vérifier
    }
    
    public CourseSession insert(CourseSession c) {
           getEntityManager().getTransaction().begin();
           getEntityManager().persist(c);
           getEntityManager().getTransaction().commit();
           return c;
    }
    
    public void delete(CourseSession c) {
        getEntityManager().getTransaction().begin();
        c = getEntityManager().merge(c); //<-Important
        getEntityManager().remove(c);
        getEntityManager().getTransaction().commit();
    }
    
    public CourseSession update(CourseSession c) {
        getEntityManager().getTransaction().begin();
        c = getEntityManager().merge(c);
        getEntityManager().getTransaction().commit();
        return c;
    }
}