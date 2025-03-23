package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersPage extends BasePage {

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public boolean areBlocksPresent() {
        try {
            //Teams
            waitForElement(By.xpath("//*[@data-id=\"4a40266\"]"));
            //Our Locations block
            waitForElement(By.xpath("//*[@data-id=\"38b8000\"]"));
            //Life at Insider block
            waitForElement(By.xpath("//*[@data-id=\"a8e7b90\"]"));
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}

