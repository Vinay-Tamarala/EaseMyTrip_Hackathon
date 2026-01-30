package testCases;

import pageObjects.FlightsPage;
import pageObjects.Home;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.BaseTest;

public class EaseMyTrip extends BaseTest {

    private Home homePage;
    private FlightsPage flights;

    @BeforeClass
    public void initPages() {
        homePage = new Home(driver);
        flights = new FlightsPage(driver);

    }

    @Test
    public void testFlightsNavigation(){
        homePage.toFlightsPage();
        flights.addTravelDetails("Delhi","Mumbai");
        driver.navigate().back();
        flights.testingSameDepartureAndDestination();
    }

}
