package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
        TITLE = "id:org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
        MY_LIST_BY_NAME_TPL = "xpath://*[@text='{LIST_NAME}']",
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATE METHODS*/
    private static String getMyListByNameXpath(String list_name)
    {
        return MY_LIST_BY_NAME_TPL.replace("{LIST_NAME}", list_name);
    }
    /*TEMPLATE METHODS*/

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public void assertTitleElementIsPresent()
    {
        this.assertElementPresent(TITLE, "Cannot find article title element on the page");
    }


    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(FOOTER_ELEMENT,"Cannot find the end of article", 20);
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options.",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find the option to add the article to reading list.",
                5
        );

        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY, "Cannot find 'Got it' overlay", 5);

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set the article folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into  article folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "cannot press OK button",
                5
        );
    }

    public void addArticleToExistingFolder(String folder_name){

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options.",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find the option to add the article to reading list.",
                5
        );

        String myListByNameXpath = getMyListByNameXpath(folder_name);

        this.waitForElementAndClick(
                myListByNameXpath,
                "Cannot find the existing reading list " + folder_name,
                5
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close the article. Cannot find X link",
                5
        );
    }
}
