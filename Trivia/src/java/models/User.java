/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Barak
 */
public class User {
 
    private String name;
    private boolean loggedIn;


    public User()
    {
    }
    
    public User(String name, boolean isLoggedIn) {
        this.name = name;
        this.loggedIn = isLoggedIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.loggedIn = isLoggedIn;
    }
    
    
}
