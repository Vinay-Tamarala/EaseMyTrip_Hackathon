package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utilities.ConfigReader;
import pageObjects.HomePage;
import pageObjects.GiftCardPage;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    protected HomePage homePage;
    protected GiftCardPage giftCardPage;

    @BeforeClass
    public void setUp() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        String baseUrl = ConfigReader.getProperty("baseUrl");
        int implicitWait = Integer.parseInt(ConfigReader.getProperty("implicitWait"));
        int explicitWait = Integer.parseInt(ConfigReader.getProperty("explicitWait"));

        switch (browser) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));

        driver.get(baseUrl);
        homePage = new HomePage(driver, wait);
        giftCardPage = new GiftCardPage(driver, wait);

        System.out.println("Running tests on: " + browser.toUpperCase());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
