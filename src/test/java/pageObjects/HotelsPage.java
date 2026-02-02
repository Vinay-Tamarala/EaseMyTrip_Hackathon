package pageObjects;
import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HotelsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='meuicowidth hotelmenuico']")
    private WebElement hotelsButton;

    @FindBy(xpath = "//div[@class='htl_location shwbx']")
    private WebElement clickSelectBox;

    @FindBy(xpath = "//input[@id='txtCity']")
    private WebElement selectPlace;

    @FindBy(xpath = "(//div[@class='hp_inputBox ht-dates'])[1]")
    private WebElement selectCheckIn;

    @FindBy(xpath = "//div[@class='ui-datepicker-group ui-datepicker-group-last']//table//tbody//a[text()='12']")
    private WebElement clickCheckIn;

    @FindBy(xpath = "(//div[@class='hp_inputBox ht-dates'])[2]")
    private WebElement selectCheckOut;

    @FindBy(xpath = "//div[@class='ui-datepicker-group ui-datepicker-group-last']//table//tbody//a[text()='14']")
    private WebElement clickCheckOut;

    @FindBy(id = "Adults_room_1_1_plus")
    private WebElement adultPlusButton;

    @FindBy(xpath = "//span[@id='Adults_room_1_1']")
    private WebElement adultCountSpan;

    public void navigateToHotels() {
        wait.until(ExpectedConditions.elementToBeClickable(hotelsButton)).click();
    }

    public void hotelAction() {
        // Location
        wait.until(ExpectedConditions.elementToBeClickable(clickSelectBox)).click();
        wait.until(ExpectedConditions.visibilityOf(selectPlace)).clear();
        selectPlace.sendKeys("Manali");

        // Wait for suggestions and pick first
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='divAppend']")));
        try {
            driver.findElement(By.xpath("(//div[@class='hflexaut'])[1]")).click();
        } catch (StaleElementReferenceException e) {
            // Retry once more in case the list refreshed
            driver.findElement(By.xpath("(//div[@class='hflexaut'])[1]")).click();
        }

        // Dates
        wait.until(ExpectedConditions.elementToBeClickable(selectCheckIn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(clickCheckIn)).click();

        wait.until(ExpectedConditions.elementToBeClickable(selectCheckOut)).click();
        wait.until(ExpectedConditions.elementToBeClickable(clickCheckOut)).click();
    }

    public void IncreamentAdult() {
        // Increase adults until it no longer increments
        while (adultPlusButton.isEnabled()) {
            int before = Integer.parseInt(adultCountSpan.getText().trim());
            adultPlusButton.click();
            int after = Integer.parseInt(adultCountSpan.getText().trim());
            if (before == after) {
                break;
            }
        }
    }

    public int getMaxAdultCount() {
        int x = Integer.parseInt(adultCountSpan.getText().trim());
        System.out.println("Max adult count is: " + x);
        return x;
    }

    public String getCheckInDate() {
        // Returns the day text that was clicked (e.g., "12")
        return clickCheckIn.getText().trim();
    }

    public String getCheckOutDate() {
        // Returns the day text that was clicked (e.g., "14")
        return clickCheckOut.getText().trim();
    }

}