/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.service;

import fr.utbm.mavenproject.repository.LocationDao;
import fr.utbm.mavenproject.entity.Location;

import java.util.List;

public class LocationService {
    
    private static LocationDao objLocaDao;

    public LocationService() {
        objLocaDao = new LocationDao();
    }

    public void create(Location entity) {
        objLocaDao.insert(entity);
    }

    public void update(Location entity) {
        objLocaDao.update(entity);
    }

    public Location getLocationById(int id) {
        return objLocaDao.getLocationById(id);
    }

    public void delete(int id) {
        Location loc = objLocaDao.getLocationById(id);
        objLocaDao.delete(loc);
    }

    public List<Location> getLocations() {
        return objLocaDao.getLocations();
    }

    public LocationDao objLocaDao() {
        return objLocaDao;
    }
}
