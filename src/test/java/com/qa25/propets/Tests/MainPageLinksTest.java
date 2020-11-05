package com.qa25.propets.Tests;

import org.testng.annotations.*;

public class MainPageLinksTest extends TestBase{

    @BeforeMethod
    public void insurePreconditions() throws InterruptedException {
        appManager.getMainPage().isMainPageOpened();
    }


    @Test(enabled = true)
    public void LinksHeaderTest() throws InterruptedException {
        //check Logo
        appManager.getMainPage().clickOnHeaderLogo();
        checkSignForm();

        //check "SignIn" button
        appManager.getMainPage().clickOnHeaderSignInButton();
        checkSignForm();

    }

    @Test(enabled = true)
    public void LinksBodyTest() throws InterruptedException {
        //check "I lost my pet" button
        appManager.getMainPage().clickOnLostButton();
        checkSignForm();

        //check "I found a pet" button
        appManager.getMainPage().clickOnFoundButton();
        checkSignForm();

        //check "Join" link
        appManager.getMainPage().clickOnJoinLink();
        checkSignForm();
    }

    private void checkSignForm() throws InterruptedException {
        appManager.getMainPage().isSignInFormPresent();
        appManager.getMainPage().delay(1000);
        appManager.getSignForm().clickXButton();
    }

}
