/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author qboudino
 */
@Entity
@Table(name = "COURSE")

/**
 *
 * @author quent
 */
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CODE", nullable = false, unique = true)
    private int code;
 
    @Column(name = "TITLE", nullable = false)
    private String title;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "COURSE")
    private List<CourseSession> sessions = new ArrayList<CourseSession>();

    public Course(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public Course() {
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public List<CourseSession> getSessions() {
        return sessions;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSessions(List<CourseSession> sessions) {
        this.sessions = sessions;
    }

}