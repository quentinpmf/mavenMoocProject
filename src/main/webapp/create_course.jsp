<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <title>Saint-Joseph</title>
    <link href="style.css" rel="stylesheet">
    <!-- searchbar -->
    <link href="searchbar.css" rel="stylesheet">
  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container">
          <a class="navbar-brand" href="home.jsp">Université privée Saint-Joseph</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
              <li class="nav-item">
                <a class="nav-link" href="home">Accueil
                
                </a>
              </li>
              <li class="nav-item active">
                <a class="nav-link" href="course_sessions">Formations</a>
                <span class="sr-only">(current)</span>
              </li>
              <%
                  HttpSession userSession = request.getSession(true);
                  Object lastname = userSession.getAttribute("lastname");
                  Object firstname = userSession.getAttribute("firstname");
                  Object role = userSession.getAttribute("role");
                  System.out.println("lastname = "+lastname);
                  System.out.println("firstname = "+firstname);
                  System.out.println("role = "+role);

                  if(lastname != null)
                  {
              %>        
                    <li class="nav-item">
                        <a class="nav-link" href="deconnexion">Déconnexion de <%=lastname%><%=firstname%></a>
                    </li>
              <%
                  }
                  else
                  {
              %>
                    <li class="nav-item">
                       <a class="nav-link" href="connexion">Connexion</a>
                    </li>
                    <li class="nav-item">
                       <a class="nav-link" href="inscription">Inscription</a>
                    </li>
              <%
                  }
              %>
            </ul>
          </div>
        </div>
    </nav>

    <!-- Page Content -->
    <div class="container">

        <!-- Page Heading -->
        <h1 class="my-4">Création
          <small>d'une formation</small>
        </h1>
      
        <div class="row">
            <div class="col-md-6">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                      <label for="contain">Titre de la formation</label>
                      <input class="form-control" type="text" name="title"/>
                    </div>
                    <div class="divCreateFormationButton">
                        <button class="btn btn-success" type="submit" >Créer une formation</button>
                    </div>
                </form>
            </div>

        </div>

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-3 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2018</p>
      </div>
      <!-- /.container -->
    </footer>

  </body>

</html>
