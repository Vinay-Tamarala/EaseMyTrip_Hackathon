package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyBookingPage extends BasePage {
    WebDriverWait wait;

    public MyBookingPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='reffid']")
    WebElement referenceId;

    @FindBy(xpath = "(//input[@id='reffemail'])[1]")
    WebElement referenceEmail;

    @FindBy(xpath = "//input[@onclick='LoginGuestUser();']")
    WebElement submitBtn;

    @FindBy(xpath = "//div[@id='guesterr']")
    WebElement errorMessage;


    public void setReferenceId(String refId) {
        wait.until(ExpectedConditions.visibilityOf(referenceId));
        referenceId.clear();
        referenceId.sendKeys(refId);
    }

    public void setReferenceEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(referenceEmail));
        referenceEmail.clear();
        referenceEmail.sendKeys(email);
    }

    public void submitTheData() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
}
