package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private String URL = "https://useinsider.com/";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void load() {
        driver.get(URL);
    }

    public boolean isPageOpened() {
        try {
            waitForElement(By.xpath("//li[@class='nav-item dropdown'][6]//a[@id='navbarDropdownMenuLink']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToCareers() {
        WebElement cookiesAcceptAll = waitForElement(By.xpath("//a[@id='wt-cli-accept-all-btn']"));
        cookiesAcceptAll.click();
        WebElement companyMenu = waitForElement(By.xpath("//li[@class='nav-item dropdown'][6]//a[@id='navbarDropdownMenuLink']"));
        companyMenu.click();
        WebElement careersMenu = waitForElement(By.xpath("//a[normalize-space()='Careers']"));
        careersMenu.click();
    }
}

