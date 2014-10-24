/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.UserStore;

/**
 *
 * @author Administrator
 */
public class User {

    /* Model designed to interface with a database for user related acquisitions, deletions, etc */
    Cluster cluster;

    public User() {

    }

    public boolean RegisterUser(String username, String Password, String email, String first_name, String last_name) {
        AeSimpleSHA1 sha1handler = new AeSimpleSHA1();
        String EncodedPassword = null;
        try {
            EncodedPassword = sha1handler.SHA1(Password);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException et) {
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into userprofiles (login,password,email,first_name,last_name) Values(?,?,?,?,?)");

        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username, EncodedPassword, email, first_name, last_name));
        //We are assuming this always works.  Also a transaction would be good here !

        return true;
    }

    public boolean IsValidUser(String username, String Password) {
        AeSimpleSHA1 sha1handler = new AeSimpleSHA1();
        String EncodedPassword = null;
        try {
            EncodedPassword = sha1handler.SHA1(Password);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException et) {
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            for (Row row : rs) {

                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public UserStore getUserDetails(String username) {
        /* Method designed to retrieve the user details and place them inside the UserStore. Based upon the IsValidUser method*/
        Session session = cluster.connect("instagrim");
        /* Next statement attempts to retrieve all from userprofiles, not just the "password" field as seen in IsValidUser */
        PreparedStatement ps = session.prepare("select * from userprofiles where login =?");
        /* Main statements to enable the database access are identical to IsValidUser */
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute(boundStatement.bind(username));
        UserStore userStore = new UserStore();
        if (rs.isExhausted()) {
            System.out.println("Cannot find user entry");
            return null;
        } else {
            for (Row row : rs) {
                String login = row.getString("login");
                String email = row.getString("email");
                String first_name = row.getString("first_name");
                String last_name = row.getString("last_name");
                userStore.setUser(login, email, first_name, last_name);
            }
        }
        return userStore;
    }

    public boolean userCheck(String username) {

        /* Method designed to query the database as to whether or not a username is already in user there or not. Used in the Register.java servlet for error handling in this respect */
        /* Works by having the username to be checked passed to it from Register.java, checks agains the database for repitions and returns true or false depending on the result */
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select email from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            for (Row row : rs) {

                String StoredPass = row.getString("email");

                return true;

            }
            return false;
        }
    }
}
