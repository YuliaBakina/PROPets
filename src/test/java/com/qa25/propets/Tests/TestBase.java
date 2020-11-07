package com.qa25.propets.Tests;

import com.qa25.propets.fw.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {
    protected static ApplicationManager appManager =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp(){
        appManager.init();
    }

    @BeforeMethod
    public void startTest(Method m, Object[] p)
    {
        logger.info("Start test: " + m.getName());
        if(p.length != 0) {
            logger.info(" --> With data: " + Arrays.asList(p));
        }
    }

    @AfterSuite(enabled = true)
    public void tearDown() {
        appManager.stop();
    }


    @AfterMethod(alwaysRun = true)
    public void stopTest(ITestResult result){

        if(result.isSuccess()){
            logger.info("Test result: PASSED");
        }else{
            logger.error("Test result: FAILED");
            logger.info("Screenshot: " + appManager.getMainPage().takeScreenShot());
        }

        logger.info("Stop test: " + result.getMethod().getMethodName());
        logger.info("======================================================");
    }


}
