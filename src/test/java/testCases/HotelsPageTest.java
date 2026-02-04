package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.BaseTest;
import utilities.Log;

public class HotelsPageTest extends BaseTest {

    @Test(priority = 1)
    public void verifyMaxAdultCountFlow() {
        hotelsPage.navigateToHotels();
        hotelsPage.hotelAction();
        int max = hotelsPage.getMaxAdultCount();

        Assert.assertTrue(max >= 1, "Adult count should be at least 1");
        Log.info("Test is passed to check whether the adult count is more than 1");
    }

    @Test(priority = 2)
    public void verifyHotelsNavigation() {
        hotelsPage.navigateToHotels();
        // If no exception occurs, test is considered passed
        Assert.assertTrue(true, "Navigation to Hotels page failed.");
        Log.info("Test is passed to check whether the hotel navigation button is not failed to click");
    }

    @Test(priority = 3)
    public void verifyAdultCountNotZero() {
        hotelsPage.navigateToHotels();
        hotelsPage.hotelAction();
        hotelsPage.IncreamentAdult();
        int max = hotelsPage.getMaxAdultCount();
        Assert.assertNotEquals(max, 0, "Adult count should not be zero.");
        Log.info("Check whether the max adult count is not zero by default");
    }
    @Test(priority = 4)
    public void verifyCheckInAndCheckOutAreNotSame() {
        hotelsPage.navigateToHotels();
        hotelsPage.hotelAction();
        String checkIn = hotelsPage.getCheckInDate();
        String checkOut = hotelsPage.getCheckOutDate();

        System.out.println("Check-In Day: " + checkIn);
        System.out.println("Check-Out Day: " + checkOut);

        Assert.assertNotEquals(checkIn, checkOut,
                "Check-in date and Check-out date should NOT be the same!");
        Log.info("Test is passed to check whether the checkIn date and checkOut date is not same");
    }

}