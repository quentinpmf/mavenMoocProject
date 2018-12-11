/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.Client;
import fr.utbm.mavenproject.entity.CourseSession;
import fr.utbm.mavenproject.entity.CourseClient;
import fr.utbm.mavenproject.repository.FileClientDao;
import fr.utbm.mavenproject.repository.FileCourseSessionDao;
import fr.utbm.mavenproject.repository.FileCourseClientDao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author qboudino
 */
public class DefaultCourseClientController 
{
    private static EntityManager em;
    
    public void createCourseSessionClient(String startDate, String endDate, String maxParticipant, String courseCode, String locationId, String image) 
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
        em.persist(cs);
        em.getTransaction().commit();
    }
    
    public boolean checkIfClientIsInCs(String clientId, String csId)
    {
        FileCourseClientDao fcscd = new FileCourseClientDao();
        boolean exists = fcscd.checkIfClientIsInCs(clientId,csId); //récupération du client
        return exists;
    }
}
