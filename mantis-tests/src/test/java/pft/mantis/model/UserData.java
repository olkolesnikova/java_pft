package pft.mantis.model;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_mantis")

public class UserData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;


    @Column(name = "username")
    private String name;


    @Column(name = "email")
    private String email;


    @Column(name = "password")
    private String password;

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }


}
