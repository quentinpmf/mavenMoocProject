package fr.utbm.mavenproject.controller;

import static fr.utbm.mavenproject.controller.LoginServlet.VUE;
import fr.utbm.mavenproject.entity.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

/*
 * @author quentinboudinot
*/
public class HomeServlet extends HttpServlet {
    public static final String VUE_HOME     = "/home.jsp";
    public static final String CHAMP_EMAIL  = "email";
    public static final String CHAMP_PASS   = "motdepasse";
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans HomeServlet 123");
        this.getServletContext().getRequestDispatcher( VUE_HOME ).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {        
        System.out.println("doPost dans HomeServlet 123");
    }
}