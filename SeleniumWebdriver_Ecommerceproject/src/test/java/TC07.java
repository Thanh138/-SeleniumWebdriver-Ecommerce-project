import POM.AccountPage;
import POM.LoginPage;
import driver.driverFactory;
import org.example.Register;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TC07 {
    public  TC07() {}
    private static  final  String imagesPath = "src/main/resources";
    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(TC07.imagesPath + fileName));
    }
    @Test
    public void TestCase07() {
        //LOGIN
        String email = "thanhncse172947@fpt.edu.vn";
        String password = ":Hiro:138";

        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            LoginPage loginPage = new LoginPage(driver);
            Register registerPage = new Register(driver);
            AccountPage accountPage = new AccountPage(driver);
            //2. Click on My Account link
            registerPage.clickMyAccountLink();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            //3. Login in application using previously created credential
            loginPage.EnterEmail(email);
            loginPage.EnterPassword(password);
            loginPage.clickLoginBtn();

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            //4. Click on 'My Orders'
            accountPage.clickMyOrder();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            //5. Click on 'View Order'
            accountPage.clickViewOrder();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            //6. Click on 'Print Order' link
            accountPage.clickPrintOrder();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            try {
                takeScreenshot(driver, "TC07.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            driver.quit();
        }
    }
}