/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.stores;

/**
 * Store designed to hold the logged in state of the session
 * @author Will Neal
 */
public class LoggedIn {
    boolean logedin=false;
    String username=null;
    String email=null;
    String first_name=null;
    String last_name=null;
    
    public void LogedIn(){     
    }   
    public void setUsername(String name){
        this.username=name;
    }
    public String getUsername(){
        return username;
    }
    public void setEmail(String email){
        this.email=email; 
    }
    public String getEmail(){
        return email; 
    }
    public void setFirstName(String first_name){
        this.first_name=first_name;
    }
    public String getFirstName(){
        return first_name;
    }
    public void setLastName(String last_name){
        this.last_name=last_name;
    }
    public String getLastName(){
        return last_name;
    }
    public void setLogedin(){
        logedin=true;
    }
    public void setLogedout(){
        logedin=false;
    }   
    public void setLoginState(boolean logedin){
        this.logedin=logedin;
    }
    public boolean getlogedin(){
        return logedin;
    }
}
