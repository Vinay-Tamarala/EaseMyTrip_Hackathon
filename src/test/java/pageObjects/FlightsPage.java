package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class FlightsPage {

    WebDriver driver;
    public FlightsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    String toCityName  ;
    String fromCityName ;

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
        fromCityField.click();
        fromCityInputField.sendKeys(departurePlace);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(fromCity)).click();
            fromCityName  = fromCity.getText();

        }
        catch(StaleElementReferenceException e)
        {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//div[@id='fromautoFill']/ul/li)[1]"))
            ).click();
            fromCityName  = fromCity.getText();
        }

        toCityInputField.sendKeys(destinationPlace);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(toCity)).click();
            toCityName  = toCity.getText();
        }
        catch(StaleElementReferenceException e)
        {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//div[@id='toautoFill']/ul/li)[1]"))
            ).click();
            toCityName  = toCity.getText();
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
        swapCity.click();
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
