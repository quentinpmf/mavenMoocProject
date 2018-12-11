/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author qboudino
 */
@Entity
@Table(name = "LOCATION")

/**
 *
 * @author quent
 */
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private int id;
 
    @Column(name = "CITY", nullable = false)
    private String city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId")
    private List<CourseSession> courseSessionList = new ArrayList<CourseSession>();

    public Location(int id, String city) {
        this.id = id;
        this.city = city;
    }

    public Location() {
    }
    
    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }
    
    public List<CourseSession> getCourseSessionList() {
        return courseSessionList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCourseSessionList(List<CourseSession> courseSessionList) {
        this.courseSessionList = courseSessionList;
    }


    
}