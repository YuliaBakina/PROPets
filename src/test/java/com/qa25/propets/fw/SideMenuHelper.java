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

    public String getUserName(){
        return getText(By.className("user_item__3udbp"));
    }

    public void clickOnLogout() {
        click(By.cssSelector("[href='/start']"));
    }

}
