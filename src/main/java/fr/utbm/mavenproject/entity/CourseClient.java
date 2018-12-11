/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.entity;

import java.io.Serializable;
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
 * @author qboudino
 */
@Entity
@Table(name = "COURSE_CLIENT")

/**
 *
 * @author quent
 */
public class CourseClient implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @JoinColumn(name = "CS_ID", referencedColumnName = "CODE")
    @ManyToOne(optional = false)
    private CourseSession csId;

    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Client clientId;

    public CourseClient(int id) {
        this.id = id;
    }

    public CourseClient() {
    }

    public int getId() {
        return id;
    }

    public CourseSession getCsId() {
        return csId;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCsId(CourseSession csId) {
        this.csId = csId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

}