package com.kateProjects.po.TestUtils;

import org.testng.*;

public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    //WebDriver driver = DriverFactory.getDriver("firefox");

  //  protected List<String> suiteResult = new LinkedList<String>();

    public void onStart(ISuite suite) {
        System.out.println("Starting " + suite.getName() + "!");
    }

    public void onFinish(ISuite suite) {
        System.out.println("Finishing " + suite.getName() + "!");
    }

    public void onStart(ITestContext arg0) {
    }

    public void onFinish(ITestContext arg0) {
    }

    public void onTestSuccess(ITestResult testResult) {
        System.out.println(testResult.getName() + " has passed successfully with status: " + testResult.getStatus());
    }

    public void onTestFailure(ITestResult arg0) {

//        try {
//            Image.takeSnapShot(driver, "src\\main\\resources");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void onTestStart(ITestResult test) {
        System.out.println("Starting my test: " + test.getName() + " !");
    }

    public void onTestSkipped(ITestResult arg0) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }

    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
    }

    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
    }

    private String returnMethodName(ITestNGMethod method) {
        return method.getRealClass().getSimpleName() + "." + method.getMethodName();
    }

}