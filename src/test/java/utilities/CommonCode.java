package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import testBase.BaseTest;

import java.io.File;
import java.io.IOException;

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

    public void takeScreenShot(String name) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File dir = new File(System.getProperty("user.dir") + "/screenshots1/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE),
                    new File(dir, name + ".jpeg"));
            System.out.println(name + ": Screenshot has been captured.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
