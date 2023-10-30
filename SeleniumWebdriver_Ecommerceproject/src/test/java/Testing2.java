import org.example.Register;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Testing2 {

    public  Testing2() {}
    private static  final  String imagesPath = "src/main/resources";
    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(Testing2.imagesPath + fileName));
    }
    private boolean checkSorted(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void TC03() {
        WebDriver driver = new ChromeDriver();
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\OneDrive\\Desktop\\SWT\\chromedriver-win64\\chromedriver.exe");
            driver.get("http://live.techpanda.org/");
            var MobileLink = driver.findElement(By.cssSelector("[href='http://live.techpanda.org/index.php/mobile.html']"));
            MobileLink.click();
            WebElement sonyXperiaAddToCart = driver.findElement(By.xpath("//li[2]//div[1]//div[3]//button[1]"));
            sonyXperiaAddToCart.click();
            //4
            WebElement qtyInput = driver.findElement(By.xpath("//input[@title='Qty']"));
            qtyInput.clear();
            qtyInput.sendKeys("1000");
            var btnUpdate = driver.findElement(By.xpath("//button[@title='Update']"));
            btnUpdate.click();
            try {
                takeScreenshot(driver, "TC03.png");
            } catch (Exception ex) {

            }
            Thread.sleep(2000);
            //5
            String reqQty = driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[2]/p")).getText();
            String msgQty = ("* The maximum quantity allowed for purchase is 500.");
            try {
                assertEquals(reqQty, msgQty);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //6
            WebElement emptyCartLink = driver.findElement(By.xpath("//button[@title='Empty Cart']"));
            emptyCartLink.click();
            //7
            String noItemsStg = ("You have no items in your shopping cart.");
            String noItemsMsg = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/div[2]/p[1]")).getText();
            System.out.println("You have no items message = " + noItemsMsg);

            try {
                assertEquals(noItemsStg, noItemsMsg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void TC04() {
        WebDriver driver = new ChromeDriver();
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\OneDrive\\Desktop\\SWT\\chromedriver-win64\\chromedriver.exe");
            driver.get("http://live.techpanda.org/");
            var MobileLink = driver.findElement(By.cssSelector("[href='http://live.techpanda.org/index.php/mobile.html']"));
            MobileLink.click();
            //3.In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)
            WebElement sonyXperiaCompare = driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[2]//a[1]"));
            sonyXperiaCompare.click();

            WebElement iphoneCompare = driver.findElement(By.xpath("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]"));
            iphoneCompare.click();
            //4.Click on �COMPARE� button. A popup window opens
            WebElement compareButton = driver.findElement(By.xpath("//button[@title='Compare']//span//span[contains(text(),'Compare')]"));
            compareButton.click();
            //5.Verify the pop-up window and check that the products are reflected in it
            //
            //Heading "COMPARE PRODUCTS" with selected products in it.
            for(String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            String mes = "COMPARE PRODUCTS";
            WebElement headerPopup = driver.findElement(By.xpath("//h1[normalize-space()='Compare Products']"));
            Assert.assertEquals(headerPopup.getText(), mes);

            WebElement samsumWeb = driver.findElement(By.xpath("//a[@title='Samsung Galaxy'][normalize-space()='Samsung Galaxy']"));
            WebElement iphoneWeb = driver.findElement(By.xpath("//a[@title='IPhone'][normalize-space()='IPhone']"));

            WebElement ihponePopup = driver.findElement(By.xpath("//a[normalize-space()='IPhone']"));
            WebElement samsungPopup = driver.findElement(By.xpath("//a[normalize-space()='Samsung Galaxy']"));

            Assert.assertEquals(samsumWeb.getText(), samsungPopup.getText());
            Assert.assertEquals(iphoneWeb.getText(), ihponePopup.getText());

            try {
                takeScreenshot(driver, "TC04.png");
            } catch (Exception ex) {

            }
            WebElement closePopupButton = driver.findElement(By.xpath("//button[@title='Close Window']"));
            closePopupButton.click();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail("Xảy ra lỗi trong quá trình kiểm thử.");
        }
        finally {
            driver.quit();
        }
    }

    @Test
    public void TC05() {
        WebDriver driver = new ChromeDriver();
        String firstname = "ABC";
        String middlename = "TESTCASE";
        String lastname = "04";
        String email_address ="test1321222@fpt.edu.vn";
        String password = ":Hiro:138";
        String confirm_password = password;
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\OneDrive\\Desktop\\SWT\\chromedriver-win64\\chromedriver.exe");
            driver.get("http://live.techpanda.org/");
            //2.Click on my account link
            Register registerPage = new Register(driver);
            registerPage.clickRegister();

            for(String handle: driver.getWindowHandles()){
                driver.switchTo().window(handle);
            }

            //3.Click Create an Account link and fill New User information excluding the registered Email ID.
            registerPage.clickCreateAccountLink();
            for(String handle: driver.getWindowHandles()){
                driver.switchTo().window(handle);
            }
            registerPage.enterFirstName(firstname);
            Thread.sleep(1000);
            registerPage.enterMiddleName(middlename);
            Thread.sleep(1000);
            registerPage.EnterLastName(lastname);
            Thread.sleep(1000);
            registerPage.enterEmail(email_address);
            Thread.sleep(1000);
            registerPage.enterPassword(password);
            Thread.sleep(1000);
            registerPage.enterConfirmPassword(confirm_password);
            Thread.sleep(1000);

            //4. Click Register
            registerPage.getRegisterButton();
            Thread.sleep(1000);

            for(String handle: driver.getWindowHandles()){
                driver.switchTo().window(handle);
            }

            //5. Verify Registration is done. Expected account registration done
            String expectWelcome = ("HELLO, " + firstname.toUpperCase() + " " + middlename.toUpperCase() + " " + lastname.toUpperCase() + "!");
            String actualWelcome = driver.findElement(By.cssSelector("p[class='hello'] strong")).getText().toUpperCase();
            System.out.println("actualWelcome = " + actualWelcome);

            Assert.assertEquals(expectWelcome, actualWelcome);


            //6.
            driver.findElement(By.xpath("//a[normalize-space()='TV']")).click();
            Thread.sleep(2000);
            //7. Add product in your wish list - use product - LG LCD
            driver.findElement(By.xpath("(//a[@class='link-wishlist'][normalize-space()='Add to Wishlist'])[1]")).click();
            Thread.sleep(2000);
            //        8. Click SHARE WISHLIST
            driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();
            Thread.sleep(2000);

            for(String handle: driver.getWindowHandles()){
                driver.switchTo().window(handle);
            }
//        9. In next page enter Email and a message and click SHARE WISHLIST
            driver.findElement(By.xpath("//textarea[@id='email_address']")).sendKeys("thanhncse172947@fpt.edu.vn");
            Thread.sleep(2000);

//        10.Check wishlist is shared. Expected wishlist shared successfully.
            driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Testing share list with you!");
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//span[contains(text(),'Share Wishlist')])[1]")).click();
            Thread.sleep(2000);

            try {
                takeScreenshot(driver, "TC05.png");            }
            catch (Exception ex) {

            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail("Xảy ra lỗi trong quá trình kiểm thử.");
        }
        finally {
            driver.quit();
        }
    }
}
