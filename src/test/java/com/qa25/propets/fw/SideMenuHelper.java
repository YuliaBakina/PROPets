package com.qa25.propets.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideMenuHelper extends HelperBase {

    public SideMenuHelper(WebDriver wd) {
        super(wd);
    }

    public boolean isLogoutPresent(){
        return isElementPresent(By.cssSelector("[href='/start']"));
    }

    public void clickOnLogout() {
        click(By.cssSelector("[href='/start']"));
    }

}
