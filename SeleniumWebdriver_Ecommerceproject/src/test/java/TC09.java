import POM.CheckOutPage;
import driver.driverFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TC09 {
    public  TC09() {}
    private static  final  String imagesPath = "src/main/resources";
    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(TC09.imagesPath + fileName));
    }
    @Test
    public void TestCase09() {
        WebDriver driver = driverFactory.getChromeDriver();
        String coupon = "GURU50";
        try {
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Go to Mobile and add IPHONE to cart
            CheckOutPage checkOutPage = new CheckOutPage(driver);
            checkOutPage.clickMobileLink();
            checkOutPage.clickAddIphone();

            String before = checkOutPage.getgrandtotal();
            checkOutPage.enterCode(coupon);
            checkOutPage.clickApply();
            String after = checkOutPage.getgrandtotal();
            assertNotEquals(after, before, "GRAND TOTAL is apply counpon?");

            takeScreenshot(driver, "TC09.png");
            Thread.sleep(2000);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            driver.quit();
        }
    }
}
