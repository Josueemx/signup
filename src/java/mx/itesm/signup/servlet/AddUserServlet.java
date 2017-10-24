package mx.itesm.signup.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import mx.itesm.signup.entity.User;
import mx.itesm.signup.helper.PersistenceHelper;
import mx.itesm.signup.util.StringChecker;

@WebServlet(name = "AddUser", urlPatterns = {"/addUser"})
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        
        session.removeAttribute("username");
        session.removeAttribute("name");
        session.removeAttribute("password");
        session.removeAttribute("errors");
        
        List<String> errors = new ArrayList<String>();
        
        String hashedPassword = "";
        try {
            hashedPassword = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(password.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException ex) {
            errors.add("Server error: "+ex.getMessage());
            Logger.getLogger(AddUserServlet.class.getName()).log(Level.SEVERE, ex.getMessage());
            response.sendRedirect("/signup/signUp");
            return;
        }
        
        boolean validUsername = StringChecker.isAlphanumeric(username);
        boolean validName = StringChecker.isValidName(name);
        
        if(!validUsername)
            errors.add("Invalid username, please use valid characters");
        if(!validName)
            errors.add("Invalid name, please use valid characters");
        
        if(validName&&validUsername){
            EntityManagerFactory emf = PersistenceHelper.getEntityManagerFactory();
            EntityManager em = emf.createEntityManager();
            User user = new User();
            user.setName(name);
            user.setUsername(username);
            user.setPassword(hashedPassword);
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
        }

        if (errors.isEmpty()) {
            response.sendRedirect("/signup/Main");
        } else {
            session.setAttribute("username", username);
            session.setAttribute("name", name);
            session.setAttribute("password", password);
            session.setAttribute("errors", errors);
            response.sendRedirect("/signup/signUp");
        }
    }
}
