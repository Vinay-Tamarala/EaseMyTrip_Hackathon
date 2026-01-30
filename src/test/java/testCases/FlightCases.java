package testCases;

import org.testng.annotations.Test;
import testBase.BaseTest;

public class FlightCases extends BaseTest {
        @Test
        public void FlightCaseTest() {

            flights.addTravelDetails("Delhi","Mumbai");
            flightLocators.selectFilter();
            flightLocators.flightPrices();
            flightLocators.selectFlight();
            flightLocators.selectType();
        }
}
