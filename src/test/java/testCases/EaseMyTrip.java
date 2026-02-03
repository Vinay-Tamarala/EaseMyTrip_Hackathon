package testCases;

import org.testng.annotations.Test;
import testBase.BaseTest;

public class EaseMyTrip extends BaseTest {

    @Test
    public void testFlightsNavigation(){
        homePageVc.toFlightsPage();
        flights.addTravelDetails("Delhi","Mumbai");
    }
}
