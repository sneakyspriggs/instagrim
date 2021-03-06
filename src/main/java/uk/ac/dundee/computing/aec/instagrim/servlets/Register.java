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
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;

/**
 * Servlet designed to handle registration requests
 *
 * @author Will Neal
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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

        /* Error handling for blank registration input*/
        if (username.equals("")) {
            error("Enter a username, please press back and try again", response);
            return;
        } else if (password.equals("")) {
            error("Enter a password, please press back and try again", response);
            return;
        } else if (first_name.equals("")) {
            error("Enter a first name, please press back and try again", response);
            return;
        } else if (last_name.equals("")) {
            error("Enter a last name, please press back and try again", response);
            return;
        }
       
        /* This part of the register is very important. It does not let the register itself run until it has checked using a method in the User Model that the username trying to be registered is */
        /* not already in use. Before adding this, it would just overwrite an existing user if you tried to do this, obviously a big problem! */
        User us = new User();
        us.setCluster(cluster);
        if ("" != username) {
            if (us.userCheck(username)) {
                error("Username: " + username + " is already in use. Please click back, and try to register again with a new one", response);
            }
        } else {
            us.RegisterUser(username, password, email, first_name, last_name);
            response.sendRedirect("/Instagrim");
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
        out.println("Input Error!");
        out.println(something);
        out.close();
        return;
    }

}
