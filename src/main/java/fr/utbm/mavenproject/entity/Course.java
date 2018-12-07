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
@Table(name = "COURSE")

/**
 *
 * @author quent
 */
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CODE")
    private int code;
 
    @Column(name = "TITLE")
    private String title;

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

    public void setCode(int code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}