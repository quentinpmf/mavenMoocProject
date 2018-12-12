package fr.utbm.mavenproject.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author quentinboudinot
 */
@Entity
@Table(name = "CLIENT")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "LASTNAME", nullable = false, length = 255)
    private String lastname;
    
    @Basic(optional = false)
    @Column(name = "FIRSTNAME", nullable = false, length = 255)
    private String firstname;
    
    @Basic(optional = false)
    @Column(name = "ADDRESS", nullable = false, length = 255)
    private String address;
    
    @Basic(optional = false)
    @Column(name = "PHONE", nullable = false, length = 12)
    private String phone;
    
    @Basic(optional = false)
    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;
    
    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private List<ClientSession> clientSessionList;
    
    public Client() {
    }

    public Client(Integer id, String lastname, String firstname, String address, String phone, String email, String password) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ClientSession> getClientSessionList() {
        return clientSessionList;
    }

    public void setClientSessionList(List<ClientSession> clientSessionList) {
        this.clientSessionList = clientSessionList;
    }
    
}