package db;

import javafx.scene.control.TextField;

import java.io.Serializable;

public class User implements Serializable {
    private String nic;
    private String firstName;
    private String lastName;
    private String address;


    public User(TextField txtId, TextField txtFirstName, TextField txtLastName, TextField txtAddress) {

    }

    public User(String nic, String firstName, String lastName, String address) {
        this.nic = nic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }


    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
