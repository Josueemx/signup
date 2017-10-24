/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itesm.signup.manager;

import mx.itesm.signup.dao.UserDAO;

/**
 *
 * @author Morales
 */
public class UsersManager {
    public boolean isUsernameAvailable(String username){
        boolean res = false;
        UserDAO userDAO = new UserDAO();
        if (userDAO.findByUsername(username)==null)
            res = true;
        return res;
    }
}
