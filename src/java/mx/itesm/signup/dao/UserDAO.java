/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itesm.signup.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.itesm.signup.entity.User;

/**
 *
 * @author Morales
 */
public class UserDAO {
    
    //NOTA: aqui cambiar de ser necesario y en Configuration Files/persistence.xml
    String db_user = "root"; 
    String db_password = "root";
    
    public User findByUsername(final String username) {
        User user = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/signup?user="+db_user+"&password="+db_password);
            String sql = "SELECT * FROM user WHERE username=? ;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setUsername(rs.getString(3));
                user.setPassword(rs.getString(4));
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, ex.toString());
        }

        return user;
    }
}
