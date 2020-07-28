package com.kateProjects.po;

public class EmptyCredentialsUserBuilder implements UserBuilder{

    private User user;

    public EmptyCredentialsUserBuilder(){
        this.user = new User();
    }

    public void buildCredentials(){
        this.user.setEmail("");
        this.user.setPassword("");
    }

    public void buildName(){
        this.user.setName("");
    }

    public User getResult(){
        return this.user;
    }
}
