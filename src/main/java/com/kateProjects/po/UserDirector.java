package com.kateProjects.po;

public class UserDirector {

    private UserBuilder userBuilder;

    public UserDirector(UserBuilder builder){
        userBuilder = builder;
    }

    public void construct(){
        userBuilder.buildCredentials();
        userBuilder.buildName();
    }
}
