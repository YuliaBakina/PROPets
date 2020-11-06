package com.qa25.propets.Tests;

import com.qa25.propets.Model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserSignInTests extends TestBase{

    @BeforeMethod
    public void insurePreconditions() {
        appManager.getMainPage().isMainPageOpened();

        if(appManager.getSideMenu().isLogoutPresent()){
            appManager.getSideMenu().clickOnLogout();
        }

        appManager.getMainPage().clickOnHeaderSignInButton();
    }

    @Test
    public void formSignInPositiveTest() throws InterruptedException {
        //Go to SignIn Tab, close form by "X" button
        appManager.getSignForm().clickOnSignInTab();
        Assert.assertTrue(appManager.getSignForm().isSignInTabPresent());
        appManager.getSignForm().clickXButton();
        Assert.assertTrue(appManager.getMainPage().isMainPagePresented());

        //Go to SignIn Tab, close form by "Cancel" button
        appManager.getMainPage().clickOnHeaderSignInButton();
        appManager.getSignForm().clickOnSignInTab();
        Assert.assertTrue(appManager.getSignForm().isSignInTabPresent());
        appManager.getSignForm().clickCancelButton();
        Assert.assertTrue(appManager.getMainPage().isMainPagePresented());

        //Go to SignIn Tab, click on "Forgot password" link
        appManager.getMainPage().clickOnHeaderSignInButton();
        appManager.getSignForm().clickOnSignInTab();
        Assert.assertTrue(appManager.getSignForm().isSignInTabPresent());
        appManager.getSignForm().clickForgotPassLink();
        Assert.assertTrue(appManager.getSignForm().isSignUpTabPresent());
    }

    @Test (dataProvider = "validUserSignInFromFile",dataProviderClass = DataProviders.class, enabled = true)
    public void userSignInPositiveTest(User user) throws InterruptedException {
        //Go to SignIn Tab
        appManager.getSignForm().clickOnSignInTab();
        Assert.assertTrue(appManager.getSignForm().isSignInTabPresent());
        appManager.getSignForm().fillSigninUserForm(user);
        appManager.getSignForm().delay(1000);
        appManager.getSignForm().clickSubmitButton();

        //check if user signed in
        logger.info("User Logged in. Actual result: "
                + appManager.getSideMenu().getUserName()
                + "; expected result: " + user.getName());
        Assert.assertEquals(appManager.getSideMenu().getUserName(),user.getName());
        appManager.getSideMenu().clickOnLogout();

    }

    @Test (dataProvider = "invalidUserSignInFromFile",dataProviderClass = DataProviders.class, enabled = true)
    public void userSignInNegativeTest(User user) throws InterruptedException {
        //Go to SignIn Tab
        appManager.getSignForm().clickOnSignInTab();
        Assert.assertTrue(appManager.getSignForm().isSignInTabPresent());
        appManager.getSignForm().fillSigninUserForm(user);
        appManager.getSignForm().delay(1000);
        appManager.getSignForm().clickSubmitButton();
        logger.info("Logged in result. Actual result: "
                + appManager.getSignForm().isSignInFormPresent()
                + "; expected result: true");
        Assert.assertTrue(appManager.getSignForm().isSignInFormPresent());

    }


}
