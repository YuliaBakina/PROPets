package com.qa25.propets.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd){
        this.wd = wd;
    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size()>0;
    }

    public void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear(); //- doesn't work for Login form

/*        wd.findElement(locator).sendKeys(Keys.CONTROL + "a");
        wd.findElement(locator).sendKeys(Keys.DELETE);*/

        if(text != null){
            wd.findElement(locator).sendKeys(text);
        }
    }

    public void click(By locator){
        wd.findElement(locator).click();
    }

    public boolean isSignInFormPresent(){
        return isElementPresent(By.className("auth_page_form__3y7rh"));
    }

    public void delay(int timeout) throws InterruptedException {
        Thread.sleep(timeout);
    }

}
