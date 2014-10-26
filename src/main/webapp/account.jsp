<%-- 
    Document   : account
    Created on : 13-Oct-2014, 16:52:24
    Author     : williamneal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <% UserStore store = (UserStore) request.getAttribute("UserStore");
             LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn"); %>
        <header>
            <h1>InstaGrim!</h1>
            <h2>Your world in Black and White</h2>
        </header>
        <h1>Your Information</h1>
        <%
            UserStore userStore = (UserStore) request.getAttribute("store");
            String login = userStore.getUsername();
            String email = userStore.getEmail();
            String firstname = userStore.getFirstName();
            String lastname = userStore.getLastName();
        %>
        <ul>    
            <li>You are logged in as: <%=login%></li>
            <li>Your email address is: <%=email%></li>
            <li>Your first name is: <%=firstname%></li>
            <li>Your last name is: <%=lastname%></li>
            <%-- Link to new edit profile page, can only be accessed by a user on their own profile page --%>
            <li><a href="/Instagrim/editAccount/<%=lg.getUsername()%>">Edit your profile information</a></li>
        </ul>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
                <li><a href="/Instagrim/Logout">Logout</a></li>
                <li>&COPY; Will Neal</li>
            </ul>
        </footer>
    </body>
</html>
