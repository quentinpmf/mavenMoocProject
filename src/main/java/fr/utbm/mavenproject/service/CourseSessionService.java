/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.service;

import fr.utbm.mavenproject.entity.CourseSession;
import fr.utbm.mavenproject.repository.FileCourseSessionDao;

/**
 *
 * @author qboudino
 */
public class CourseSessionService {
    
    public void registerCourseSession(CourseSession cs) {
        FileCourseSessionDao fcsd = new FileCourseSessionDao();
    }
    
}
