package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject
{
    private static final String
        STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOOUT_DATA_COLLECTION_LINK = "id:Learn more about data collection",
        NEXT_LINK = "id:Next",
        GET_STARTED_BUTTON = "id:Get started",
        //SKIP = "id:Skip";
        SKIP = "xpath://XCUIElementTypeButton[@name='Skip']";
    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wilipedia' link",10);

    }

    public void waitForNewWayToExplore()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' link",10);

    }

    public void waitForAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK, "Cannot find 'Add or edit preferred languages' link",10);

    }

    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOOUT_DATA_COLLECTION_LINK, "Cannot find 'Learn more about data collection\"' link",10);

    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find click 'Next' link",10);

    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find click 'Get started' button",10);

    }

    public void clickSkip()
    {
        this.waitForElementAndClick(SKIP,"Cannot  find and click Skip button", 5);
    }

}
