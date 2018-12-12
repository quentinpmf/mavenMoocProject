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
@Table(name = "COURSE")
public class Course implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CODE", nullable = false)
    private int code;
    
    @Basic(optional = false)
    @Column(name = "TITLE", nullable = false, length = 255)
    private String title;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseCode")
    private List<CourseSession> courseSessionList;
    
    public Course() {
    }

    public Course(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CourseSession> getCourseSessionList() {
        return courseSessionList;
    }

    public void setCourseSessionList(List<CourseSession> courseSessionList) {
        this.courseSessionList = courseSessionList;
    }


}