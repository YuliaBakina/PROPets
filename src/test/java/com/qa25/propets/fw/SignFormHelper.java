package com.qa25.propets.fw;

import com.qa25.propets.fw.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignFormHelper extends HelperBase {

    public SignFormHelper(WebDriver wd) {
        super(wd);
    }

    public void clickXButton(){
        click(By.className("closeIcon"));
    }

    public void clickOnSignInTab() {
        click(By.xpath("//div//div[@class='signingPageTabs_button__3gUPX signingPageTabs_signBtn__3Ymvo']"));
      //  click(By.className("signingPageTabs_button__3gUPX signingPageTabs_signBtn__3Ymvo signingPageTabs_activeSignBtn__H46eI"));
    }

    public void clickOnSignUpTab() {
       // click(By.xpath("//div[text()='Sign up']"));
    }

}
