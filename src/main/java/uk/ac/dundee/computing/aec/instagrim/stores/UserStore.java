/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

/**
 * Store created to hold user details for viewing on account page
 *
 * @author williamneal
 */
public class UserStore {

    String username = null;
    String email = null;
    String first_name = null;
    String last_name = null;

    public UserStore() {
    }

public void setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(){
        return username;
    }
   
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setFirstName(String first_name){
        this.first_name = first_name;
    }
    
    public String getFirstName(){
        return first_name;
    }
    
    public void setLastName(String last_name){
        this.last_name = last_name;
    }
    
    public String getLastName(){
        return last_name;
    }   
    
    public void setUser(String username, String email, String first_name, String last_name){
        this.username = username;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;           
    }
}
