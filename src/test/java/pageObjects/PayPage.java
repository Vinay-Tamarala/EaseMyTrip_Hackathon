package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utilities.ConfigReader;

public class PayPage extends BasePage{
    WebDriverWait wait;
    public PayPage(WebDriver driver, WebDriverWait wait){
        super(driver);
        this.wait=wait;
    }
    SoftAssert softAssert = new SoftAssert();

    @FindBy(xpath="//span[normalize-space(text())='E-mail']/following-sibling::span")
    WebElement email;

    @FindBy(xpath="//span[contains(normalize-space(text()),'Contact')]/following-sibling::span")
    WebElement contact;

    @FindBy(xpath="//span[contains(normalize-space(text()),'Adult')]/following-sibling::span")
    WebElement name;

    public void assertEmail(){
        String expectedEmail= ConfigReader.getProperty("email");
        String actualEmail=wait.until(ExpectedConditions.visibilityOf(email)).getText();
        String normalizedEmail=expectedEmail.replaceAll(" ","");
        String normalizedActualEmail= actualEmail.replaceAll(" ","");
        softAssert.assertEquals(normalizedActualEmail,normalizedEmail,"Email doesn't match");
    }

    public void assertContact(){
        String expectedContact= ConfigReader.getProperty("Number");
        String actualContact= contact.getText();
        String normalizedContact= expectedContact.replaceAll(" ","");
        String normalizedActualContact= actualContact.replace("+91","");
        softAssert.assertEquals(normalizedActualContact,normalizedContact,"Mobile Number doesn't match");
    }

    public void assertName(){
        String expectedName= ConfigReader.getProperty("prefix")+ConfigReader.getProperty("fName")+ConfigReader.getProperty("lName");
        String actualName= name.getText();
        String normalizedName= expectedName.replaceAll(" ","");
        String normalizedActualName= actualName.replaceAll(" ","");
        softAssert.assertEquals(normalizedActualName,normalizedName,"Name doesn't match");
    }

    public void assertTitle(){
        String actualTitle=driver.getTitle();
        softAssert.assertEquals(actualTitle,"SafePay – EaseMyTrip’s Secure Booking Payments","Title doesn't match");
    }

    public void checkAssertAll(){
        softAssert.assertAll();
    }

}
