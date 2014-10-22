/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.UserStore;

/**
 * Servlet designed to handle account.jsp requests
 *
 * @author williamneal
 */
@WebServlet(urlPatterns = {"/Account", "/Account/*"})
public class Account extends HttpServlet {

    private Cluster cluster;
    private HashMap CommandsMap = new HashMap();

    public Account() {
        super();
        CommandsMap.put("Account", 1);
    }

    public void init(ServletConfig config) throws ServletException {
        cluster = CassandraHosts.getCluster();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args[] = Convertors.SplitRequestPath(request);
        int command = 0;
        command = (Integer) CommandsMap.get(args[1]);

        switch (command) {
            case 1:
                viewAccount(args[2], request, response);
                break;
            default:
                error("Bad Operator", response);
        }
    }

    private void viewAccount(String login, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setCluster(cluster);
        UserStore userStore = user.getUserDetails(login);

        if (userStore == null) {
            error("Cannot locate user", response);
            return;
        }

        request.setAttribute("store", userStore);
        RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
        rd.forward(request, response);
    }

    private void error(String something, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        out = new PrintWriter(response.getOutputStream());
        out.println("Input Error");
        out.println(something);
        out.close();
        return;
    }
}
