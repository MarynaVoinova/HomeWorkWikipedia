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

}
