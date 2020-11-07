package com.qa25.propets.Tests;

import com.qa25.propets.Model.User;
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

    @Test (dataProvider = "validUserSignUpFromFile",dataProviderClass = DataProviders.class, enabled = true)
    public void userSignUpPositiveTest(User user) throws InterruptedException {
        //Go to SignUp Tab
        if(!appManager.getSignForm().isSignUpTabPresent()) {
            appManager.getSignForm().clickOnSignUpTab();
        }
        Assert.assertTrue(appManager.getSignForm().isSignUpTabPresent());
        appManager.getSignForm().fillSignupUserForm(user);
        appManager.getSignForm().delay(1000);
        appManager.getSignForm().clickSubmitButton();
        logger.info("Signup flow is completed. Checking if new user can log in...");

        //check if new user can sign in
        appManager.getSignForm().clickOnSignInTab();
        if(appManager.getSignForm().isSignInTabPresent()) {
            appManager.getSignForm().fillSigninUserForm(user);
            appManager.getSignForm().delay(1000);
            appManager.getSignForm().clickSubmitButton();
            appManager.getSignForm().delay(1000);

            String[] split = user.getName().split(" ");
            logger.info("User Logged in. Actual result: "
                    + appManager.getSideMenu().getUserName()
                    + "; expected result: " + split[0]);
            Assert.assertEquals(appManager.getSideMenu().getUserName(), split[0]);
            appManager.getSideMenu().clickOnLogout();
        }

    }

    @Test (dataProvider = "invalidUserSignUpFromFile",dataProviderClass = DataProviders.class, enabled = true)
    public void userSignUpNegativeTest(User user) throws InterruptedException {
        //Go to SignUp Tab
        if(!appManager.getSignForm().isSignUpTabPresent()) {
            appManager.getSignForm().clickOnSignUpTab();
        }
        Assert.assertTrue(appManager.getSignForm().isSignUpTabPresent());
        appManager.getSignForm().fillSignupUserForm(user);
        appManager.getSignForm().delay(1000);
        appManager.getSignForm().clickSubmitButton();
        logger.info("Signup flow is completed. Checking if new user can log in...");

        //check if new user can sign in
        appManager.getSignForm().clickOnSignInTab();
        if(appManager.getSignForm().isSignInTabPresent()) {
            appManager.getSignForm().fillSigninUserForm(user);
            appManager.getSignForm().delay(1000);
            appManager.getSignForm().clickSubmitButton();
            appManager.getSignForm().delay(1000);

            logger.info("Is User Logged in? Actual result: "
                    + appManager.getSideMenu().isLogoutPresent()
                    + "; expected result: false");
            Assert.assertFalse(appManager.getSideMenu().isLogoutPresent());

            if(appManager.getSideMenu().isLogoutPresent()) {
                appManager.getSideMenu().clickOnLogout();
            }
        }

    }

}
