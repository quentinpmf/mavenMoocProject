/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.service;

import fr.utbm.mavenproject.entity.Client;
import fr.utbm.mavenproject.repository.FileClientDao;

/**
 *
 * @author qboudino
 */
public class ClientService {
    
    public void registerClient(Client cli) {
        FileClientDao fcd = new FileClientDao();
    }
    
}