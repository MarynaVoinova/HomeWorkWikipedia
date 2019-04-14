package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject
{
    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_ELEMENT,
        SEARCH_EMPTY_RESULT_ELEMENT,
        SEARCH_RESULT_LIST_ITEM,
        SEARCH_RESULT_TITLE_TPL,
        DELETE_ME_TITLE;

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATE METHODS*/
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultByTitleAndDescription_xPath(String title, String description){

        return SEARCH_RESULT_TITLE_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }

    private static String getResultByTitleDELETEME_xPath(String title){

        return DELETE_ME_TITLE.replace("{TITLE}", title);
    }

    /*TEMPLATE METHODS*/

    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search element.",5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find  search input after clicking search init element.");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button.", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present.", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button.",5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,"Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String searchResult_xPath = getResultSearchElement(substring);
        this.waitForElementPresent(searchResult_xPath, "Cannot find search result with substring: " + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String searchResult_xPath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResult_xPath, "Cannot find  and click search result with substring: " + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request.",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element by the request.",
                15
        );
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public void assertSearchListResultsContainSearchString(List<WebElement> search_result_lists, String search_String)
    {
        this.AssertSubStringIsPresentInListOfWebElements(search_result_lists, search_String);
    }


    public List<WebElement> getSearchResultList()
    {
        return this.waitForElementsPresent(
                SEARCH_RESULT_LIST_ITEM,
                "Cannot not find any result to the request",
                15
        );
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String searchResultByTitleAndDescription_xPath = getResultByTitleAndDescription_xPath(title, description);
        this.waitForElementPresent(
                searchResultByTitleAndDescription_xPath,
                "Cannot find search result with title : " + title + " and description: " + description);
    }

    public void waitForAndDeleteBYTITLE(String title)
    {
        String delete = getResultByTitleDELETEME_xPath(title);
        this.waitForElementPresent(
                getResultByTitleDELETEME_xPath(delete),
                "Cannot find by title: " + title,
                5);
    }

}
