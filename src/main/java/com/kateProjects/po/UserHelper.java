package com.kateProjects.po;

public class UserHelper {

    public static TestUserBuilder getTestUserBuilder (){
        TestUserBuilder testUserBuilder = new TestUserBuilder();
        UserDirector director = new UserDirector(testUserBuilder);
        director.construct();
        return testUserBuilder;
    }

    public static EmptyCredentialsUserBuilder getEmptyCredentialsUserBuilder (){
        EmptyCredentialsUserBuilder emptyCredentialsUserBuilder = new EmptyCredentialsUserBuilder();
        UserDirector director = new UserDirector(emptyCredentialsUserBuilder);
        director.construct();
        return emptyCredentialsUserBuilder;
    }
}
