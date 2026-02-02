package testCases;

import pageObjects.FlightsPage;
import pageObjects.Home;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.BaseTest;
import utilities.Log;

public class EaseMyTrip extends BaseTest {

    @Test
    public void testFlightsNavigation(){
        homePageVc.toFlightsPage();
        flights.addTravelDetails("Delhi","Mumbai");
        driver.navigate().back();
        flights.testingSameDepartureAndDestination();
    }

}
