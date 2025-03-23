package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class QAJobsPage extends BasePage {
    private String URL = "https://useinsider.com/careers/quality-assurance/";

    public QAJobsPage(WebDriver driver) {
        super(driver);
    }

    public void load() {
        driver.get(URL);
    }

    public void clickSeeAllJobs() {
        WebElement seeAllButton = waitForElement(By.xpath("//a[contains(text(), 'See all QA jobs')]"));
        seeAllButton.click();
    }

    public void filterJobs(String location, String department) throws InterruptedException {

        Thread.sleep(3000);
        WebElement locationFilter = waitForElement(By.xpath("//span[@id = 'select2-filter-by-location-container']"));
        locationFilter.click();

        Thread.sleep(3000);
        WebElement locationSelection = waitForElement(By.xpath("//select[@id = 'filter-by-location']"));
        Select locationSelect = new Select(locationSelection);
        locationSelect.selectByVisibleText(location);


        WebElement departmentFilter = waitForElement(By.xpath("//span[@id = 'select2-filter-by-department-container']"));
        departmentFilter.click();

        WebElement departmentSelection = waitForElement(By.xpath("//select[@id = 'filter-by-department']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", departmentSelection);

        Select departmentSelect = new Select(departmentSelection);
        departmentSelect.selectByVisibleText(department);
        departmentFilter.click();


        // checking the presence of the jobs list
        waitForElement(By.xpath("//div[contains(@id, 'jobs-list')]"));
        WebElement jobCardsSection = driver.findElement(By.xpath("//div[contains(@id, 'jobs-list')]"));

        //Scroll down the page and wait for the Job Cards to appear
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", jobCardsSection);
        Thread.sleep(2000);
    }

    public List<WebElement> getJobCards() {
        return driver.findElements(By.xpath("//div[contains(@id, 'jobs-list')]/div"));

    }

    public void validateJobCards(String location, String department) {
        List<WebElement> jobCards = getJobCards();

        for (WebElement card : jobCards) {
            String positionText = card.findElement(By.xpath(".//p")).getText();
            String deptText = card.findElement(By.xpath(".//span[contains(@class, 'department')]")).getText();
            String locationText = card.findElement(By.xpath(".//div[contains(@class, 'location')]")).getText();

            if ((!positionText.contains("Quality Assurance")) && (!positionText.contains("QA"))) {
                throw new AssertionError("Position text does not contain 'Quality Assurance' or 'QA': " + positionText);
            }
            if (!deptText.contains(department)) {
                throw new AssertionError("Department text does not contain " + department + ": " + deptText);
            }
            if (!locationText.contains(location)) {
                throw new AssertionError("Location text does not contain " + location + ": " + locationText);
            }
        }
    }

    public void clickViewRole() {
        WebElement viewRoleButton = getJobCards().getFirst().findElement(By.xpath(".//a"));
        Actions actions = new Actions(driver);
        actions.moveToElement(viewRoleButton).perform();

        viewRoleButton.click();
    }
}

