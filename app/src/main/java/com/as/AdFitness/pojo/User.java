package com.as.AdFitness.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class User implements Serializable {
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("firstName")
    private String firstName;
    @Expose
    @SerializedName("lastName")
    private String lastName;
    @Expose
    @SerializedName("birthday")
    private String birthday;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("role")
    private String role;

    public User() {
    }

    public User(int id, String username, String password, String email, String firstName, String lastName, String birthday, String phone, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phone = phone;
        this.role = role;
    }

    public User(String username, String password, String email, String firstName, String lastName, String birthday, String phone, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phone = phone;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
