package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Home extends DriverInitialization{

    WebDriverWait wait;
    public Home(WebDriver driver, WebDriverWait wait){
        super(driver);
        this.wait=wait;
    }

    @FindBy(xpath = "//span[ @class ='meuicowidth flightmenuico']")
    WebElement flight;

    public void toFlightsPage(){
        wait.until(ExpectedConditions.elementToBeClickable(flight)).click();
    }
}
