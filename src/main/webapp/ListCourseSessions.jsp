<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<h2>Liste des Sessions de cours</h2>

<table border="2">
    <tr>
        <td>ID</td>
        <td>Start Date</td>
        <td>End Date</td>
        <td>Max Participant</td>
        <td>Course Code</td>
        <td>Location Id</td>
        <td>Image link</td>
    </tr>
      
    <%
    try
    {
    Class.forName("org.apache.derby.jdbc.ClientDriver");
    String url="jdbc:derby://localhost:1527/bdd_projetLO54";
    String username="bdd_user";
    String password="bdd_password";
    String query="select * from COURSE_SESSION";
    Connection conn=DriverManager.getConnection(url, username, password);
    Statement stmt=conn.createStatement();
    ResultSet rs=stmt.executeQuery(query);
    while(rs.next())
    {

    %>
    
    <tr>
        <td><%=rs.getInt("ID") %></td>
        <td><%=rs.getDate("START_DATE") %></td>
        <td><%=rs.getDate("END_DATE") %></td>
        <td><%=rs.getString("MAX_PARTICIPANT") %></td>
        <td><%=rs.getString("COURSE_CODE") %></td>
        <td><%=rs.getString("LOCATION_ID") %></td>
        <td><%=rs.getString("IMAGE") %></td>
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