package ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject
{
    private static final String
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]",
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']",
        SEARCH_RESULT_LIST_ITEM = "id:org.wikipedia:id/page_list_item_title",
        SEARCH_RESULT_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']/following-sibling::[@text='{DESCRIPTION}']",
        DELETE_ME_TITLE ="xpath:org.wikipedia:id/page_list_item_title";
    /*
    ////"ancestor::*[*[@text="foo"]][@resource-id='org.wikipedia:id/page_list_item_container']"
    //ancestor::*[*[@text="foo"]][@text="bar"]
    SEARCH_RESULT_TITLE_TPL = SEARCH_RESULT_LIST_ITEM_CONTAINER+"[]"
                "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']" +
                SEARCH_RESULT_LIST_ITEM_CONTAINER +
                "[@resource-id='org.wikipedia:id/page_list_item_description']//*[@text='{DESCRIPTION}']",
        SEARCH_RESULT_DESCRIPTION_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description']//*[@text='{DESCRIPTION}']";
*/
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
