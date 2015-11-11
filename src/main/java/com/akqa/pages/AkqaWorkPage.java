package com.akqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

/**
 * Created by TuXuu on 11.11.2015.
 */
public class AkqaWorkPage extends BasePage {
    private static final int WIDTH_WHEN_IMAGES_SHOULD_CHANGE_LAYOUT = 769; //value is taken from css rule on the page
    private static final int WIDTH_WHEN_MENU_SHOULD_CHANGE_LAYOUT = 600; //value is taken from css rule on the page

    @FindBy(css = "section.work-list")
    private WebElement blockWorkFeed;

    @FindAll(@FindBy(css = "article img"))
    private List<WebElement> articleImages;

    @FindBy(css = "article.in-view img")
    private WebElement articleInViewImg;

    @FindBy(css = ".header .nav ul")
    private WebElement horizontalMenuList;

    @FindBy(css = ".header .nav .msg-menu")
    private WebElement buttonVerticalMenuOpen;

    @FindBy(css = ".dropdown-menu-content")
    private WebElement dropdownMenuLists;

    @FindBy(css = ".header .nav .msg-close")
    private WebElement buttonVerticalMenuClose;

    public AkqaWorkPage(WebDriver wd) {
        super(wd);
        load();
    }

    public void verifyImagesChangeSizeAccordinglyToBrowserWindow(int[] customBrowserWidths) {
        waitForVisibility(blockWorkFeed);
        assertTrue(isImageSizeCorrespondsBrowserSize(), "Images are not changing their size accordingly");
        for (int i : customBrowserWidths) {
            setWebDriverWindowSize(i, getWebDriverWindowsSize().getHeight());
            assertTrue(isImageSizeCorrespondsBrowserSize(), "Images are not changing their size accordingly");
        }
    }

    public void verifyMenuChangesLayoutAccordinglyToBrowserWindow(int[] customBrowserWidths) {
        waitForVisibility(horizontalMenuList);
        assertTrue(isMenuLayoutCorrespondsBrowserSize(), "Menu layout is not as expected");
        for (int i : customBrowserWidths) {
            setWebDriverWindowSize(i, getWebDriverWindowsSize().getHeight());
            assertTrue(isMenuLayoutCorrespondsBrowserSize(), "Menu layout is not as expected");
        }
    }

    protected int getImagesWidth() {
        Set<Integer> widths = new HashSet<>();
        int imageSizeWidth = 0;
        waitForVisibility(articleInViewImg);
        for (WebElement image : articleImages) {
            imageSizeWidth = image.getSize().getWidth();
            widths.add(imageSizeWidth);
        }
        //images should have equal size so set of them will contain just 1 unique element which size is equal for each image
        assertTrue(widths.size() == 1, "Images are not the same size");
        return imageSizeWidth;
    }

    protected boolean isImageSizeCorrespondsBrowserSize() {
        if (getWebDriverWindowsSize().getWidth() > WIDTH_WHEN_IMAGES_SHOULD_CHANGE_LAYOUT) {
            return ((Math.abs(2 * getImagesWidth() - getPageBodyWidth()) < 10));
        } else if (getWebDriverWindowsSize().getWidth() <= WIDTH_WHEN_IMAGES_SHOULD_CHANGE_LAYOUT) {
            return ((Math.abs(getImagesWidth() - getPageBodyWidth()) < 10));
        }
        return false;
    }

    protected boolean isMenuLayoutCorrespondsBrowserSize() {
        if (getWebDriverWindowsSize().getWidth() > WIDTH_WHEN_MENU_SHOULD_CHANGE_LAYOUT) {
            return (isElementPresent(horizontalMenuList));
        } else if (getWebDriverWindowsSize().getWidth() <= WIDTH_WHEN_MENU_SHOULD_CHANGE_LAYOUT) {
            boolean a = isElementPresent(buttonVerticalMenuOpen);
            click(buttonVerticalMenuOpen);
            boolean b = isElementPresent(waitForVisibility(dropdownMenuLists));
            click(buttonVerticalMenuClose);
            return a && b;
        }
        return false;
    }
}
