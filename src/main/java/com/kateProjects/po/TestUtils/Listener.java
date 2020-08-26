package com.kateProjects.po.TestUtils;

import org.testng.*;
import java.util.LinkedList;
import java.util.List;

public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    protected List<String> suiteResult = new LinkedList<String>();

    public void onStart(ISuite suite) {
        System.out.println("Starting " + suite.getName() + " !");
    }
    public void onFinish(ISuite suite) {
        System.out.println("Finishing " + suite.getName() + " !");
    }
    public void onStart(ITestContext arg0) {
    }
    public void onFinish(ITestContext arg0) {
    }
    public void onTestSuccess(ITestResult arg0) {
    }
    public void onTestFailure(ITestResult arg0) {
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