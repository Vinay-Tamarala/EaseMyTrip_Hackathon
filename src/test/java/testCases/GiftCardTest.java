package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePage;
import pageObjects.GiftCardPage;
import utilities.ConfigReader;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GiftCardTest {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    GiftCardPage giftCardPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        int explicitWait = Integer.parseInt(ConfigReader.getProperty("explicitWait"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));

        driver.get(ConfigReader.getProperty("baseUrl"));

        homePage = new HomePage(driver, wait);
        giftCardPage = new GiftCardPage(driver, wait);
    }

    //Correct test case, checking working of full Gift Card Details.
    @Test(priority = 1)
    public void testGiftCardPurchaseFlow() {
        homePage.navigateToGiftCard();
        giftCardPage.selectWeddingCard();
        giftCardPage.enterGiftCardDetails(
                ConfigReader.getProperty("amount"),
                ConfigReader.getProperty("quantity"),
                ConfigReader.getProperty("senderName"),
                ConfigReader.getProperty("senderEmail"),
                ConfigReader.getProperty("senderMobile"),
                ConfigReader.getProperty("receiverName"),
                ConfigReader.getProperty("receiverEmail"),
                ConfigReader.getProperty("receiverMobile")
        );
        giftCardPage.clickPayNow();

        String actualOtp = giftCardPage.getOtpMessage();
        Assert.assertEquals(actualOtp,
                ConfigReader.getProperty("expectedOtpMessage"),
                "OTP message validation failed!");
    }

    @Test(priority = 2)
    public void testInvalidAmount() {
        homePage.navigateToGiftCard();
        giftCardPage.selectWeddingCard();

        // Entering invalid amount (below 500)
        giftCardPage.enterGiftCardDetails(
                ConfigReader.getProperty("incorrectAmount"),
                ConfigReader.getProperty("quantity"),
                ConfigReader.getProperty("senderName"),
                ConfigReader.getProperty("senderEmail"),
                ConfigReader.getProperty("senderMobile"),
                ConfigReader.getProperty("receiverName"),
                ConfigReader.getProperty("receiverEmail"),
                ConfigReader.getProperty("receiverMobile")
        );

        String errorMessage = wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//p[contains(.,'Voucher amount should be between 500 and 50000.')]")))
                .getText();

        Assert.assertEquals(errorMessage,
                "Voucher amount should be between 500 and 50000.",
                "Error message validation failed for invalid amount!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
