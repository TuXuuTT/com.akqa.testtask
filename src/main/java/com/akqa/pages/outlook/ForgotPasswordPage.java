package com.akqa.pages.outlook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertEquals;

public class ForgotPasswordPage extends OutlookLoginPage {

    @FindBy(css = "#resetPwdWhyTitle")
    private WebElement titleResetPasswd;

    @FindBy(css = "#resetPwdWhyCancel")
    private WebElement buttonResetPasswdCancel;

    public ForgotPasswordPage(WebDriver wd) {
        super(wd);
    }

    public OutlookLoginPage checkResetPasswordPageOpenedAndCancel() {
        waitForVisibility(titleResetPasswd);
        assertEquals(titleResetPasswd.getText(), "Why can't you sign in?");
        click(buttonResetPasswdCancel);
        return new OutlookLoginPage(getWebDriverCurrent());
    }
}
