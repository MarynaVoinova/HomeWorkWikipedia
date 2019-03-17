package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject
{
    private static final String
        MY_LISTS_LINK = "//android.widget.FrameLayout[@content-desc='My lists']";
    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyLists()
    {
        this.waitForElementAndClick(By.xpath(MY_LISTS_LINK), "Cannot find navigation button to My list", 5);
    }

    public void goToTheArticle(String input_search, String article_text_in_search_result) {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(input_search);
        SearchPageObject.clickByArticleWithSubstring(article_text_in_search_result);
    }

}
