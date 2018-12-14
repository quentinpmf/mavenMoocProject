package fr.utbm.mavenproject.repository;
import fr.utbm.mavenproject.entity.ClientSession;
import fr.utbm.mavenproject.entity.ClientSession;
import fr.utbm.mavenproject.entity.CourseSession;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author quentinboudinot
 */
public class ClientSessionDao 
{
    private static final String JPA_UNIT_NAME = "fr.utbm_mavenproject_jar_1.0-SNAPSHOTPU";
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory(JPA_UNIT_NAME).createEntityManager();
        }
        return entityManager;
    }
    
    public ClientSession getById(int id) {
        TypedQuery<ClientSession> query = getEntityManager().createQuery("select c from ClientSession c where c.id= :id", ClientSession.class);
        ClientSession result = query.setParameter("id", id).getSingleResult();
        return result;
    }
    
    public List<ClientSession> getCourseClients() {
        List<ClientSession> courseSessionClients = getEntityManager().createQuery("select c from ClientSession c", ClientSession.class).getResultList();
        return courseSessionClients;
    }
    
    public List<ClientSession> getClientsByCourseCode(int courseCode) {
        TypedQuery<ClientSession> query = getEntityManager().createQuery("select c from ClientSession c where c.sessionId =:sessionId", ClientSession.class);
        List<ClientSession> result = query.setParameter("sessionId", courseCode).getResultList();
        return result;
    }
    
    public List<CourseSession> getCourseByClient(int clientId) {
        TypedQuery<CourseSession> query = getEntityManager().createQuery("select c from ClientSession c where c.clientId =:clientId", CourseSession.class);
        List<CourseSession> result = query.setParameter("clientId", clientId).getResultList();
        return result;
    }
    
    public ClientSession insert(ClientSession c) {
           getEntityManager().getTransaction().begin();
           getEntityManager().persist(c);
           getEntityManager().getTransaction().commit();
           return c;
    }

    public void delete(ClientSession c) {
        getEntityManager().getTransaction().begin();
        c = getEntityManager().merge(c);//<-Important
        getEntityManager().remove(c);
        getEntityManager().getTransaction().commit();
    }
    
    public ClientSession update(ClientSession c) {
        getEntityManager().getTransaction().begin();
        c = getEntityManager().merge(c);
        getEntityManager().getTransaction().commit();
        return c;
    }
}