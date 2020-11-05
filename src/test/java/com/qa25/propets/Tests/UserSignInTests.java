package com.qa25.propets.Tests;

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
    public void userSignInPositiveTest() throws InterruptedException {
        //Go to SignIn Tab
        appManager.getSignForm().clickOnSignInTab();
        appManager.getSignForm().delay(2000);


    }

}
