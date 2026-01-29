package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GiftCardPage {
    WebDriver driver;
    WebDriverWait wait;

    public GiftCardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@alt='Wedding']")
    private WebElement weddingCard;

    @FindBy(xpath = "//input[@placeholder='Min 500 -  50000']")
    private WebElement amountField;

    @FindBy(xpath = "//select[@ng-model='User.Quantity']")
    private WebElement quantityDropdown;

    @FindBy(xpath = "//input[@placeholder=\"Sender's name*\"]")
    private WebElement senderName;

    @FindBy(xpath = "//input[@placeholder=\"Sender's email address*\"]")
    private WebElement senderEmail;

    @FindBy(xpath = "//input[@placeholder=\"Sender's 10 digits mobile number*\"]")
    private WebElement senderMobile;

    @FindBy(xpath = "//input[@placeholder=\"Receiver's name*\"]")
    private WebElement receiverName;

    @FindBy(xpath = "//input[@placeholder=\"Receiver's email address*\"]")
    private WebElement receiverEmail;

    @FindBy(xpath = "//input[@placeholder=\"Retype receiver's email address*\"]")
    private WebElement receiverEmailConfirm;

    @FindBy(xpath = "//input[@placeholder=\"Receiver's 10 digits mobile number*\"]")
    private WebElement receiverMobile;

    @FindBy(xpath = "//input[@ng-model='User.Term']")
    private WebElement termsCheckbox;

    @FindBy(xpath = "//div[.='Pay Now']")
    private WebElement payNowButton;

    @FindBy(xpath = "//p[@class='err_msg ng-binding' and contains(text(),'OTP has been sent')]")
    private WebElement otpMessage;

    public void selectWeddingCard() {
        wait.until(ExpectedConditions.elementToBeClickable(weddingCard)).click();
    }

    public void enterGiftCardDetails(String amount, String quantity,
                                     String sName, String sEmail, String sMobile,
                                     String rName, String rEmail, String rMobile) {
        wait.until(ExpectedConditions.visibilityOf(amountField)).sendKeys(amount);
        wait.until(ExpectedConditions.elementToBeClickable(quantityDropdown));
        new Select(quantityDropdown).selectByValue(quantity);

        wait.until(ExpectedConditions.visibilityOf(senderName)).sendKeys(sName);
        senderEmail.sendKeys(sEmail);
        senderMobile.sendKeys(sMobile);

        receiverName.sendKeys(rName);
        receiverEmail.sendKeys(rEmail);
        receiverEmailConfirm.sendKeys(rEmail);
        receiverMobile.sendKeys(rMobile);

        wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox)).click();
    }

    public void clickPayNow() {
        wait.until(ExpectedConditions.elementToBeClickable(payNowButton)).click();
    }

    public String getOtpMessage() {
        return wait.until(ExpectedConditions.visibilityOf(otpMessage)).getText();
    }
}
