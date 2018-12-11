/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 *
 * @author quentinboudinot
 */
@Entity
@Table(name = "CLIENT")

/**
 *
 * @author quentinboudinot
 */
public class Client implements Serializable {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private int id;
 
    @Column(name = "LASTNAME", nullable = false)
    private String lastname;

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstname;
    
    @Column(name = "ADDRESS", nullable = false)
    private String address;
    
    @Column(name = "PHONE", nullable = false)
    private String phone;
    
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    
    @ManyToMany(mappedBy = "CLIENT")
    private List<CourseSession> sessions = new ArrayList<CourseSession>();

    public Client(int id, String lastname, String firstname, String address, String phone, String email, String password) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public List<CourseSession> getSessions() {
        return sessions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSessions(List<CourseSession> sessions) {
        this.sessions = sessions;
    }
    
}