package com.akqa.pages.outlook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage extends OutlookLoginPage {

    @FindBy(css = "input[name=query]")
    private WebElement inputSearchInbox;

    @FindBy(css = "#c_meun")
    private WebElement buttonAccountMenu;

    @FindBy(css="#c_memenu")
    private WebElement labelAccountMenu;

    @FindBy(css = "#c_signout")
    private WebElement buttonSignOut;

    @FindBy(css = "#icTmMeControlMedium0_usertile")
    private WebElement userTile;

    @FindBy(css="#icTmMeControlMedium0_bar")
    private WebElement userOnlineBar;

    @FindBy(css="#icTmMeControlMedium0_usertilecontainer")
    private WebElement userTileContainer;

    private By logoBy = By.cssSelector(".c_clogoimg");

    public InboxPage(WebDriver wd) {
        super(wd);
    }

    protected InboxPage verifyPageLayout() {
        waitForVisibility(inputSearchInbox);
        waitForVisibility(buttonAccountMenu);
        return this;
    }

    protected boolean isLoggedIn(String email) {
        waitForVisibility(userTile);
        waitForVisibility(userOnlineBar);
        waitForVisibility(userTileContainer);
        return (userTile.getAttribute("alt").equals(email));
    }

    protected OutlookLoginPage signOut() {
        click(buttonAccountMenu);
        waitForVisibility(labelAccountMenu);
        click(buttonSignOut);
//        waitForInvisibility(logoBy);
        return new OutlookLoginPage(getWebDriverCurrent()).loadPage();
    }

}
