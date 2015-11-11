package com.akqa.automation;

import com.akqa.pages.AkqaWorkPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by TuXuu on 11.11.2015.
 * <p>
 * This Test class contains solutions for both tasks - basic and advanced
 * </p>
 */
public class TestAkqaTask extends BaseTest {

    private AkqaWorkPage akqaWorkPage;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        akqaWorkPage = new AkqaWorkPage(getWdInstance());
    }

    //Test contains verification method with asserts inside which will check images size and menu layout accordingly with different browser sizes, specifically widths. Changing browser height does not affect page layout
    @Test
    public void testAkqaWorkPageResponsiveDesign() throws InterruptedException {
        akqaWorkPage.verifyImagesChangeSizeAccordinglyToBrowserWindow(new int[]{1000, 769, 500});
        akqaWorkPage.maximizeWebdriverWindow();
        akqaWorkPage.verifyMenuChangesLayoutAccordinglyToBrowserWindow(new int[]{1000, 600, 500, 300});
    }
}
