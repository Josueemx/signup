/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itesm.signup.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.itesm.signup.manager.UsersManager;
import mx.itesm.signup.util.StringChecker;

/**
 *
 * @author Morales
 */
@WebServlet(name = "validatePassword", urlPatterns = {"/validatePassword"})
public class ValidatePasswordServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String password = request.getParameter("password");

        int points = 0;
        String level = "";
        String textClass = "bg-warning";
        
        if(password.length()>8){
            points++;
        }
        if(StringChecker.hasDigits(password)){
            points++;
        }
        if(StringChecker.hasUpperAndLowerCase(password)){
            points++;
        }
        
        switch(points){
            case 0:
                level = "weak";
                break;
            case 1:
                level = "low";
                break;
            case 2:
                level = "good";
                textClass = "bg-info";
                break;
            case 3:
                level = "high";
                textClass = "bg-success";
                break;
        }

        Map responseJSON = new HashMap();
        responseJSON.put("level", level);
        responseJSON.put("textClass", textClass);
        
        String json = new Gson().toJson(responseJSON);
        out.print(json);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
