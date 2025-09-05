package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--window-size=1400,900");
        driver = new ChromeDriver(opts); // Initialize the WebDriver
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait with a 10-second timeout
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser and end the session
        }
    }
}
