package com.kateProjects.po.Strings;

public class Constant {

    private Constant() {}
    public static final String LOGIN_EMAIL = "kate_d_test16@mail.com";
    public static final String LOGIN_UNREGISTERED_EMAIL = "kate_d_test@mail.com";

    public static final Object [][] LOGIN_INVALID_EMAIL = {{"kate_d_test16@mail."}, {"kate_d_test16@mail"}, {"kate_d_test16mail.com"},
            {"@mail.com"}, {"kate_d_test16@mail,com"}, {"kate_d_test16@@mail.com"}};

    public static final String VALID_ENCRYPTED_PASSWORD = "wnleXnHllYWt8id4C+rxow==";
    public static final String INVALID_ENCRYPTED_PASSWORD = "xVk99Mdlyi/hLGu7u4tc3Q==";
}
