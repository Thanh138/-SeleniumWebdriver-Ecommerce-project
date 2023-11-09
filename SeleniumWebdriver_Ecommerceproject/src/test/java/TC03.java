import driver.driverFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TC03 {
    public  TC03() {}
    private static  final  String imagesPath = "src/main/resources";
    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(TC03.imagesPath + fileName));
    }
    @Test
    public void TestCase03() {
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // 2. open target page
            driver.get("http://live.techpanda.org/index.php/");
            // 3: click on MOBILE menu
            WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
            mobileMenu.click();
            // 4: click Add to cart
            WebElement addToCart =  driver.findElement(By.xpath("//h2[a='Sony Xperia']/following-sibling::div//button[@title='Add to Cart']"));
            addToCart.click();
            // 5: select QTY
            WebElement qty = driver.findElement(By.xpath("//input[@title='Qty']"));
            qty.clear();
            qty.sendKeys("1000");
            // 6: click Update
            WebElement update = driver.findElement(By.xpath("//button[@title='Update']"));
            update.click();
            // 7: verify the error message
            WebElement errorMessage = driver.findElement(By.xpath("//li[@class='error-msg']"));
            String expectedErrorMessage = "The requested quantity for 'Sony Xperia' is not available";
            if(!errorMessage.getText().equals(expectedErrorMessage)){
                System.out.println("Error message is not matched");
            }else{
                System.out.println("Error message is matched");
            }
            // 8: click empty cart
            WebElement emptyCartLink = driver.findElement(By.xpath("//button[@title='Empty Cart']"));
            emptyCartLink.click();
            // 9: verify emty cart
            WebElement emptyCartMessage = driver.findElement(By.xpath("//h1[text()='Shopping Cart is Empty']"));
            if (emptyCartMessage.isDisplayed()) {
                System.out.println("Shopping cart is empty.");
            } else {
                System.out.println("Shopping cart is not empty.");
            }
            takeScreenshot(driver, "TC03.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 9. Quit browser session
        driver.quit();
    }
}
