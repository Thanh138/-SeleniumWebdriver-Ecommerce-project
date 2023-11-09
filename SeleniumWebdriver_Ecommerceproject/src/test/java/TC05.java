import driver.driverFactory;
import org.example.Register;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class TC05 {
    public  TC05() {}
    private static  final  String imagesPath = "src/main/resources";
    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(TC05.imagesPath + fileName));
    }
    private String generateRandomEmail() {
        // Generate a random email using UUID
        String emailPrefix = UUID.randomUUID().toString().substring(0, 8);
        return emailPrefix + "@gmail.com";
//        return UUID.randomUUID().toString() + "@example.com";
        ////
    }
    @Test
    public void TestCase05() {
        WebDriver driver = driverFactory.getChromeDriver();
        try {


            // Step 1: Go to the website
            driver.get("http://live.techpanda.org/");

            // Step 3: Click on ACCOUNT
            WebElement accountMenu = driver.findElement(By.cssSelector(".account-cart-wrapper .label"));
            accountMenu.click();

            //Step 4: Click on Register
            WebElement registerMenu = driver.findElement(By.cssSelector("a[title='Register']"));
            registerMenu.click();

            //Step 5: Input fields
            Register registerPage = new Register(driver);

            // Step 5: Input fields using the Page Object Model
            registerPage.enterFirstName("Test");
            registerPage.enterMiddleName("Case ");
            registerPage.EnterLastName("05");
            String randomEmail =generateRandomEmail();
            registerPage.enterEmail(randomEmail);
            registerPage.enterPassword("123456");
            registerPage.enterConfirmPassword("123456");

            registerPage.getRegisterButton();

            //Step 7: Click on TV
            Thread.sleep(3000);
            for(String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //Step 7: Click on TV
            WebElement tvMenu = driver.findElement(By.linkText("TV"));
            tvMenu.click();

            try {
                Thread.sleep(1000); // Độ trễ 10 giây (10.000 milliseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Step 8: Click on Add To Wishlist
            WebElement wishList = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > ul:nth-child(2) > li:nth-child(1) > div:nth-child(2) > div:nth-child(4) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1)"));
            wishList.click();

            //
            try {
                Thread.sleep(1000); // Độ trễ 10 giây (10.000 milliseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Step 9: Click on Share WishList
            WebElement shareWishList = driver.findElement(By.cssSelector("button[title='Share Wishlist']"));
            shareWishList.click();

            try {
                Thread.sleep(1000); // Độ trễ 10 giây (10.000 milliseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Step 10: Input shared Email
            WebElement sharedEmail = driver.findElement(By.cssSelector("#email_address"));
            sharedEmail.click();
            sharedEmail.sendKeys("thanhncs172947@fpt.edu.vn");

            try {
                Thread.sleep(1000); // Độ trễ 10 giây (10.000 milliseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Step 11: Input message
            WebElement sharedMessage = driver.findElement(By.cssSelector("#message"));
            sharedMessage.click();
            sharedMessage.sendKeys("See yah");

            try {
                Thread.sleep(1000); // Độ trễ 10 giây (10.000 milliseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Step 12: Share WishList
            WebElement share = driver.findElement(By.cssSelector("button[title='Share Wishlist']"));
            share.click();

            //Step 13: Check
            WebElement successMessageElement = driver.findElement(By.cssSelector("li[class='success-msg'] ul li span"));
            String successMessage = successMessageElement.getText();
            String expectedMessage = "Your Wishlist has been shared.";

            if (successMessage.equals(expectedMessage)) {
                System.out.println("Wishlist shared successfully.");
            } else {
                System.out.println("Failed to share wishlist.");
            }
            Assertions.assertEquals(successMessage, expectedMessage);
            takeScreenshot(driver, "TC05.png");
            try {
                Thread.sleep(1000); // Độ trễ 10 giây (10.000 milliseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Quit
    }

}
