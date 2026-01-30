package testCases;

import org.testng.annotations.Test;
import testBase.BaseTest;

public class FlightCases extends BaseTest {
        @Test
        public void FlightCaseTest() {
            flightLocators.selectFilter();
            flightLocators.flightPrices();
            flightLocators.selectFlight();
            flightLocators.selectType();
        }
}
