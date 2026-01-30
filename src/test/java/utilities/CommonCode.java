package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testBase.BaseTest;

public class CommonCode extends BaseTest {
    WebDriver driver;
    public CommonCode(WebDriver driver){
        this.driver=driver;
    }
    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickElement(WebElement element) {
        try {
            element.click();
          }
        catch (Exception e) {
            scrollIntoView(element);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}
