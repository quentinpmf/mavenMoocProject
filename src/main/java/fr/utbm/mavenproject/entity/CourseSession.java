package fr.utbm.mavenproject.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
 * @author quentinboudinot
 */
@Entity
@Table(name = "COURSE_SESSION")
public class CourseSession implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;
    
    @Basic(optional = false)
    @Column(name = "END_DATE", nullable = false)
    private Date endDate;
    
    @Basic(optional = false)
    @Column(name = "MAXI", nullable = false)
    private int maxi;
    
    @JoinColumn(name = "COURSE_CODE", referencedColumnName = "CODE", nullable = false)
    @ManyToOne(optional = false)
    private Course courseCode;
    
    @JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Location locationId;
    
    @Basic(optional = false)
    @Column(name = "IMAGE", nullable = false)
    private String image;
    
    @Basic(optional = false)
    @Column(name = "PLACES_LIBRES", nullable = false)
    private String placesLibres;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessionId", fetch = FetchType.EAGER)
    private List<ClientSession> clientSessionList;

    public CourseSession() {
    }

    public CourseSession(Integer id) {
        this.id = id;
    }

    public CourseSession(Integer id, Date startDate, Date endDate, int maxi, String image, String placesLibres) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxi = maxi;
        this.image = image;
        this.placesLibres = placesLibres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getMaxi() {
        return maxi;
    }

    public void setMaxi(int maxi) {
        this.maxi = maxi;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getPlacesLibres() {
        return placesLibres;
    }

    public void setPlacesLibres(String placesLibres) {
        this.placesLibres = placesLibres;
    }

    public Course getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Course courseCode) {
        this.courseCode = courseCode;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    public List<ClientSession> getClientSessionList() {
        return clientSessionList;
    }

    public void setClientSessionList(List<ClientSession> clientSessionList) {
        this.clientSessionList = clientSessionList;
    }

}