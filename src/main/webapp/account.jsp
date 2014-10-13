<%-- 
    Document   : account
    Created on : 13-Oct-2014, 16:52:24
    Author     : williamneal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
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
        <h1>Your Information</h1>
        <ul>
            <%
                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                if (lg != null) {
                    String UserName = lg.getUsername();
                    if (lg.getlogedin()) {
            %>
            <li><% out.println("You are logged in as " + lg.getUsername());%></li>
            <li><% out.println("Your email address is registered as: " + lg.getEmail());%></li>
            <li><% out.println("Your first name is registered as: " + lg.getFirstName());%></li> 
            <li><% out.println("Your last name is registered as: " + lg.getLastName());%></li> 
            <li><a href="/Instagrim/Logout">Logout</a></li>
                <%}
                } else {
                %>
                <%}%>
        </ul>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
                <li>&COPY; Will Neal</li>
            </ul>
        </footer>
    </body>
</html>
