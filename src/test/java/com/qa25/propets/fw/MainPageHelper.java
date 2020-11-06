package com.qa25.propets.fw;

import com.qa25.propets.fw.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageHelper extends HelperBase {

    public MainPageHelper(WebDriver wd) {
        super(wd);
    }

    public void isMainPageOpened(){
        if(!wd.getCurrentUrl().equalsIgnoreCase("https://propets-mern.herokuapp.com/")){
            wd.navigate().to("https://propets-mern.herokuapp.com/");
        }
    }

    public boolean isMainPagePresented(){
        return isElementPresent(By.className("wellcome_leftSide__4EtcI"));
    }

    public void clickOnHeaderLogo() {
        click(By.cssSelector("[href='/home']"));
    }

    public void clickOnHeaderSignInButton() {
        click(By.cssSelector("[href='/login']"));
    }

    public void clickOnLostButton() {
       click(By.xpath("//div[text()='I lost my pet!']"));
    }

    public void clickOnFoundButton() {
        click(By.xpath("//div[text()='I found a pet!']"));
    }

    public void clickOnJoinLink() {
        click(By.xpath("//a[text()='JOIN']"));
    }

}
