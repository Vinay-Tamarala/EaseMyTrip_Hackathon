package testCases;

import org.testng.annotations.Test;
import testBase.BaseTest;
import utilities.ConfigReader;

public class FlightCases extends BaseTest {

        @Test(priority = 1)
        public void FlightCaseTest() {
            flights.addTravelDetails(ConfigReader.getProperty("fromCity"),ConfigReader.getProperty("toCity"));
            flightLocators.selectFilter();
            flightLocators.flightPrices();
            flightLocators.selectFlight();
            flightLocators.selectType();
        }

        @Test(priority =2)
        public void selectEmptyCoupon(){
          String alertMsg=checkLocators.enterCoupon();
          System.out.println("Alert message is :"+alertMsg);
        }

        @Test(priority=3)
        public void selectFlightDetailsCase(){
         checkLocators.selectInsurance(ConfigReader.getProperty("insurance"));
         checkLocators.enterTravellerDetails(ConfigReader.getProperty("prefix"),ConfigReader.getProperty("fName"),
                 ConfigReader.getProperty("lName"),ConfigReader.getProperty("email"),ConfigReader.getProperty("Number"));
         checkLocators.enterContactDetails(ConfigReader.getProperty("email"),ConfigReader.getProperty("Number"));
        }

        @Test(priority=4)
        public void payCases() {
            payLocators.assertEmail();
            payLocators.assertContact();
            payLocators.assertName();
            payLocators.assertTitle();
            payLocators.checkAssertAll();
        }
}
