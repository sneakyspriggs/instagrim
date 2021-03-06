<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.Pic" %>
<%@page import="uk.ac.dundee.computing.aec.instagrim.models.User" %> 
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.UserStore" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            <h1>InstaGrim!</h1>
            <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>                             
                <li><a href="upload.jsp">Upload</a></li>
                    <%
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            System.out.println("Session in jsp "+session +  lg.getUsername()+lg.getlogedin());
                            if (lg.getlogedin()) {
                    %>
                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <br>
                <li><% out.println("You are logged in as " + lg.getUsername());%></li>
                
                <li><a href="/Instagrim/Account/<%=lg.getUsername()%>">Account</a></li>
                <li><a href="/Instagrim/Logout">Logout</a></li>
                    <%}
                    } else {
                    %>
                <li><a href="register.jsp">Register</a></li>
                <li><a href="login.jsp">Login</a></li>
                    <%}%>
            </ul>
        </nav>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
                <li>&COPY; Will Neal</li>
            </ul>
        </footer>
    </body>
</html>
