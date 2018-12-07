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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

    <title>Saint-Joseph</title>

    <!-- Custom styles for this template -->
    <link href="style.css" rel="stylesheet">
    <link href="login.css" rel="stylesheet">
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
                <a class="nav-link" href="home.jsp">Accueil</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="course_sessions">Formations</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="connexion">Connexion</a>
              </li>
              <li class="nav-item active">
                <a class="nav-link" href="inscription">Inscription</a>
                <span class="sr-only">(current)</span>
              </li>
            </ul>
          </div>
        </div>
    </nav>

    <!-- Page Content -->
    <div class="container">
	
        <div class="login-page">
            <div class="form">
            
              <h2>Inscription</h2>  
              <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p> 
              
              <form class="login-form" method="post" action="inscription" >
                  
                <span class="erreur">${erreurs['email']}</span>
                <input type="email" id="email" name="email" placeholder="Email" value="${param.email}" maxlength="60" />
                
                <span class="erreur">${erreurs['motdepasse']}</span>
                <input type="password" id="motdepasse" name="motdepasse" placeholder="Mot de passe" value="" maxlength="20" />
                
                <span class="erreur">${erreurs['confirmation']}</span>
                <input type="password" id="confirmation" placeholder="Confirmation mot de passe" name="confirmation" value="" maxlength="20" />
                
                <span class="erreur">${erreurs['lastname']}</span>
                <input type="text" id="lastname" name="lastname" placeholder="Nom de famille" value="${param.lastname}" maxlength="30" />
                
                <span class="erreur">${erreurs['firstname']}</span>
                <input type="text" id="firstname" name="firstname" placeholder="Prénom" value="${param.firstname}" maxlength="30" />
                
                <span class="erreur">${erreurs['address']}</span>
                <input type="text" id="address" name="address" placeholder="Adresse" value="${param.address}" maxlength="100" />
                
                <span class="erreur">${erreurs['phone']}</span>
                <input type="text" id="phone" name="phone" placeholder="Téléphone" value="${param.phone}" maxlength="10" />
                
                <button>Inscription</button>
                <p class="message">Déja enregistré? <a href="connexion">Connectez-vous</a></p>
            </form>
                 
            </div>
        </div>

    </div>
</body>
</html>