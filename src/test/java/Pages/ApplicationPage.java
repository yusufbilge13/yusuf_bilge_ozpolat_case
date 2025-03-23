package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApplicationPage extends BasePage {

    public ApplicationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isApplicationPageOpened() {
        String originalWindow = driver.getWindowHandle();

        // Yeni pencere/sekmenin açılmasını bekleyin (örneğin, 10 saniye)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Açık olan tüm pencere tanımlayıcılarını kontrol edip, orijinal pencere dışındaki pencereye geçiş yapın
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        return driver.getCurrentUrl().contains("lever.co");
    }
}

