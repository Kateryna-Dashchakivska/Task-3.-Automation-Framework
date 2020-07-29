package com.kateProjects.po;

public class TestUserBuilder implements UserBuilder{

    private User user;

    public TestUserBuilder(){
        this.user = new User();
    }

    public void buildCredentials(){
        this.user.setEmail(CONSTANT.LOGIN_EMAIL);
        this.user.setPassword(CONSTANT.VALID_ENCRYPTED_PASSWORD);
    }

    public void buildName(){
        this.user.setName("Kate Test");
    }

    public User getResult(){
        return this.user;
    }
}
