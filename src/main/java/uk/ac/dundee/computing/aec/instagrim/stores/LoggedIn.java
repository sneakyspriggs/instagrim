/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.stores;

/**
 *
 * @author Administrator
 */
public class LoggedIn {
    boolean logedin=false;
    String Username=null;
    String Email=null;
    
    public void LogedIn(){     
    }   
    public void setUsername(String name){
        this.Username=name;
    }
    public String getUsername(){
        return Username;
    }
    /*public void setEmail(String email){
        this.Email=email; (not working currently)
    }
    public String getEmail(){
        return Email; (not working currently)
    }*/
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
