package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.factories.SearchPageObjectFactory;

abstract public class NavigationUI extends MainPageObject
{
    protected static  String
        MY_LISTS_LINK; //= "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyLists()
    {
        this.waitForElementAndClick(MY_LISTS_LINK, "Cannot find navigation button to My list", 5);
    }

    public void goToTheArticle(String input_search, String article_text_in_search_result) {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(input_search);
        SearchPageObject.clickByArticleWithSubstring(article_text_in_search_result);
    }

}
