/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

/**
 * Servlet designed to handle login requests
 *
 * @author Will Neal
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    Cluster cluster = null;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");

        if (username.equals("")) {
            error("Enter a username", response);
            return;
        } else if (password.equals("")) {
            error("Enter a password", response);
            return;
        }

        User us = new User();
        us.setCluster(cluster);
        boolean isValid = us.IsValidUser(username, password);
        HttpSession session = request.getSession();
        System.out.println("Session in servlet " + session);
        if (isValid) {
            LoggedIn lg = new LoggedIn();
            lg.setLogedin();
            lg.setUsername(username);
            lg.setEmail(email);
            lg.setFirstName(first_name);
            lg.setLastName(last_name);

            session.setAttribute("LoggedIn", lg);
            System.out.println("Session in servlet " + session + username + lg.getUsername());
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

        } else {
            error("Login Incorrect", response);
            /* response.sendRedirect("/Instagrim/login.jsp"); */
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void error(String something, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        out = new PrintWriter(response.getOutputStream());
        out.println("Input Error");
        out.println(something);
        out.close();
        return;
    }

}
