<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    
    <title>Saint-Joseph</title>

    <!-- Custom styles for this template -->
    <link href="style.css" rel="stylesheet">

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
              <li class="nav-item active">
                <a class="nav-link" href="home">Accueil
                <span class="sr-only">(current)</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="course_sessions">Formations</a>
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

      <!-- Portfolio Item Heading -->
      <h1 class="my-4">Notre enceinte privée
        <small>à Belfort</small>
      </h1>

      <!-- Portfolio Item Row -->
      <div class="row">

        <div class="col-md-8">
          <img class="img-fluid" src="http://image.noelshack.com/fichiers/2018/49/4/1544128770-ecole.jpg" alt="image de l'université Saint-Joseph de Belfort">
        </div>

        <div class="col-md-4">
          <h3 class="my-3">Description</h3>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae. Sed dui lorem, adipiscing in adipiscing et, interdum nec metus. Mauris ultricies, justo eu convallis placerat, felis enim.</p>
          <h3 class="my-3">Ce que nous proposons</h3>
          <ul>
            <li>Des enseignants compétents</li>
            <li>Un suivi régulier et personnel</li>
            <li>De nombreuses formations à la carte</li>
            <li>Des lieux de formation  différents</li>
          </ul>
        </div>

      </div>
      <!-- /.row -->

      <h3 class="my-4">Nos prochaines sessions de formation</h3>

      <div class="row">
        <c:forEach items="${courseSessions}" var="courseSession">
            <div class="col-md-3 col-sm-6 mb-4">
                <img class="img-fluid" src="${courseSession.image}" alt="">
            </div>
        </c:forEach>
      </div>
        
      <div class="row">
        <c:forEach items="${courseSessions}" var="courseSession">
            <div class="col-md-3 col-sm-6 mb-4 formation_info">
                <div class="formation_title"><b>${courseSession.courseCode.title}</b></div>
                <div class="formation_date"><i>Du <fmt:formatDate value="${courseSession.startDate}" pattern="dd/MM/yyyy"/> au <fmt:formatDate value="${courseSession.endDate}" pattern="dd/MM/yyyy"/></i></div>
                <div class="formation_lieu"><u>Lieu</u> : ${courseSession.locationId.city}</div>
                <div class="formation_places_dispo"><u>Places libres</u> : ${courseSession.placesLibres}/${courseSession.maxi}</div>
            </div>
        </c:forEach>
      </div>
      <!-- /.row -->
      
      <div class="row">
          <a class="btn btn-primary" href="course_sessions" role="button">Voir toutes nos formations</a>
      </div>
      <br>
      <!-- /.row -->

      </div>
    <!-- /.container -->
    
    <!-- Footer
    <footer class="py-3 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright© 2018 - Projet LO54</p>
      </div>
      <!-- /.container
    </footer> -->

  </body>

</html>