package fr.utbm.mavenproject.service;

import fr.utbm.mavenproject.repository.CourseSessionDao;
import fr.utbm.mavenproject.entity.CourseSession;

import java.util.List;

/**
 *
 * @author quentinboudinot
 */
public class CourseSessionService {
    
    private static CourseSessionDao objCSDao;

    public CourseSessionService() {
        objCSDao = new CourseSessionDao();
    }

    public void create(CourseSession entity) {
        objCSDao.insert(entity);
    }

    public void update(CourseSession entity) {
        objCSDao.update(entity);
    }

    public CourseSession getCsById(int id) {
        return objCSDao.getCsById(id);
    }

    public void delete(int id) {
        CourseSession loc = objCSDao.getCsById(id);
        objCSDao.delete(loc);
    }

    public List<CourseSession> getAllCs() {
        return objCSDao.getAllCs();
    }
    
    //utilisé sur la home pour afficher les dernières courses sessions
    public List<CourseSession> getFourFirstCs() {
        return objCSDao.getFourFirstCs();
    }
    
    public List<CourseSession> getAllCsWithFilters(String whereClause) {
        return objCSDao.getAllCsWithFilters(whereClause);
    }

    public CourseSessionDao locationDao() {
        return objCSDao;
    }
}
