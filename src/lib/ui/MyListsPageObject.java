package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject
{
    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    protected static  String
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']",
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folderNameXPath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folderNameXPath,
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String articleTitleXPath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                articleTitleXPath,
                "Cannot find saved article by title " + article_title,
                15);
    }

    public void waitForArticleToDisappear(String article_title)
    {
        String articleTitleXPath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                articleTitleXPath,
                "Saved article is still present with title " + article_title,
                15);
    }

    public void swipeByArticleToDelete(String article_title)
    {

        waitForArticleToAppearByTitle(article_title);
        String articleTitleXPath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(articleTitleXPath, "Cannot find saved article");
        //this.waitForElementNotPresent(articleTitleXPath, "Cannot delete saved article", 5);
        if (Platform.getInstance().isIOS()){
           this.ClickElementRoTheRightUpperCorner(articleTitleXPath, "Cannot find saved article");
        }
        this.waitForArticleToDisappear(article_title);
    }
}
