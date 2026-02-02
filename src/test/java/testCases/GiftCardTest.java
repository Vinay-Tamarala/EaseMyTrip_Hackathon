package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.HomePage;
import pageObjects.GiftCardPage;
import utilities.ConfigReader;
import testBase.BaseTest;

public class GiftCardTest extends BaseTest {

    // Correct test case, checking working of full Gift Card Details.
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
    @Test(priority = 3)
    public void testLaterButton(){
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
        giftCardPage.selectLaterButton();
        giftCardPage.selectTomorrowDate();
        giftCardPage.clickPayNow();
        String actualOtp = giftCardPage.getOtpMessage();
        Assert.assertEquals(actualOtp,
                ConfigReader.getProperty("expectedOtpMessage"),
                "OTP message validation failed!");
    }
    @Test(priority = 4)
    public void testUnselectedLaterButton(){
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
        giftCardPage.selectLaterButton();
        giftCardPage.clickPayNow();

        String errorMessage = wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//span[.='Select date.']")))
                .getText();

        Assert.assertEquals(errorMessage,
                "Select date.",
                "Error message validation failed for later date calendar");

    }

}
