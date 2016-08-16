
package by.bsu.kolodyuk.bettingapp.model.entity;

import java.io.Serializable;


public class Admin extends User implements Serializable {

    public Admin() {
        super(UserType.ADMIN);
    }

    public Admin(int id, String firstName, String lastName, String login, String password) {
        super(id, firstName, lastName, login, password, UserType.ADMIN);
    }
    
}
