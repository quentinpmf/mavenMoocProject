package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.Client;
import fr.utbm.mavenproject.service.ClientService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author quentinboudinot
 */
@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    public static final String VUE = "/admin.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans AdminServlet"); //TODO QUENTIN : à désactiver lors de la mise en prod
        
        ClientService cs = new ClientService();
        List<Client> clients = cs.getClients();
        request.setAttribute("clients", clients);
        
        //parametres
        String param_role = request.getParameter("role");
        String param_clientId = request.getParameter("clientId");
	
        if(param_clientId != null && param_role != null)
        {
            Integer intClientId = Integer.valueOf(param_clientId);
            System.out.println("param_role = "+param_role);
            System.out.println("param_clientId = "+param_clientId);
        
            Client cli = cs.getClientById(intClientId);
            
            switch(param_role)
            {
                case "down":
                    cli.setRole("0");
                break;
                case "up":
                    cli.setRole("1");
                break;
            }
            cs.update(cli);
        }
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {        
        System.out.println("doPost dans AdminServlet"); //TODO QUENTIN : à désactiver lors de la mise en prod        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}