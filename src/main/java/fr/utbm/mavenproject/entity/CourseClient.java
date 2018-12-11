/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @Column(name = "CS_ID")
    private int csId;
 
    @Id
    @Column(name = "CLIENT_ID")
    private int clientId;

    public CourseClient(int csId, int clientId) {
        this.csId = csId;
        this.clientId = clientId;
    }

    public CourseClient() {
    }

    public int getCsId() {
        return csId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setCsId(int csId) {
        this.csId = csId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}