package testCases;

import org.testng.annotations.Test;
import testBase.BaseTest;
import utilities.ConfigReader;
import utilities.Log;

public class HomePageTest extends BaseTest {

    @Test(priority = 1)
    public void validateDefaultTravellerCount(){
        flights.verifyDefaultTravellerCountAndClass();
        Log.info("Validated default traveller count.");
    }

    @Test(priority = 2)
    public void testSameFromAndTo(){
        homePageVc.toFlightsPage();
        flights.addTravelDetails(ConfigReader.getProperty("fromCity"),ConfigReader.getProperty("fromCity"));
        flights.navigateToFlightsSearch();
        cc.takeScreenShot("SameFromToCity");
        //to fetch error message displayed
        flights.testingSameDepartureAndDestination();
        driver.navigate().refresh();
    }

    @Test(priority = 3)
    public void testSwitchCity(){
        flights.addTravelDetails(ConfigReader.getProperty("fromCity"),ConfigReader.getProperty("toCity"));
        flights.testingCitySwitchFunctionality();
        cc.takeScreenShot("SwitchCity");
    }

    @Test(priority = 4)
    public void testValidDetails(){
        flights.addTravelDetails(ConfigReader.getProperty("fromCity"),ConfigReader.getProperty("toCity"));
        cc.takeScreenShot("ValidSearchDetails");
        flights.navigateToFlightsSearch();
        driver.navigate().back();
    }

    @Test(priority = 5)
    public void testRoundTripFunctionality(){
        flights.testingRoundTripFunctionality(ConfigReader.getProperty("fromCity"),ConfigReader.getProperty("toCity"));
        cc.takeScreenShot("RoundTripSearch");
    }
}