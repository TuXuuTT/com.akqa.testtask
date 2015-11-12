package com.akqa.pages.outlook;

import com.akqa.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import static org.testng.Assert.assertTrue;

public class OutlookLoginPage extends BasePage {

    @FindBy(css = "#idSIButton9")
    private WebElement buttonSignIn;

    @FindBy(css = "#i0116")
    private WebElement inputEmail;

    @FindBy(css = "#i0118")
    private WebElement inputPassword;

    @FindBy(css = "#idLbl_PWD_KMSI_Cb")
    private WebElement labelKeepSignedIn;

    @FindBy(css = "#idA_PWD_ForgotPassword")
    private WebElement buttonForgotPassword;

    @FindBy(css = "#idTd_Tile_ErrorMsg_Login")
    private WebElement errorMessage;

    public OutlookLoginPage(WebDriver wd) {
        super(wd);
    }

    public OutlookLoginPage loadPage() {
        load(BasePage.getProps().getProperty("outlook.url"));
        return this;
    }

    @Step
    public void verifyLoginWithValidCredentials(String email, String password, boolean rememberMe) {
        InboxPage inbox = (InboxPage) validSignIn(email, password, rememberMe);
        inbox.verifyPageLayout();
        assertTrue(inbox.isLoggedIn(email), String.format("Your user %s is not logged in", email));
        inbox.signOut();
    }

    @Step
    public void verifyLoginWithInvalidCredentials(String email, String password, String errormsg) {
        invalidSignIn(email, password);
        waitForVisibility(errorMessage);
        assertTrue(errorMessage.getText().contains(errormsg), "Error is displayed but does not contain required message");
    }

    @Step
    public void verifyForgottenPasswordFunctionality() {
        click(buttonForgotPassword);
        new ForgotPasswordPage(getWebDriverCurrent()).checkResetPasswordPageOpenedAndCancel();
    }

    public OutlookLoginPage validSignIn(String email, String password, boolean rememberMe) {
        fillCredentialsAndProceed(email, password, rememberMe);
        return new InboxPage(getWebDriverCurrent());
    }

    public OutlookLoginPage invalidSignIn(String email, String password) {
        fillCredentialsAndProceed(email, password, false);
        return this;
    }

    protected void fillCredentialsAndProceed(String email, String password, boolean rememberMe) {
        waitForVisibility(buttonSignIn);
        sendKeys(waitForVisibility(inputEmail), email);
        sendKeys(waitForVisibility(inputPassword), password);
        if (rememberMe) {
            click(labelKeepSignedIn);
        }
        click(buttonSignIn);
    }

}
