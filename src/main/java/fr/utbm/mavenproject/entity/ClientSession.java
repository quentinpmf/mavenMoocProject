package fr.utbm.mavenproject.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author quentinboudinot
 */
@Entity
@Table(name = "CLIENT_SESSION")
public class ClientSession implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Client clientId;
    
    @JoinColumn(name = "SESSION_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private CourseSession sessionId;

    public ClientSession() {
    }

    public ClientSession(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public CourseSession getSessionId() {
        return sessionId;
    }

    public void setSessionId(CourseSession sessionId) {
        this.sessionId = sessionId;
    }

}