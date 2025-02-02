package tests;

import lib.CoreTestCase;
import org.junit.Test;
import lib.ui.WelcomePageObject;

public class GetStartedTest extends CoreTestCase
{
    @Test
    public void  testPassThroughWelcome()
    {
        if (Platform.getInstance().isAndroid()){
            return;
        }
        WelcomePageObject welcomePage = new WelcomePageObject(driver);

        welcomePage.waitForLearnMoreLink();
        welcomePage.clickNextButton();

        welcomePage.waitForNewWayToExplore();
        welcomePage.clickNextButton();

        welcomePage.waitForAddOrEditPreferredLangText();
        welcomePage.clickNextButton();

        welcomePage.waitForLearnMoreAboutDataCollectedText();
        welcomePage.clickGetStartedButton();
    }
}
