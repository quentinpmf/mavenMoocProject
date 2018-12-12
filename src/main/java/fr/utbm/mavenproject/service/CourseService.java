package fr.utbm.mavenproject.service;

import fr.utbm.mavenproject.repository.CourseDao;
import fr.utbm.mavenproject.entity.Course;

import java.util.List;

/**
 *
 * @author quentinboudinot
 */
public class CourseService {
    
    private static CourseDao objCourseDao;

    public CourseService() {
        objCourseDao = new CourseDao();
    }

    public void create(Course entity) {
        objCourseDao.insert(entity);
    }

    public void update(Course entity) {
        objCourseDao.update(entity);
    }

    public Course getCourseByCode(int code) {
        return objCourseDao.getCourseByCode(code);
    }

    public void delete(int code) {
        Course course = objCourseDao.getCourseByCode(code);
        objCourseDao.delete(course);
    }

    public List<Course> getCourses() {
        return objCourseDao.getCourses();
    }

    public CourseDao objCourseDao() {
        return objCourseDao;
    }
}
