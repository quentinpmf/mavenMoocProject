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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @Column(name = "ID", nullable = false, unique = true)
    private int id;
 
    @Column(name = "START_DATE", nullable = false)
    private String startDate;
    
    @Column(name = "END_DATE", nullable = false)
    private String endDate;

    @Column(name = "MAX_PARTICPANT", nullable = false)
    private String maxParticipant;
    
    @JoinColumn(name = "COURSE_CODE")
    @ManyToOne(fetch = FetchType.LAZY)
    private Course courseCode;
    
    @JoinColumn(name = "LOCATION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Location locationId;
    
    @Column(name = "IMAGE", nullable = false)
    private String imageLink; 
    
    @Column(name = "PLACES_LIBRE", nullable = false)
    private int placesLibre; 
    
    public CourseSession(int id, String startDate, String endDate, String maxParticipant, String imageLink, int placesLibre) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxParticipant = maxParticipant;
        this.imageLink = imageLink;
        this.placesLibre = placesLibre;
    }    
    
    public CourseSession() {
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

    public Course getCourseCode() {
        return courseCode;
    }

    public Location getLocationId() {
        return locationId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public int getPlacesLibre() {
        return placesLibre;
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

    public void setCourseCode(Course courseCode) {
        this.courseCode = courseCode;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setPlacesLibre(int placesLibre) {
        this.placesLibre = placesLibre;
    }


}