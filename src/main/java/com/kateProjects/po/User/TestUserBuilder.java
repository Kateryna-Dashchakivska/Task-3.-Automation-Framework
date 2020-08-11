package com.kateProjects.po.User;

import com.kateProjects.po.Strings.Constant;

public class TestUserBuilder implements UserBuilder {

    private User user;

    public TestUserBuilder(){
        this.user = new User();
    }

    public void buildCredentials(){
        this.user.setEmail(Constant.LOGIN_EMAIL);
        this.user.setPassword(Constant.VALID_ENCRYPTED_PASSWORD);
    }

    public void buildName(){
        this.user.setName("Kate Test");
    }

    public User getResult(){
        return this.user;
    }
}
