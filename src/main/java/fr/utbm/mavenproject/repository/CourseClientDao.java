package fr.utbm.mavenproject.repository;
import fr.utbm.mavenproject.entity.ClientSession;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author quentinboudinot
 */
public class CourseClientDao 
{
    private static final String JPA_UNIT_NAME = "fr.utbm_mavenproject_jar_1.0-SNAPSHOTPU";
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory(JPA_UNIT_NAME).createEntityManager();
        }
        return entityManager;
    }
    
    public List<ClientSession> getCourseClients() {
        List<ClientSession> courseSessionClients = getEntityManager().createQuery("select c from CourseClient c", ClientSession.class).getResultList();
        return courseSessionClients;
    }
    
    public List<ClientSession> getClientsByCourseCode(int courseCode) {
        TypedQuery<ClientSession> query = getEntityManager().createQuery("select c from CourseClient c where c.csId =:csId", ClientSession.class);
        List<ClientSession> result = query.setParameter("csId", courseCode).getResultList();
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
    
    //#########################################################
    
    public boolean checkIfClientIsInCs(String clientId, String csId) {        
        System.out.println("AZAZAZAZAZAZAZ");
        Query query = getEntityManager().createNativeQuery("SELECT COUNT(*) FROM CourseClient WHERE clientId=14 AND csId=4", ClientSession.class);
        System.out.println("BLABLABLABLA");
        int count = (int) query.getSingleResult();
        
        if(count == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}