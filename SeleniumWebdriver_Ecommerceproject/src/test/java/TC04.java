import driver.driverFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TC04 {
    public  TC04() {}
    private static  final  String imagesPath = "src/main/resources";
    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(TC04.imagesPath + fileName));
    }

    @Test
    public void TestCas04() {
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // 2. open target page
            driver.get("http://live.techpanda.org/");

            // 3: click on MOBILE menu
            WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
            mobileMenu.click();

            // 4: click on "Add To Compare" for Sony Xperia
            WebElement sonyXperiaCompare = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > ul:nth-child(2) > li:nth-child(3) > div:nth-child(2) > div:nth-child(4) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(2)"));
            sonyXperiaCompare.click();

            // 5: click on "Add To Compare" for iPhone
            WebElement iPhoneCompare = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > ul:nth-child(2) > li:nth-child(1) > div:nth-child(2) > div:nth-child(4) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(2)"));
            iPhoneCompare.click();

            // 6: click on "COMPARE" button
            WebElement compareButton = driver.findElement(By.cssSelector("button[title='Compare']"));
            compareButton.click();

            // 7: verify the pop-up window and check that the products are reflected in it
            String parentWindowHandle = driver.getWindowHandle();
            for (String windowHandle : driver.getWindowHandles()) {
                driver.switchTo().window(windowHandle);
            }
            WebElement header = driver.findElement(By.xpath("//h1"));
            if(header.getText().equals("COMPARE PRODUCTS")){
                System.out.println("Pop-up window display");
            }
            WebElement popUpContent = driver.findElement(By.id("product_comparison"));
            String popUpText = popUpContent.getText();
            if(popUpText.contains("SONY XPERIA") && popUpText.contains("IPhone")){
                System.out.println("Pop-up window contain compare products");
            }
            takeScreenshot(driver, "TC04.png");
            // 8: close the Popup Window
            driver.switchTo().window(parentWindowHandle);

        }catch (Exception e){
            e.printStackTrace();
        }
        // 9. Quit browser session
        driver.quit();
    }
}
