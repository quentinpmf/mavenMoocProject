/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.service;

import fr.utbm.mavenproject.entity.Location;
import fr.utbm.mavenproject.repository.FileLocationDao;

/**
 *
 * @author qboudino
 */
public class LocationService {
    
    public void registerLocation(Location loc) {
        FileLocationDao fld = new FileLocationDao();
    }
    
}
