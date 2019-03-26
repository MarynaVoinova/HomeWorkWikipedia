package tests.iOS;

import lib.iOSTestCase;
import org.junit.Test;
import ui.WelcomePageObject;

public class GetStartedTest extends iOSTestCase
{
    @Test
    public void  testPassThroughWelcome()
    {
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
