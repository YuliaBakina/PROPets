package com.qa25.propets.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    //WebDriver wd;
    EventFiringWebDriver wd;
    MainPageHelper mainPage;
    SignFormHelper signForm;
    SideMenuHelper sideMenu;
/*    UserHelper user;
    CarHelper car;
    HeaderHelper header;
    FooterHelper footer;*/

    String browser;

    public static class MyListener extends AbstractWebDriverEventListener {

        Logger logger = LoggerFactory.getLogger(MyListener.class);

/*        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            logger.info("Start search " + by + "...");
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            logger.info(by + " found.");
        }*/

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            logger.error(throwable.toString());
        }
    }

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

/*    public UserHelper getUser() {
        return user;
    }

    public CarHelper getCar() {
        return car;
    }

    public HeaderHelper getHeader() {
        return header;
    }

    public FooterHelper getFooter(){
        return footer;
    }*/

    public MainPageHelper getMainPage() {
        return mainPage;
    }

    public SignFormHelper getSignForm(){
        return signForm;
    }

    public SideMenuHelper getSideMenu(){
        return sideMenu;
    }

    public void init() {
      //  wd = new EventFiringWebDriver(new ChromeDriver());

        if(browser.equals(BrowserType.CHROME)){
            wd = new EventFiringWebDriver(new ChromeDriver());
        }else if(browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
        }

        wd.register(new MyListener());

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.manage().window().maximize();

        wd.get("https://propets-mern.herokuapp.com/");

        mainPage = new MainPageHelper(wd);
        signForm = new SignFormHelper(wd);
        sideMenu = new SideMenuHelper(wd);

    }

    public void stop() {
        wd.quit();
    }

}
