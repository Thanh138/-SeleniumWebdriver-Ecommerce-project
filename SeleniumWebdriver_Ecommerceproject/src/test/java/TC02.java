import driver.driverFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TC02 {
    public  TC02() {}
    private static  final  String imagesPath = "src/main/resources";
    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(TC02.imagesPath + fileName));
    }
    @Test
    public void TestCase02() {
        // 1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // 2. Open target page - Login Form
            driver.get("http://live.techpanda.org/index.php/");
            // 3: Click on MOBILE menu
            WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
            mobileMenu.click();
            // 4: Read the cost of Sony Xperia mobile
            WebElement sonyXperiaPriceElement = driver.findElement(By.xpath("//a[contains(text(),'Sony Xperia')]/../following-sibling::div/span"));
            String sonyXperiaPrice = sonyXperiaPriceElement.getText();
            System.out.println("Price in mobile page: " + sonyXperiaPrice);
            //Take screenshot
            try {
                takeScreenshot(driver, "TC02_1.png");
            } catch (Exception e) {

            }
            // 5: Click on Sony Xperia mobile
            WebElement sonyXperiaLink = driver.findElement(By.xpath("//a[contains(text(),'Sony Xperia')]"));
            sonyXperiaLink.click();
            //Take screenshot
            try {
                takeScreenshot(driver, "TC02_2.png");
            } catch (Exception e) {

            }
            // 6: Read the Sony Xperia mobile from the detail page
            WebElement sonyXperiaDetailPriceElement = driver.findElement(By.xpath("//span[@class='price']"));
            String sonyXperiaDetailPrice = sonyXperiaDetailPriceElement.getText();
            // 7: Compare the product value in list and details page
            if (sonyXperiaPrice.equals(sonyXperiaDetailPrice)) {
                System.out.println("Product value in list and details page is equal: " + sonyXperiaPrice);
            } else {
                System.out.println("Product value in list and details page is not equal.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 8. Quit browser session
        driver.quit();
        // }
    }
}
