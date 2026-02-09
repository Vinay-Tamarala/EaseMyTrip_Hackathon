package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CommonCode;
import utilities.ConfigReader;

import java.util.ArrayList;
import java.util.List;

public class FlightPage extends DriverInitialization {
    WebDriverWait wait;
    public FlightPage(WebDriver driver, WebDriverWait wait){
        super(driver);
        this.wait=wait;
    }

    CommonCode cc=new CommonCode(driver);

    @FindBy(xpath="(//button[normalize-space(text())='More Fares'])[1]")
    public WebElement fareButton;

    @FindBy(xpath="//h4[contains(@id,'spnPrice')]")
    public List<WebElement> prices;

    public void selectFilter(){
        WebElement airlineFilter=wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h5[@class='prc_ttl2' and text()='Airlines']"))));
        cc.scrollIntoView(airlineFilter);
        cc.clickElement(driver.findElement(By.xpath("//h5[@class='prc_ttl2' and text()='Airlines']//following::div[label/span[normalize-space(text())='"+ ConfigReader.getProperty("Airline")+"'] and p[@class='aln_prc']]//input[@type='checkbox']")));
    }

    public void selectFlight() {
        cc.clickElement(fareButton);
    }

    public void selectType(){
        try {
            driver.findElement(By.xpath("//div[contains(normalize-space(text()),'" + ConfigReader.getProperty("type") + "')]")).click();
            driver.findElement(By.xpath("//div[contains(normalize-space(@class),'fareheader') and contains(normalize-space(text()),'" + ConfigReader.getProperty("Value") + "')]//ancestor::label//a[text()=' Book Now ']")).click();
        }
        catch (Exception e)
        {
            driver.findElement(By.xpath("(//a[text()=' Book Now '])[1]")).click();
        }
    }
    public List<String> flightPrices() {
        List<String> priceslist= new ArrayList<>();
        for(WebElement price:prices){
            priceslist.add(price.getText());
        }
        return priceslist;
    }
}
