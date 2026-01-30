package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonCode;
import utilities.ConfigReader;

import java.util.List;

public class FlightPage extends BasePage{
    public FlightPage(WebDriver driver){
        super(driver);
    }

    CommonCode cc=new CommonCode(driver);

    @FindBy(xpath="(//button[normalize-space(text())='More Fares'])[1]")
    public WebElement fareButton;

    @FindBy(xpath="//h4[contains(@id,'spnPrice')]")
    public List<WebElement> prices;

    public void selectFilter(){
        WebElement airlineFilter=driver.findElement(By.xpath("//h5[@class='prc_ttl2' and contains(text(),'Airlines')]//following::div[label/span[normalize-space(text())='"+ ConfigReader.getProperty("Airline")+"'] and p[@class='aln_prc']]//input[@type='checkbox']"));
        cc.scrollIntoView(airlineFilter);
        airlineFilter.click();
    }

    public void selectFlight() {
        cc.clickElement(fareButton);
    }

    public void selectType(){
        driver.findElement(By.xpath("//div[contains(normalize-space(text()),'"+ConfigReader.getProperty("type")+"')]")).click();
        driver.findElement(By.xpath("//div[contains(normalize-space(@class),'fareheader') and contains(normalize-space(text()),'"+ConfigReader.getProperty("Value")+"')]//ancestor::label//a[text()=' Book Now ']")).click();
    }
    public void flightPrices(){
        List<WebElement> priceslist=prices;
        for(WebElement price:priceslist){
            System.out.print(price.getText()+" ");
        }
    }
}
