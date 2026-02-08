package testCases;

import org.testng.annotations.Test;
import testBase.BaseTest;
import utilities.ConfigReader;
import utilities.Log;

public class LoginTest extends BaseTest {

    @Test(priority=1,groups={"login"})
    public void invalidMailCase(){
        Log.info("Starting Login Test.");
        loginLocators.clickOnLogin();
        loginLocators.enterEmail(ConfigReader.getProperty("invalidMail"));
        Log.info("Entering Invalid Mail and Clicking on Continue.");
        String mailError=loginLocators.emailError();
        Log.info("Error is :"+ mailError);
        loginLocators.closeMailPopUp();
    }

    @Test(priority = 2,groups={"login"})
    public void invalidPasswordCase(){
        loginLocators.clickOnLogin();
        Log.info("Entering Valid Mail and Invalid Password and Clicking on Continue.");
        loginLocators.enterEmail(ConfigReader.getProperty("loginMail"));
        loginLocators.enterPassword(ConfigReader.getProperty("invalidPass"));
        String passError=loginLocators.passError();
        Log.info("Error is :"+ passError);
        loginLocators.closePasswordPopUp();
    }

    @Test(priority = 3,groups={"login"})
    public void validLoginTestCase(){
        loginLocators.clickOnLogin();
        Log.info("Entering Valid Mail and Valid Password and Clicking on Continue.");
        loginLocators.enterEmail(ConfigReader.getProperty("loginMail"));
        loginLocators.enterPassword(ConfigReader.getProperty("loginPass"));
    }

}
