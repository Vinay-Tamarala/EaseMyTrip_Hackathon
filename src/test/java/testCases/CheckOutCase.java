package testCases;

import org.testng.annotations.Test;
import testBase.BaseTest;

public class CheckOutCase extends BaseTest {
  @Test
    public void selectFlightDetailsCase(){

      flights.addTravelDetails("Delhi","Mumbai");
      flightLocators.selectFilter();
      flightLocators.selectFlight();
      flightLocators.selectType();
      checkLocators.selectInsurance();
      checkLocators.enterTravellerDetails();
      checkLocators.enterContactDetails();
  }
}
