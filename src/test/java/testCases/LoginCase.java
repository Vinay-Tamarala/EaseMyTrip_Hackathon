package testCases;

import org.testng.annotations.Test;
import testBase.BaseTest;
import utilities.ConfigReader;

public class LoginCase extends BaseTest {

    @Test(priority=1)
    public void invalidMailCase(){
        loginLocators.clickOnLogin();
        loginLocators.enterEmail(ConfigReader.getProperty("invalidMail"));
        loginLocators.emailError();
        loginLocators.closeMailPopUp();
    }

    @Test(priority = 2)
    public void invalidPasswordCase(){
        loginLocators.clickOnLogin();
        loginLocators.enterEmail(ConfigReader.getProperty("loginMail"));
        loginLocators.enterPassword(ConfigReader.getProperty("invalidPass"));
        loginLocators.passError();
        loginLocators.closePasswordPopUp();
    }

    @Test(priority = 3)
    public void validLoginTestCase(){
        loginLocators.clickOnLogin();
        loginLocators.enterEmail(ConfigReader.getProperty("loginMail"));
        loginLocators.enterPassword(ConfigReader.getProperty("loginPass"));
    }

}
