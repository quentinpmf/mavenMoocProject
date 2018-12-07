/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.service;

import fr.utbm.mavenproject.entity.Course;
import fr.utbm.mavenproject.repository.FileCourseDao;

/**
 *
 * @author qboudino
 */
public class CourseService {
    
    public void registerCourse(Course cou) {
        FileCourseDao fcd = new FileCourseDao();
    }
    
}
