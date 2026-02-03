package testCases;

import org.testng.annotations.Test;
import testBase.BaseTest;
import utilities.ConfigReader;
import utilities.ExcelUtil;

import java.io.IOException;

public class LoginCase extends BaseTest {

    @Test(priority=1)
    public void invalidMailCase() throws IOException {
        ExcelUtil.loadExcel("src/test/resources/TestData.xlsx");
        String invalidMail=ExcelUtil.getCellValue("Sheet1",1,0);
        loginLocators.clickOnLogin();
        loginLocators.enterEmail(invalidMail);
        loginLocators.emailError();
        loginLocators.closeMailPopUp();
    }

    @Test(priority = 2)
    public void invalidPasswordCase(){
        String loginMail=ExcelUtil.getCellValue("Sheet1",2,0);
        String invalidPass=ExcelUtil.getCellValue("Sheet1",2,1);
        loginLocators.clickOnLogin();
        loginLocators.enterEmail(loginMail);
        loginLocators.enterPassword(invalidPass);
        loginLocators.passError();
        loginLocators.closePasswordPopUp();
    }

    @Test(priority = 3)
    public void validLoginTestCase(){
        String loginMail=ExcelUtil.getCellValue("Sheet1",3,0);
        String loginPass=ExcelUtil.getCellValue("Sheet1",3,1);
        loginLocators.clickOnLogin();
        loginLocators.enterEmail(loginMail);
        loginLocators.enterPassword(loginPass);
    }

}
