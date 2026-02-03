package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class FlightsPage extends BasePage{


    public FlightsPage(WebDriver driver){
        super(driver);
    }

    @FindBy (xpath = "//div[@class='innerspcr' and @id='frmcity']")
    private WebElement fromCityField;

    @FindBy (id = "a_FromSector_show")
    private WebElement fromCityInputField;

    @FindBy (xpath = "(//div[@id='fromautoFill']/ul/li)[1]")
    private WebElement fromCity;

    @FindBy (id = "a_Editbox13_show")
    private WebElement toCityInputField;

    @FindBy (xpath = "(//div[@id='toautoFill']/ul/li)[1]")
    private WebElement toCity;

    @FindBy (xpath = "//li[@id='snd_6_14/02/2026']")
    private WebElement departureDate;

    @FindBy (xpath = "//input[@class='srchBtnSe']")
    private WebElement searchBtn;

    @FindBy(xpath = "//div[@class='errorfrmsrc error-message-dropoff']")
    private WebElement errorMsg;

    @FindBy(id = "swap")
    private WebElement swapCity;

    @FindBy(id = "spnDrpNo")
    private WebElement defaultCount;

    @FindBy(id = "pcalss")
    private WebElement defaultSelectedClass;

    //TC1 - Adding valid travel details
    public void addTravelDetails(String departurePlace,String destinationPlace){
        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.findElement(By.xpath("//div[@class='innerspcr' and @id='frmcity']")).click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(By.xpath("//div[@class='innerspcr' and @id='frmcity']")).click();
        }
        fromCityInputField.sendKeys(departurePlace);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(fromCity)).click();
        }
        catch(StaleElementReferenceException e)
        {
//            driver.findElement(By.xpath("(//div[@id='fromautoFill']/ul/li)[1]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//div[@id='fromautoFill']/ul/li)[1]"))
            ).click();

        }

        toCityInputField.sendKeys(destinationPlace);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(toCity)).click();
        }
        catch(StaleElementReferenceException e)
        {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//div[@id='toautoFill']/ul/li)[1]"))
            ).click();
        }

        departureDate.click();
        searchBtn.click();

    }

    //TC2 - validating error message with same departure and destination date
    public void testingSameDepartureAndDestination(){
        addTravelDetails("Mumbai", "Mumbai");
        String error = errorMsg.getText();
        System.out.println("Error message displayed when same departure and destination location is selected : " + error);
    }

    //TC3 - validating the city swap functionality
    public void testingCitySwitchFunctionality(){
        // Capture current values before swap
        String fromBefore = fromCityInputField.getAttribute("value").trim();
        String toBefore   = toCityInputField.getAttribute("value").trim();

        // Perform swap
        swapCity.click();

        // Wait until the fields reflect swapped values
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> toBefore.equals(fromCityInputField.getAttribute("value").trim())
                && fromBefore.equals(toCityInputField.getAttribute("value").trim()));

        // Capture values after swap
        String fromAfter = fromCityInputField.getAttribute("value").trim();
        String toAfter   = toCityInputField.getAttribute("value").trim();

        // Validate the swap
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(fromAfter, toBefore,
                "From city should become the previous To city after swap");
        softAssert.assertEquals(toAfter, fromBefore,
                "To city should become the previous From city after swap");
        softAssert.assertAll();

    }


    public void testingPassengers(){

    }

    //TC4 - validating default traveller count
    public void verifyDefaultTravellerCount(){
        String expectedDefaultTraveller = "1 Economy";
        String actualDefaultTraveller = defaultCount.getText() + " " + defaultSelectedClass.getText();
        SoftAssert asserT = new SoftAssert();
        asserT.assertEquals(actualDefaultTraveller,expectedDefaultTraveller, "Does not match");
        asserT.assertAll();
        System.out.println("Passed");
    }
}