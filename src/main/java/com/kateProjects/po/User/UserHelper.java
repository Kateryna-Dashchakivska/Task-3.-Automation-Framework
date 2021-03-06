package com.kateProjects.po.User;

public class UserHelper {

    public static User getTestUser() {
        TestUserBuilder testUserBuilder = new TestUserBuilder();
        UserDirector director = new UserDirector(testUserBuilder);
        director.construct();
        return testUserBuilder.getResult();
    }

    public static User getEmptyCredentialsUser() {
        EmptyCredentialsUserBuilder emptyCredentialsUserBuilder = new EmptyCredentialsUserBuilder();
        UserDirector director = new UserDirector(emptyCredentialsUserBuilder);
        director.construct();
        return emptyCredentialsUserBuilder.getResult();
    }

    /*private static List <String> invalidEmails(){
        return Arrays.asList("kate_d_test16@mail.", "kate_d_test16@mail", "kate_d_test16mail.com",
                "@mail.com", "kate_d_test16@mail,com", "kate_d_test16@@mail.com");
    }

    public static List<User> createUsersWithInvalidEmail() {
        List <User> result = new ArrayList<User>() ;

        for (String email : UserHelper.invalidEmails()){
            User user = new User();
            user.setEmail(email);
            user.setPassword(CONSTANT.VALID_ENCRYPTED_PASSWORD);
            result.add(user);
        }
        return result;
    }*/

    /*public static User createUsersWithInvalidEmail() {
            User user = new User();
            user.setEmail(email);
            user.setPassword(CONSTANT.VALID_ENCRYPTED_PASSWORD);
            return user;
    }*/
}
