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
              <li class="nav-item">
                <a class="nav-link" href="home.jsp">Accueil
                
                </a>
              </li>
              <li class="nav-item active">
                <a class="nav-link" href="course_sessions">Formations</a>
                <span class="sr-only">(current)</span>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="connexion">Connexion</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="inscription">Inscription</a>
              </li>
            </ul>
          </div>
        </div>
    </nav>

    <!-- Page Content -->
    <div class="container">

      <!-- Page Heading -->
      <h1 class="my-4">Toutes les sessions
        <small>de formations</small>
      </h1>

      <div class="row">
        <%
        try
        {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        String url="jdbc:derby://localhost:1527/bdd_projetLO54";
        String username="bdd_user";
        String password="bdd_password";
        String query="select * from COURSE_SESSION cs INNER JOIN COURSE c ON cs.COURSE_CODE = c.CODE";
        Connection conn=DriverManager.getConnection(url, username, password);
        Statement stmt=conn.createStatement();
        stmt.setMaxRows(4);
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next())
        {
            String imageLink = rs.getString("IMAGE");
            
            if (imageLink == null || imageLink.isEmpty()) {
                imageLink="http://image.noelshack.com/fichiers/2018/49/4/1544137044-formation8.jpg";
            }
        %>
          
        <a href="course_sessions?id=<%=rs.getInt("ID") %>">
            <div class="col-lg-4 col-sm-6 portfolio-item sessions_list">
              <div class="card h-100">
                <a href="#"><img class="card-img-top" src="<%=imageLink %>" alt=""></a>
                <div class="card-body">
                  <h4 class="card-title">
                    <a href="course_sessions?id=<%=rs.getInt("ID") %>"><%=rs.getString("TITLE") %></a>
                  </h4>
                  <p class="card-text">
                      <i>Du <%=rs.getDate("START_DATE") %> au <%=rs.getDate("END_DATE") %></i>
                      <br>
                      <u>Lieu</u> : <%=rs.getInt("LOCATION_ID") %>
                      <br>
                      <u>Places restantes</u> : <%=rs.getInt("PLACES_LIBRE") %>/<%=rs.getInt("MAX_PARTICPANT") %>
                  </p>
                  <p class="card-text">
                    <!-- <button type="button" class="btn btn-dark">Pré-inscrit</button> -->
                    <a class="btn btn-success" href="#" role="button">Se pré-inscrire</a>
                  </p>
                </div>
              </div>
            </div>
        </a>

        <%

        }
        %>

        <%
        rs.close();
        stmt.close();
        conn.close();
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }
        %>
        
      </div><!-- /.row -->
      
      <!-- Pagination -->
      <ul class="pagination justify-content-center">
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
          </a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#">1</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#">2</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#">3</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
          </a>
        </li>
      </ul>

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2018</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
