package com.qa25.propets.Tests;

import com.qa25.propets.fw.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    protected static ApplicationManager appManager =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

 //   Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp(){
        appManager.init();
    }


/*    @BeforeMethod
    public void startTest(Method m, Object[] p)
    {
        logger.info("Start test: " + m.getName());
        logger.info(" --> With data: " + Arrays.asList(p));

    }*/

    @AfterSuite(enabled = false)
    public void tearDown() {
        appManager.stop();
    }


/*    @AfterMethod(alwaysRun = true)
    public void stopTest(ITestResult result){
        if(result.isSuccess()){
            logger.info("PASSED: Test method --> " + result.getMethod().getMethodName());
        }else{
            logger.error("FAILED: Test method --> " + result.getMethod().getMethodName());
            logger.info("Screenshot: " + appManager.getHeader().takeScreenShot());
        }

        logger.info("Stop test: " + result.getMethod().getMethodName());
        logger.info("======================================================");
    }*/


}
