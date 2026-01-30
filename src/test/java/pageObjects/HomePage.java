package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='More']")
    private WebElement moreButton;

    @FindBy(xpath = "//span[text()='Gift Card']")
    private WebElement giftCardLink;

    @FindBy(xpath = "//span[@class=\"meuicowidth hotelmenuico\"]")
    WebElement hotels;


    public void navigateToGiftCard() {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(moreButton));
        actions.moveToElement(moreButton).perform();
        wait.until(ExpectedConditions.elementToBeClickable(giftCardLink)).click();
    }
    //hotels
    public void navigateToHotels(){
       hotels.click();
    }

}
