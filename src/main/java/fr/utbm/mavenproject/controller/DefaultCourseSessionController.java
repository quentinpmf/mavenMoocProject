/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.Client;
import fr.utbm.mavenproject.entity.CourseSession;
import fr.utbm.mavenproject.repository.FileClientDao;
import fr.utbm.mavenproject.repository.FileCourseSessionDao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author qboudino
 */
public class DefaultCourseSessionController 
{
    private static EntityManager em;
    
    public void createCourseSession(String startDate, String endDate, String maxParticipant, String courseCode, String locationId, String image) 
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fr.utbm_mavenproject_jar_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        CourseSession cs = new CourseSession();
        cs.setStartDate(startDate);
        cs.setEndDate(endDate);
        cs.setMaxParticipant(maxParticipant);
        cs.setCourseCode(courseCode);
        cs.setLocationId(locationId);
        if(image == null)
        {
            image = "http://image.noelshack.com/fichiers/2018/49/4/1544137044-formation8.jpg";
        }
        cs.setImageLink(image);
        em.persist(cs);
        em.getTransaction().commit();
    }
    
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
            FileCourseSessionDao fcsd = new FileCourseSessionDao();
            List<CourseSession> csList = fcsd.selectAllWithFilters(whereClause);
            
        }
        else
        {
            List<CourseSession> csList = new ArrayList();
        }
        return csList;
    }
}
