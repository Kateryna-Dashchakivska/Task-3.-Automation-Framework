package com.kateProjects.po;

public class TestUserBuilder implements UserBuilder{

    private User user;

    public TestUserBuilder(){
        this.user = new User();
    }

    public void buildCredentials(){
        this.user.setEmail("kate_d_test16@mail.com");
        this.user.setPassword("123456");
    }

    public void buildName(){
        this.user.setName("Kate Test");
    }

    public User getResult(){
        return this.user;
    }
}
