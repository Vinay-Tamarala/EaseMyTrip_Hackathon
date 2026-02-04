package pageObjects;


import org.openqa.selenium.*;
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
    public WebElement insuranceOpt;

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

    @FindBy(xpath ="//*[@id='spnApply']")
    WebElement apply;

    @FindBy(xpath ="//div[text()='Remove']")
    WebElement remove;

    @FindBy(xpath="//a[@id='skipPop']")
    WebElement skip;

    public void selectInsurance(String insurance){
     cc.scrollIntoView(insuranceOpt);
     if(insurance.equals("yes"))
     {
         cc.clickElement(insuranceYes);
     }
     else {
         cc.clickElement(insuranceNo);
     }
    }

    public void enterTravellerDetails(String prefix,String fName,String lName,String email,String numb){
        Select select = new Select(prefixSelect);
        select.selectByVisibleText(prefix);
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        emailBox.sendKeys(email);
        number.sendKeys(numb);
    }

    public String enterCoupon(){
        try{
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[text()='Remove' and not(@id)]"))));
            System.out.println("Is the coupon already selected :" +driver.findElement(By.xpath("//div[text()='Remove' and not(@id)]")).isDisplayed() );
            cc.clickElement(remove);
            cc.clickElement(apply);
            Alert alert = driver.switchTo().alert();
            String alertMsg = alert.getText();
            alert.accept();
            return alertMsg;
        }
        catch (Exception e){
            cc.clickElement(apply);
            Alert alert = driver.switchTo().alert();
            String alertMsg = alert.getText();
            alert.accept();
            return alertMsg;
        }
    }
    public void enterContactDetails(String email,String number){
      contactEmail.sendKeys(email);
      contactNumber.sendKeys(number);
      try {
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='spnTransactionbtnLoader']")));
          cc.clickElement(continueBtn);
      }
      catch(TimeoutException e) {
          cc.clickElement(continueBtn);
          cc.clickElement(driver.findElement(By.xpath("//a[@class='conf_btn']")));
          cc.clickElement(skip);
      }
    }



}
