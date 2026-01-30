package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CommonCode;
import utilities.ConfigReader;


public class CheckOutPage extends BasePage {
    WebDriverWait wait;
    public CheckOutPage(WebDriver driver, WebDriverWait wait){
        super(driver);
        this.wait=wait;
    }

    CommonCode cc=new CommonCode(driver);

    @FindBy(xpath="//*[@id='divInsuranceTab']/app-insurance/div[3]")
    public WebElement insurance;

    @FindBy(xpath="//input[@id='chkInsurance']")
    WebElement insuranceYes;

    @FindBy(xpath="//input[@id='notinsure']")
    WebElement insuranceNo;

    @FindBy(xpath="//select[@id='titleAdult0']")
    WebElement prefixSelect;

    @FindBy(xpath="//input[@id='txtFNAdult0']")
    WebElement firstName;

    @FindBy(xpath="//input[@id='txtLNAdult0']")
    WebElement lastName;

    @FindBy(xpath ="//input[@id='txtEmailAdult0']")
    WebElement emailBox;

    @FindBy(xpath ="//input[@id='txtCPhoneAdult0']")
    WebElement number;

    @FindBy(xpath="//input[@id='txtEmailId']")
    WebElement contactEmail;

    @FindBy(xpath="//input[@id='txtCPhone']")
    WebElement contactNumber;

    @FindBy(xpath="//*[@id='spnTransaction']/span")
    WebElement continueBtn;

    public void selectInsurance(){
     cc.scrollIntoView(insurance);
     if(ConfigReader.getProperty("insurance").equals("yes"))
     {
         cc.clickElement(insuranceYes);
     }
     else {
         cc.clickElement(insuranceNo);
     }
    }

    public void enterTravellerDetails(){
        Select select = new Select(prefixSelect);
        select.selectByVisibleText(ConfigReader.getProperty("prefix"));
        firstName.sendKeys(ConfigReader.getProperty("fName"));
        lastName.sendKeys(ConfigReader.getProperty("lName"));
        emailBox.sendKeys(ConfigReader.getProperty("email"));
        number.sendKeys(ConfigReader.getProperty("Number"));
    }

    public void enterContactDetails(){
      contactEmail.sendKeys(ConfigReader.getProperty("email"));
      contactNumber.sendKeys(ConfigReader.getProperty("Number"));
      wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
      cc.clickElement(continueBtn);
    }



}
