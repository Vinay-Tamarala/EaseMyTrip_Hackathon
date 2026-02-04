package testCases;

import org.testng.annotations.Test;
import testBase.BaseTest;
import utilities.ConfigReader;
import utilities.Log;

import java.io.IOException;
import java.util.List;

public class FlightsTest extends BaseTest {

        @Test(priority = 1)
        public void flightCaseTest() throws IOException {
            Log.info("Starting Flights Test.");
            flights.addTravelDetails(ConfigReader.getProperty("fromCity"),ConfigReader.getProperty("toCity"));
            flights.navigateToFlightsSearch();
            Log.info("Selecting Airline Filter.");
            flightLocators.selectFilter();
            List<String> price= flightLocators.flightPrices();
            Log.info("Prices" + price);
            flightLocators.selectFlight();
            flightLocators.selectType();
            cc.takeScreenShot("flightSelection");
        }

        @Test(priority =2)
        public void selectEmptyCoupon(){
          cc.takeScreenShot("flightDetails");
          Log.info("Selecting Empty Coupon.");
          String alertMsg=checkLocators.enterCoupon();
          Log.info("Alert message is :"+alertMsg);
        }

        @Test(priority=3)
        public void selectFlightDetailsCase(){
         checkLocators.selectInsurance(ConfigReader.getProperty("insurance"));
         cc.takeScreenShot("travellerDetails");
         Log.info("Filling Traveller Details.");
         checkLocators.enterTravellerDetails(ConfigReader.getProperty("prefix"),
                                             ConfigReader.getProperty("fName"),
                                             ConfigReader.getProperty("lName"),
                                             ConfigReader.getProperty("email"),
                                             ConfigReader.getProperty("Number"));
         checkLocators.enterContactDetails(ConfigReader.getProperty("email"),ConfigReader.getProperty("Number"));
        }

        @Test(priority=4)
        public void payCases() {
            Log.info("Asserting Traveller Details.");
            payLocators.assertEmail();
            payLocators.assertContact();
            payLocators.assertName();
            payLocators.assertTitle();
            payLocators.checkAssertAll();
            cc.takeScreenShot("flightPayment");
        }
}
