<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
        <h1>InstaGrim ! </h1>
        <h2>Your world in Black and White</h2>
        <nav>
            <ul>
                    <%
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <li class="nav"><a href="/Instagrim/Images/majed">Sample Images</a></li>
                <br>
                <li><% out.println("You are logged in as " + lg.getUsername());%></li>
                <li><a href="/Instagrim/Logout">Logout</a></li>
                    <%}
                    } else {
                    %>
                    <%}%>

            </ul>
        </nav>

        <article>

            <!-- Added accept="image/*" to filter image types automatically in the browser-->
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image">
                File to upload: <input type="file" accept="image/*" name="upfile"><br/>
                Caption: <input type="text" name="caption">
                <br/>
                <input type="submit" value="Press"> to upload the file!
            </form>

        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
