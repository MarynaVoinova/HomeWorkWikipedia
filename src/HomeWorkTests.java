import lib.CoreTestCase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.*;

import java.util.List;


public class HomeWorkTests extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearchInputContainsDefaultText(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        WebElement search_element = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find Search element",
                5
        );
        String search_text = search_element.getAttribute("text");
        assertEquals(
                "The input string does not contain Search",
                "Search…",
                search_text
        );
        assertTrue(
                "The element does not contain searched element",
                MainPageObject.isTextPresentInElement(search_element, "Search…")
        );
    }




    @Test
    public void testThatTitleIsPresent(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String search_line = "Java";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find init search",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot not find 'Object-oriented programming language' topic searching by " + search_line,
                15
        );

        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Title is not found"
        );

    }

    @Test
    public void testSaveTwoArticlesToMyListAndDeleteOne(){
        goToTheArticle("Java", "Object-oriented programming language");
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "cannot find the option to add the article to reading list",
                5
        );

        String name_of_folder = "Learning programming";
        createNewFolder(name_of_folder);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close the article. Cannot find X link",
                5
        );

        String not_deleted_artickle_title = "C Sharp (programming language)";
        goToTheArticle("C#", not_deleted_artickle_title);

        addArticleToExistingFolder(name_of_folder);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close the article. Cannot find X link",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation to My list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='"+ name_of_folder +"']"),
                "Cannot find created folder",
                5
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='"+ not_deleted_artickle_title +"']"),
                "Cannot not find '"+ not_deleted_artickle_title +"' after deletion" ,
                15
        );
    }

    private void createNewFolder(String folder_name){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' overlay",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set the article folder",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                folder_name,
                "Cannot put text into  article folder input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "cannot press OK button",
                5
        );

    }

    private void addArticleToExistingFolder(String folder_name){

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                //By.xpath("//*[@index ='2']"),
                "cannot find the option to add the article to reading list",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='"+ folder_name +"']"),
                "Cannot find the existing reading list: " + folder_name,
                5
        );

    }

    private void goToTheArticle(String input_search, String artickle_text_in_search_result){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                input_search,
                "Cannot find init search: " + input_search,
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='"+ artickle_text_in_search_result +"']"),
                "Cannot not find '"+artickle_text_in_search_result+"' topic searching by " + input_search,
                15
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
    }
}
