<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<h2>Liste des Utilisateurs</h2>

<table border="2">
    <tr>
        <td>ID</td>
        <td>Lastname</td>
        <td>Firstname</td>
        <td>Address</td>
        <td>Phone</td>
        <td>Email</td>
    </tr>
      
    <%
    try
    {
    Class.forName("org.apache.derby.jdbc.ClientDriver");
    String url="jdbc:derby://localhost:1527/bdd_projetLO54";
    String username="bdd_user";
    String password="bdd_password";
    String query="select * from CLIENT";
    Connection conn=DriverManager.getConnection(url, username, password);
    Statement stmt=conn.createStatement();
    ResultSet rs=stmt.executeQuery(query);
    while(rs.next())
    {

    %>
    
    <tr>
        <td><%=rs.getInt("ID") %></td>
        <td><%=rs.getString("LASTNAME") %></td>
        <td><%=rs.getString("FIRSTNAME") %></td>
        <td><%=rs.getString("ADDRESS") %></td>
        <td><%=rs.getString("PHONE") %></td>
        <td><%=rs.getString("EMAIL") %></td>
   </tr>

    <%

    }
    %>
    
</table>
    
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