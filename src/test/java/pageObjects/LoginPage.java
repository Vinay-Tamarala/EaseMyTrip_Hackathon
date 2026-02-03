package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
    WebDriverWait wait;
    public LoginPage(WebDriver driver,WebDriverWait wait){
        super(driver);
        this.wait=wait;
    }

    @FindBy(xpath="//*[@id='divSignInPnl']")
    WebElement loginBtn;

    @FindBy(xpath="//a[@class='_drpdubro log']")
    WebElement customerLog;

    @FindBy(xpath="//*[@id='txtEmail']")
    WebElement loginEmail;

    @FindBy(xpath ="//input[@id='shwotp']")
    WebElement loginContinueBtn;

    @FindBy(xpath="//*[@id='txtEmail2']")
    WebElement loginPwd;

    @FindBy(xpath="//div[@class='_btnlog']/input[@value='Login' and not(@id)]")
    WebElement login;

    @FindBy(xpath="//div[@id='RegValidEmail' and normalize-space(text())='* Enter a valid Email']")
    WebElement invalidMail;

    @FindBy(xpath="//div[@id='ValidPass']")
    WebElement invalidPass;

    @FindBy(xpath="//div[@class='_crosslog' and @onclick='Popup()']")
    WebElement closeMailPop;

    @FindBy(xpath="//div[@class='_ttl_hd' and text()='Password Authentication']/following-sibling::div[@class='_crosslog' ]")
    WebElement closePasswordPop;

    public void clickOnLogin(){
        loginBtn.click();
        customerLog.click();
    }

    public void enterEmail(String mail){
        loginEmail.clear();
        loginEmail.sendKeys(mail);
        loginContinueBtn.click();
    }

    public void enterPassword(String password){
        loginPwd.clear();
        loginPwd.sendKeys(password);
        login.click();
    }

    public void emailError(){
        wait.until(ExpectedConditions.visibilityOf(invalidMail));
        System.out.println("Error is: "+invalidMail.getText());
    }

    public void passError(){
        wait.until(ExpectedConditions.visibilityOf(invalidPass));
        System.out.println("Error is : "+invalidPass.getText());
    }

    public void closeMailPopUp(){
        closeMailPop.click();
    }

    public void closePasswordPopUp(){
        closePasswordPop.click();
    }
}
