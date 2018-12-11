<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>Saint-Joseph</title>
  </head>

    <body>
        <%
            HttpSession userSession = request.getSession(true);
            Object email = userSession.getAttribute("email");
            System.out.println("email = "+email);

            if(email != null)
            {
                userSession.invalidate();
            }
            response.sendRedirect("home.jsp");
            return; // <--- Here.
        %>
    </body>
</html>