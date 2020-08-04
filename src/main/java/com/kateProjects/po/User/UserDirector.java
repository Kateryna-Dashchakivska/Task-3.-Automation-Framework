package com.kateProjects.po.User;

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
