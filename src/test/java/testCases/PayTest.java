package testCases;

import org.testng.annotations.Test;
import testBase.BaseTest;

public class PayTest extends BaseTest {
    @Test
    public void payCases() {

        flights.addTravelDetails("Delhi","Mumbai");
        flightLocators.selectFilter();
        flightLocators.selectFlight();
        flightLocators.selectType();
        checkLocators.selectInsurance();
        checkLocators.enterTravellerDetails();
        checkLocators.enterContactDetails();
        payLocators.assertEmail();
        payLocators.assertContact();
        payLocators.assertName();
        payLocators.assertTitle();
        payLocators.checkAssertAll();
    }

}
