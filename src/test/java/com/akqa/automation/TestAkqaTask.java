package com.akqa.automation;

import com.akqa.pages.AkqaWorkPage;
import com.akqa.pages.outlook.OutlookLoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by TuXuu on 11.11.2015.
 * <p>
 * This Test class contains solutions for both tasks - basic and advanced.
 *
 * In case of many test scenarios they should be divided into separate Test classes, but here they are stored together to be more visual
 * </p>
 */
public class TestAkqaTask extends BaseTest {

    private AkqaWorkPage akqaWorkPage;
    private OutlookLoginPage outlookLoginPage;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
    }

    /*Test contains verification method with asserts inside which will check images size and menu layout accordingly with different browser sizes, specifically widths. Changing browser height does not affect page layout
     */
    @Test
    public void testAkqaWorkPageResponsiveDesign() {
        akqaWorkPage = new AkqaWorkPage(getWdInstance());
        akqaWorkPage.verifyImagesChangeSizeAccordinglyToBrowserWindow(new int[]{1000, 769, 500});
        akqaWorkPage.maximizeWebdriverWindow();
        akqaWorkPage.verifyMenuChangesLayoutAccordinglyToBrowserWindow(new int[]{1000, 600, 500, 300});
        akqaWorkPage.maximizeWebdriverWindow();
    }

    /*Test contains verification method with asserts inside which will sign in with valid credentials and "keep signed in" checkbox checked and will verify that user is logged and, and then sign out
     */
    @Test
    public void testOutlookPositiveScenario() {
        outlookLoginPage = new OutlookLoginPage(getWdInstance()).loadPage();
        outlookLoginPage.verifyLoginWithValidCredentials("akqadummyacc@outlook.com", "P@sswordP@ssword", true);
    }

    /*Test contains verification method with asserts inside which will check error message when user attempts to login with invalid password
     */
    @Test
    public void testOutlookNegativeScenario() {
        outlookLoginPage = new OutlookLoginPage(getWdInstance()).loadPage();
        String wrongPasswordMessage = "That password is incorrect. Be sure you're using the password for your Microsoft account.";
        outlookLoginPage.verifyLoginWithInvalidCredentials("akqadummyacc@outlook.com", "password", wrongPasswordMessage);
    }

    /*Test contains verification method with asserts inside which will check if restore password specific page is displayed after clicking "forgot password" specific button
     */
    @Test
    public void testOutlookForgottenPassword() {
        outlookLoginPage = new OutlookLoginPage(getWdInstance()).loadPage();
        outlookLoginPage.verifyForgottenPasswordFunctionality();
    }
}
