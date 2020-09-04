package com.albumbazaar.albumbazar.model;

import javax.persistence.*;

@Entity
@Table(
        name = "superuser",
        uniqueConstraints =
                @UniqueConstraint(
                        name = "unique_email",
                        columnNames = {"email"}
                )
)
public class Superuser {
    
    @Id
    private int id;
    private String password;
    private String name;
    private String profile_pic;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Superuser [email=" + email + ", id=" + id + ", name=" + name + ", password=" + password
                + ", profile_pic=" + profile_pic + "]";
    }

    

}