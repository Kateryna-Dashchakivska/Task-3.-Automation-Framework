package com.kateProjects.po.User;

public class User {

    private String email;
    private String password;
    private String name;

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "Email = " + email +
                ", password = " + password +
                '}';
    }
}
