package com.qa25.propets.fw;

import com.qa25.propets.Model.User;
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

    public void clickCancelButton(){
        click(By.className("Button_cancelBtn__2a17z"));
    }

    public void clickSubmitButton(){
        click(By.className("Button_submitBtn__ZrQff"));
    }

    public void clickForgotPassLink(){
        click(By.cssSelector("[href='/forgot']"));
    }

    public void clickOnSignInTab() {
        click(By.xpath("//div//div[@class='signingPageTabs_button__3gUPX signingPageTabs_signBtn__3Ymvo']"));
    }

    public boolean isSignInTabPresent(){
        return isElementPresent(By.cssSelector("[href='/forgot']"));
    }

    public void clickOnSignUpTab() {
        click(By.xpath("//div//div[@class='signingPageTabs_button__3gUPX signingPageTabs_signBtn__3Ymvo']"));
    }

    public boolean isSignUpTabPresent(){
        return !(isElementPresent(By.cssSelector("[href='/forgot']")));
    }

    public void fillSigninUserForm(User user) {
        type(By.name("email"), user.getEmail());
        type(By.name("password"), user.getPassword());
    }

    public void fillSignupUserForm(User user) {
        type(By.name("name"), user.getName());
        type(By.name("email"), user.getEmail());
        type(By.name("password"), user.getPassword());
        type(By.name("confirmPassword"), user.getPasswordConfirm());
    }

}
