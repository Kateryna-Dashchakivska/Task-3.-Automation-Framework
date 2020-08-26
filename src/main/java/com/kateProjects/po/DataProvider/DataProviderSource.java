package com.kateProjects.po.DataProvider;

import com.kateProjects.po.Strings.Constant;
import org.testng.annotations.DataProvider;

public class DataProviderSource {

    @DataProvider (name = "dp-invalid-emails")
    public Object[][] dpInvalidEmails(){
        return Constant.LOGIN_INVALID_EMAIL;
    }
}
