package com.qa25.propets.Tests;

import com.qa25.propets.Model.User;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserSignInTests extends TestBase{
    String login = "api/auth/login";

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
    public void userSignInPositiveTest(User user) throws InterruptedException, IOException {
        //Go to SignIn Tab
        appManager.getSignForm().clickOnSignInTab();
        Assert.assertTrue(appManager.getSignForm().isSignInTabPresent());
        appManager.getSignForm().fillSigninUserForm(user);
        appManager.getSignForm().delay(1000);
        appManager.getSignForm().clickSubmitButton();

        //check if user signed in
        String[] split = user.getName().split(" ");

        logger.info("User Logged in. Actual result: "
                + appManager.getSideMenu().getUserName()
                + "; expected result: " + split[0]);
        Assert.assertEquals(appManager.getSideMenu().getUserName(),split[0]);
        appManager.getSideMenu().clickOnLogout();

        //API test
        int statusCode = Request.Post(appManager.getBaseURL() + login)
                .bodyString("{\n" +
                        "            \"email\": \"" + user.getEmail() + "\",\n" +
                        "            \"password\": \"" + user.getPassword() + "\"\n" +
                        "        }", ContentType.APPLICATION_JSON)
                .execute().returnResponse().getStatusLine().getStatusCode();

        logger.info("!!! Rest API test --> Actual status Code = " + statusCode + ". Expected status Code = 200.");
        Assert.assertEquals(statusCode, 200);

    }

    @Test (dataProvider = "invalidUserSignInFromFile",dataProviderClass = DataProviders.class, enabled = true)
    public void userSignInNegativeTest(User user) throws InterruptedException, IOException {
        //Go to SignIn Tab
        appManager.getSignForm().clickOnSignInTab();
        Assert.assertTrue(appManager.getSignForm().isSignInTabPresent());
        appManager.getSignForm().fillSigninUserForm(user);
        appManager.getSignForm().delay(1000);
        appManager.getSignForm().clickSubmitButton();
        logger.info("Logged in result. User is not logged in: "
                + appManager.getSignForm().isSignInFormPresent()
                + "; expected result: true");
        Assert.assertTrue(appManager.getSignForm().isSignInFormPresent());

        //API test
        int statusCode = Request.Post(appManager.getBaseURL() + login)
                .bodyString("{\n" +
                                "            \"email\": \"" + user.getEmail() + "\",\n" +
                                "            \"password\": \"" + user.getPassword() + "\"\n" +
                                "        }", ContentType.APPLICATION_JSON)
                .execute().returnResponse().getStatusLine().getStatusCode();

        logger.info("!!! Rest API test --> Actual status Code = " + statusCode + ". Expected status Code = 400.");
        Assert.assertEquals(statusCode, 400);

    }


}
