package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utilities.ConfigReader;
import utilities.Log;

public class FlightsPage extends DriverInitialization {

    WebDriverWait wait;
    public FlightsPage(WebDriver driver, WebDriverWait wait){
        super(driver);
        this.wait=wait;
    }

    @FindBy (id = "a_FromSector_show")
    private WebElement fromCityInputField;

    @FindBy (id = "a_Editbox13_show")
    private WebElement toCityInputField;

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

    @FindBy(xpath = "//input[@id='FromSector_show']")
    private WebElement afterFrom;

    @FindBy(xpath = "//input[@id='Editbox13_show']")
    private WebElement afterTo;

    @FindBy(xpath = "//li[@id='rtrip']")
    private WebElement roundtrip;

    @FindBy(xpath = "//div[@id='divRtnCal']")
    private WebElement selectReturnDateField;

    @FindBy(xpath = "//li[@id='trd_5_20/02/2026']")
    private WebElement selectReturnDate;

    @FindBy(xpath = "//div[@id='QUC_TOTALFARE']")
    private WebElement roundTripElement;

    By fromCityLocator = By.xpath("(//div[@id='fromautoFill']/ul/li)[1]");
    By toCityLocator = By.xpath("(//div[@id='toautoFill']/ul/li)[1]");

    //TC1 - Adding valid travel details
    public void addTravelDetails(String departurePlace,String destinationPlace){
        try {
            driver.findElement(By.xpath("//div[@class='innerspcr' and @id='frmcity']")).click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(By.xpath("//div[@class='innerspcr' and @id='frmcity']")).click();
        }
        fromCityInputField.sendKeys(departurePlace);

        safeClick(fromCityLocator);

        toCityInputField.sendKeys(destinationPlace);

        safeClick(toCityLocator);

        departureDate.click();
    }

    //TC2 - validating error message with same departure and destination date
    public void testingSameDepartureAndDestination(){
        String error = errorMsg.getText();
        System.out.println("Error message displayed when same departure and destination location is selected : " + error);
    }

    //TC3 - validating the city swap functionality
    public void testingCitySwitchFunctionality(){
        // Capture current values before swap
        String fromBefore = ConfigReader.getProperty("fromCity");
        String toBefore   = ConfigReader.getProperty("toCity");

        // Perform swap
        swapCity.click();

        // Capture values after swap
        String fromAfter = afterFrom.getAttribute("value");
        String toAfter = afterTo.getAttribute("value");

        // Validate the swap
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(fromAfter, toBefore,
                "From city should become the previous To city after swap");
        softAssert.assertEquals(toAfter, fromBefore,
                "To city should become the previous From city after swap");
        softAssert.assertAll();
    }

    //TC4 - validating default traveller count at least 1 traveller is selected by default
    public void verifyDefaultTravellerCountAndClass(){
        String expectedDefaultTraveller = "1 Economy";
        String actualDefaultTraveller = defaultCount.getText() + " " + defaultSelectedClass.getText();
        SoftAssert asserT = new SoftAssert();
        asserT.assertEquals(actualDefaultTraveller,expectedDefaultTraveller,
                "Does not match");
        asserT.assertAll();

    }

    //Navigate to result page
    public void navigateToFlightsSearch(){
        searchBtn.click();
    }

    // TC5 - validating round trip functionality
    public void testingRoundTripFunctionality(String departurePlace,String destinationPlace) {

        roundtrip.click();

        try {
            driver.findElement(By.xpath("//div[@class='innerspcr' and @id='frmcity']")).click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(By.xpath("//div[@class='innerspcr' and @id='frmcity']")).click();
        }
        fromCityInputField.clear();
        fromCityInputField.sendKeys(departurePlace);

         safeClick(fromCityLocator);

        toCityInputField.sendKeys(destinationPlace);

          safeClick(toCityLocator);

        departureDate.click();
        selectReturnDateField.click();
        selectReturnDate.click();


        String fromDisplayed = "";
        String toDisplayed   = "";
        try { fromDisplayed = fromCityInputField.getAttribute("value"); } catch (Exception ignored) {}
        try { toDisplayed   = toCityInputField.getAttribute("value"); }   catch (Exception ignored) {}

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(fromDisplayed.isEmpty(), "From city should be selected for round trip");
        softAssert.assertFalse(toDisplayed.isEmpty(),   "To city should be selected for round trip");

        softAssert.assertAll();
        Log.info("Round trip flow executed successfully with From: "
                + fromDisplayed + " and To: " + toDisplayed);

        searchBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='QUC_TOTALFARE']")));

    }

    public void safeClick(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                element.click();
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        throw new RuntimeException("Element kept going stale: " + locator);
    }

}