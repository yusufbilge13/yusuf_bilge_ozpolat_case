package Tests;

import Pages.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InsiderAutomationTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        //Chrome driver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Yusuf\\Desktop\\Java_Projects\\yusuf_bilge_ozpolat_case\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testInsiderApplicationFlow() throws InterruptedException {
        // 1. Visit https://useinsider.com/ and check Insider home page is opened or not
        HomePage homePage = new HomePage(driver);
        homePage.load();
        Assert.assertTrue("Insider home page did not open properly", homePage.isPageOpened());

        // 2. Select the “Company” menu in the navigation bar, select “Careers” and check Career page, its Locations, Teams, and Life at Insider blocks are open or not
        homePage.navigateToCareers();
        CareersPage careersPage = new CareersPage(driver);
        Assert.assertTrue("Career page blocks are missing", careersPage.areBlocksPresent());

        // 3. Go to https://useinsider.com/careers/quality-assurance/, click “See all QA jobs”, filter jobs by Location: “Istanbul, Turkey”, and Department: “Quality Assurance”, check the presence of the jobs list
        QAJobsPage qaJobsPage = new QAJobsPage(driver);
        qaJobsPage.load();
        qaJobsPage.clickSeeAllJobs();
        qaJobsPage.filterJobs("Istanbul, Turkiye", "Quality Assurance");

        List<?> jobCards = qaJobsPage.getJobCards();
        Assert.assertTrue("No job cards found after filtering", jobCards.size() > 0);

        // 4. Check that all jobs’ Position contains “Quality Assurance”, Department contains “Quality Assurance”, and Location contains “Istanbul, Turkey”
        qaJobsPage.validateJobCards("Istanbul, Turkiye", "Quality Assurance");


        // 5. Click the “View Role” button and check that this action redirects us to the Lever Application form page
        qaJobsPage.clickViewRole();
        ApplicationPage applicationPage = new ApplicationPage(driver);
        Assert.assertTrue("Lever Application form page did not open", applicationPage.isApplicationPageOpened());
    }
}

