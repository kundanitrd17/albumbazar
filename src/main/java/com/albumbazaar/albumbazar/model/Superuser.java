package com.albumbazaar.albumbazar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "superuser", uniqueConstraints = @UniqueConstraint(name = "unique_email", columnNames = { "email" }))
public class Superuser {
<<<<<<< HEAD

    @Id
    private Integer id;
=======
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
>>>>>>> 884700053791cec91e49759d7246182701454545
    private String password;
    private String name;
    private String profile_pic;
    private String email;
    private String role;

<<<<<<< HEAD
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

=======
>>>>>>> 884700053791cec91e49759d7246182701454545
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

    public String getRole() {
        return role;
    }

<<<<<<< HEAD
    public void setRole(String role) {
        this.role = role;
    }
=======
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
>>>>>>> 884700053791cec91e49759d7246182701454545

}