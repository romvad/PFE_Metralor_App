package com.example.rvadam.pfe.Model;

import java.io.Serializable;

/**
 * Created by rdelfoss on 13/05/2018.
 */

public class People implements Serializable {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String idCompany;
    private String idRole;
    private String phone;

    public People() {
    }

    public People(String id, String firstname, String lastname, String email, String idCompany, String idRole, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.idCompany = idCompany;
        this.idRole = idRole;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}