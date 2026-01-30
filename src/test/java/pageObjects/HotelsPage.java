package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HotelsPage  {
    WebDriver driver;
    WebDriverWait wait;

    public HotelsPage(WebDriver driver, WebDriverWait wait){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[@class='htl_location shwbx']\"")
    WebElement ClickSelectBox;

    @FindBy(xpath = "//input[@id='txtCity']\"")
    WebElement SelectPlace;

    @FindBy(xpath = "(//div[@class='hp_inputBox ht-dates'])[1]")
    WebElement selectCheckIn;
    @FindBy(xpath = "//div[@class='ui-datepicker-group ui-datepicker-group-last']//table//tbody//a[text()='12']")
    WebElement clickCheckIn;
    @FindBy(xpath = "(//div[@class='hp_inputBox ht-dates'])[2]")
    WebElement selectCheckOut;
    @FindBy(xpath = "//div[@class='ui-datepicker-group ui-datepicker-group-last']//table//tbody//a[text()='14']")
    WebElement clickCheckOut;

    @FindBy(id="Adults_room_1_1_plus")
    WebElement adult_plus_button;

    public void HotelAction() {
        ClickSelectBox.click();
        SelectPlace.sendKeys("Manali");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//ul[@id='divAppend']"))));
        try {
            driver.findElement(By.xpath("(//div[@class='hflexaut'])[1]")).click();
        }
        catch(StaleElementReferenceException e)
        {
            driver.findElement(By.xpath("(//div[@class='hflexaut'])[1]")).click();
        }
        selectCheckIn.click();
        clickCheckIn.click();
        selectCheckOut.click();
        clickCheckOut.click();
        while(adult_plus_button.isEnabled()){
            int before=Integer.parseInt(driver.findElement(By.xpath("//span[@id='Adults_room_1_1']")).getText());
            adult_plus_button.click();
            int after=Integer.parseInt(driver.findElement(By.xpath("//span[@id='Adults_room_1_1']")).getText());
            if(before==after)
                break;
        }
    }
    public void getMaxAdultCount(){
        int x=Integer.parseInt(driver.findElement(By.xpath("//span[@id='Adults_room_1_1']")).getText());
        System.out.println("Max adult count is:"+x);
    }

}
