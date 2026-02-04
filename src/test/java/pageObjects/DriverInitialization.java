package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DriverInitialization {
    WebDriver driver;
    public DriverInitialization(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
