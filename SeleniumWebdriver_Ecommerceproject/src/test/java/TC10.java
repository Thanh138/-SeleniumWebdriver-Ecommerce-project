import POM.BackEndPage;
import driver.driverFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TC10 {
    public  TC10() {}
    private static  final  String imagesPath = "src/main/resources";
    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(TC10.imagesPath + fileName));
    }
    @Test
    public void TestCase10() {
        WebDriver driver = driverFactory.getChromeDriver();
        String user = "user01";
        String pass = "guru99com";
        String orderId = "100021250";
        String fromDate = "Nov 8, 2023 8:27:39 PM";
        String toDate  = "Nov 10, 2023 8:27:39 PM";
        try{
            //1. Go to http://live.techpanda.org/index.php/backendlogin
            driver.get("http://live.techpanda.org/index.php/backendlogin");

            //2. Login the credentials provided
            BackEndPage backEndPage = new BackEndPage(driver);

            backEndPage.enterUserName(user);
            backEndPage.enterPassWord(pass);
            backEndPage.clickLogin();

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(3000);

            //3. Go to Sales-> Orders menu
            backEndPage.clickClose();
            backEndPage.chooseSale();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            backEndPage.clickOrders();
            //backEndPage.enterOrderId(orderId);
            backEndPage.enterFromDate(fromDate);
            backEndPage.enterToDate(toDate);
            backEndPage.clickSearch();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            takeScreenshot(driver, "TC10.png");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
