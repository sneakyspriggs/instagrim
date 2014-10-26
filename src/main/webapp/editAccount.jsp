<%-- 
    Document   : editProfile
    Created on : 26-Oct-2014, 14:22:07
    Author     : williamneal
--%>

<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit your profile information</title>
    </head>
    <body>
        <% UserStore store = (UserStore) request.getAttribute("UserStore");
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn"); %>
        <header>
            <h1>InstaGrim!</h1>
            <h2>Your world in Black and White</h2>
        </header>
        <article>
            <h3>Edit your profile</h3>
            <form method="POST"  action="editAccount">
                <ul>
                    <li><b>User Name:</b> <input type="text" name="username" value="<%=store.getUsername()%>" readonly="readonly"></li>
                    <li><b>Email:</b> <input type="text" name="email" value="<%=store.getEmail()%>"></li>
                    <li><b>First Name:</b> <input type="text" name="firstName" value="<%=store.getFirstName()%>"></li>
                    <li><b>Last Name:</b> <input type="text" name="lastName" value="<%=store.getLastName()%>"></li>
                </ul>
                <br/>
                <input type="submit" value="Update"> 
            </form>
        </article>
    </body>
</html>