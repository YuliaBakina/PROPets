package com.qa25.propets.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserSignupTests extends TestBase{
    @BeforeMethod
    public void insurePreconditions() {
        appManager.getMainPage().isMainPageOpened();

        if(appManager.getSideMenu().isLogoutPresent()){
            appManager.getSideMenu().clickOnLogout();
        }

        appManager.getMainPage().clickOnJoinLink();
    }

    @Test
    public void formSignUpPositiveTest() throws InterruptedException {
        //Go to SignUp Tab, close form by "X" button
        if(!appManager.getSignForm().isSignUpTabPresent()) {
            appManager.getSignForm().clickOnSignUpTab();
        }
        Assert.assertTrue(appManager.getSignForm().isSignUpTabPresent());
        appManager.getSignForm().clickXButton();
        Assert.assertTrue(appManager.getMainPage().isMainPagePresented());

        //Go to SignUp Tab, close form by "Cancel" button
        appManager.getMainPage().clickOnJoinLink();
        if(!appManager.getSignForm().isSignUpTabPresent()) {
            appManager.getSignForm().clickOnSignUpTab();
        }
        Assert.assertTrue(appManager.getSignForm().isSignUpTabPresent());
        appManager.getSignForm().clickCancelButton();
        Assert.assertTrue(appManager.getMainPage().isMainPagePresented());

        //Switch between tabs
        appManager.getMainPage().clickOnJoinLink();
        if(!appManager.getSignForm().isSignUpTabPresent()) {
            appManager.getSignForm().clickOnSignUpTab();
        }
        Assert.assertTrue(appManager.getSignForm().isSignUpTabPresent());

        appManager.getSignForm().delay(1000);

        if(!appManager.getSignForm().isSignInTabPresent()) {
            appManager.getSignForm().clickOnSignInTab();
        }
        Assert.assertTrue(appManager.getSignForm().isSignInTabPresent());

        appManager.getSignForm().delay(1000);

        if(!appManager.getSignForm().isSignUpTabPresent()) {
            appManager.getSignForm().clickOnSignUpTab();
        }
        Assert.assertTrue(appManager.getSignForm().isSignUpTabPresent());

        appManager.getSignForm().delay(1000);

        appManager.getSignForm().clickCancelButton();
        Assert.assertTrue(appManager.getMainPage().isMainPagePresented());

    }
}
