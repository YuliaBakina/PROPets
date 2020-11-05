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

}
