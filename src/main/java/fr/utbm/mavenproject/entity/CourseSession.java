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
import javax.persistence.Table;

/**
 *
 * @author qboudino
 */
@Entity
@Table(name = "COURSE_SESSION")

/**
 *
 * @author quent
 */
public class CourseSession implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
 
    @Column(name = "START_DATE")
    private String startDate;
    
    @Column(name = "END_DATE")
    private String endDate;

    @Column(name = "MAX_PARTICPANT")
    private String maxParticipant;
    
    @Column(name = "COURSE_CODE")
    private String courseCode;
    
    @Column(name = "LOCATION_ID")
    private String locationId;   
    
    @Column(name = "IMAGE")
    private String imageLink; 
    
    @Column(name = "PLACES_LIBRE")
    private int placesLibre; 

    public CourseSession(int id, String startDate, String endDate, String maxParticipant, String courseCode, String locationId, String imageLink, int placesLibre) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxParticipant = maxParticipant;
        this.courseCode = courseCode;
        this.locationId = locationId;
        this.imageLink = imageLink;
        this.placesLibre = placesLibre;
    }

    public CourseSession() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setMaxParticipant(String maxParticipant) {
        this.maxParticipant = maxParticipant;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
    
    public void setPlacesLibre(int placesLibre) {
        this.placesLibre = placesLibre;
    }
    
    public int getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getMaxParticipant() {
        return maxParticipant;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getLocationId() {
        return locationId;
    }
    
    public String getImageLink() {
        return imageLink;
    }

    public int getPlacesLibre() {
        return placesLibre;
    }
}