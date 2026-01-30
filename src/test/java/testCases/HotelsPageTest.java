package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.GiftCardPage;
import pageObjects.HomePage;
import pageObjects.HotelsPage;
import testBase.BaseTest;
import utilities.ConfigReader;

import java.time.Duration;

public class HotelsPageTest extends BaseTest {
    WebDriverWait wait;
    WebDriver driver;
    HotelsPage hotelsPage;
    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        int explicitWait = Integer.parseInt(ConfigReader.getProperty("explicitWait"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));

        driver.get(ConfigReader.getProperty("baseUrl"));

        hotelsPage = new HotelsPage(driver, wait);
    }
    @Test(priority = 3){
        public void testHotelsPage(){
            hotelsPage.HotelAction();

        }
    }
}
