
package by.bsu.kolodyuk.bettingapp.model.entity;

import java.io.Serializable;


public abstract class User implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private UserType userType;
    

    public User() {
    }
    
    public User(UserType userType) {
        this.userType = userType;
    }
    
    public User(int id, String firstName, String lastName, String login, String password, UserType userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.userType = userType;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    
}