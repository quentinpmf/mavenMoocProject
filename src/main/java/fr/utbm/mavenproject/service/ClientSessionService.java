package fr.utbm.mavenproject.service;

import fr.utbm.mavenproject.repository.ClientSessionDao;
import fr.utbm.mavenproject.entity.ClientSession;
import fr.utbm.mavenproject.entity.CourseSession;

import java.util.List;

/**
 *
 * @author quentinboudinot
 */
public class ClientSessionService {
    
    private static ClientSessionDao objCSDao;

    public ClientSessionService() {
        objCSDao = new ClientSessionDao();
    }

    public void create(ClientSession entity) {
        objCSDao.insert(entity);
    }

    public void update(ClientSession entity) {
        objCSDao.update(entity);
    }
    
    public void delete(int id) {
        ClientSession cls = objCSDao.getById(id);
        objCSDao.delete(cls);
    }

    public List<ClientSession> getCourseClients() {
        return objCSDao.getCourseClients();
    }
    
    public List<ClientSession> getClientsByCourseCode(int courseCode) {
        return objCSDao.getClientsByCourseCode(courseCode);
    }
    
    public List<CourseSession> getCourseByClient(int clientId) {
        return objCSDao.getCourseByClient(clientId);
    }

    public ClientSessionDao locationDao() {
        return objCSDao;
    }
}
