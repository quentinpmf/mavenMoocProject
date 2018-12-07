<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<h2>Liste des Locations</h2>

<table border="2">
    <tr>
        <td>ID</td>
        <td>City</td>
    </tr>
      
    <%
    try
    {
    Class.forName("org.apache.derby.jdbc.ClientDriver");
    String url="jdbc:derby://localhost:1527/bdd_projetLO54";
    String username="bdd_user";
    String password="bdd_password";
    String query="select * from LOCATION";
    Connection conn=DriverManager.getConnection(url, username, password);
    Statement stmt=conn.createStatement();
    ResultSet rs=stmt.executeQuery(query);
    while(rs.next())
    {

    %>
    
    <tr>
        <td><%=rs.getInt("ID") %></td>
        <td><%=rs.getString("CITY") %></td>
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