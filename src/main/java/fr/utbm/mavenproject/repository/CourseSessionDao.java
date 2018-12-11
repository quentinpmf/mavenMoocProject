/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.repository;

import fr.utbm.mavenproject.entity.Course;
import fr.utbm.mavenproject.entity.CourseSession;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author qboudino
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
        List<CourseSession> courseSessions = getEntityManager().createQuery("select c from CourseSession c").getResultList();
        return courseSessions;
    }
    
    public List<CourseSession> getFourFirstCs() {
        /* TODO QUENTIN 
        String query="SELECT * FROM COURSE_SESSION ORDER BY ID DESC";
        Connection conn=DriverManager.getConnection(url, username, password);
        Statement stmt=conn.createStatement();
        stmt.setMaxRows(4);
        */
        List<CourseSession> courseSessions = getEntityManager().createQuery("select c from CourseSession").getResultList(); 
        return courseSessions;
    }
    
    public List<CourseSession> getAllCsWithFilters(String whereClause) {
        List<CourseSession> courseSessions = getEntityManager().createQuery("from CourseSession as cs INNER JOIN Course as c "+whereClause).getResultList();
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
        c = getEntityManager().merge(c);//<-Important
        getEntityManager().remove(c);
        getEntityManager().getTransaction().commit();
    }
    
    public CourseSession update(CourseSession c) {
        getEntityManager().getTransaction().begin();
        c = getEntityManager().merge(c);
        getEntityManager().getTransaction().commit();
        return c;
    }
    
    //################################################################
    
    public List<CourseSession> selectAllWithFilters(String param_title, String param_date, String param_location) 
    {
        String whereClause = "";
        
        //Si il y à les 3 paramètres
        if(!"".equals(param_title) && !"".equals(param_date) && !"".equals(param_location))
        {
            whereClause = "WHERE c.title LIKE '%"+param_title+"%' AND cs.startDate="+param_date+"AND cs.locationId ="+param_location;
        }
        else
        {
            //si il y à deux parametres : title et date
            if(!"".equals(param_title) && !"".equals(param_date))
            {
                whereClause = "WHERE c.title LIKE '%"+param_title+"%' AND cs.startDate="+param_date;
            }
            else
            {
                //si il y à deux parametres : title et location
                if(!"".equals(param_title) && !"".equals(param_location))
                {
                    whereClause = "WHERE c.title LIKE '%"+param_title+"%' AND cs.locationId ="+param_location;
                }
                else
                {
                    //si il y à deux parametres : date et location
                    if(!"".equals(param_date) && !"".equals(param_location))
                    {
                        whereClause = "WHERE cs.startDate="+param_date+" AND cs.locationId ="+param_location;
                    }
                    else
                    {
                        //si il n'y à qu'un parametre : title
                        if(!"".equals(param_title))
                        {
                            whereClause = "WHERE c.title LIKE '%"+param_title+"%'";
                        }
                        else
                        {
                            //si il n'y à qu'un parametre : date
                            if(!"".equals(param_date))
                            {
                                whereClause = "WHERE cs.startDate="+param_date;
                            }
                            else
                            {
                                //si il n'y à qu'un parametre : location
                                if(!"".equals(param_location))
                                {
                                    whereClause = "WHERE cs.locationId="+param_location;
                                }
                            }
                        }
                    }
                }
            }
        }

        //éxécution de la requête
        if(!"".equals(whereClause))
        {
            CourseSessionDao fcsd = new CourseSessionDao();
            List<CourseSession> csList = fcsd.selectAllWithFilters(whereClause);
            
        }
        else
        {
            List<CourseSession> csList = new ArrayList();
        }
        return csList;
    }
}