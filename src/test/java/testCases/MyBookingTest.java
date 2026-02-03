package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import testBase.BaseTest;

public class MyBookingTest extends BaseTest {

    @Test
    public void verifyInvalidBookingDetails() {
        homePage.navigateToSignupButton();

        myBookingPage.setReferenceId(ConfigReader.getProperty("wrongReferenceId"));
        myBookingPage.setReferenceEmail(ConfigReader.getProperty("wrongEmail"));
        myBookingPage.submitTheData();

        String actualError = myBookingPage.getErrorMessage();
        String expectedError = "Your reference ID does not match with this email address.";
        Assert.assertEquals(actualError, expectedError, "Error message expected, is not shown");
    }
}
