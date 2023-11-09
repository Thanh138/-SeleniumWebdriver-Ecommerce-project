import POM.AccountPage;
import POM.CheckOutPage;
import POM.LoginPage;
import POM.ShoppingCartPage;
import driver.driverFactory;
import org.example.Register;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TC08 {
    public  TC08() {}
    private static  final  String imagesPath = "src/main/resources";
    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(TC08.imagesPath + fileName));
    }

    @Test
    public void TestCae08() {
        //LOGIN
        String email = "thanhncse172947@fpt.edu.vn";
        String password = ":Hiro:138";

        //Update Info
        String qty = "2";

        //billing
        String postcode = "2000";
        String firstName ="A";
        String lastName= "B";
        String address = "1520 Roads";
        String city = "SAD City";
        String telephone= "0334363339";
        //shipping
        String firstname2 = "E";
        String lastname2 = "F";
        String address2 = "13123 RED";
        String city2 = "NOT LOSE City";
        String telephone2= "1892317811";

        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            LoginPage loginPage = new LoginPage(driver);
            Register registerPage = new Register(driver);
            AccountPage accountPage = new AccountPage(driver);
            ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
            CheckOutPage checkOutPage = new CheckOutPage(driver);
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

            //4. Click on 'REORDER' link , change QTY & click Update
            accountPage.clickReOrder();

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // change QTY & click Update
            String beforeUpdate = shoppingCartPage.getGrandTotal();
            System.out.println("Before update: " + beforeUpdate);
            shoppingCartPage.setQty(qty);
            shoppingCartPage.clickUpdate();
            String afterUpdate = shoppingCartPage.getGrandTotal();
            System.out.println("After update: " + afterUpdate);
            //Check before and after
            //5. Verify Grand Total is changed
            if(!beforeUpdate.equals(afterUpdate)){
                System.out.println("Grand Total has Change");
            }
            checkOutPage.clickCheckOutButton();

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            //6. Complete Billing & Shipping Information
            //6a. Billing Info
            WebElement dropDownElementNewAddress = driver.findElement(By.xpath("//select[@id='billing-address-select']"));
            Select selectOptionNewAddress = new Select(dropDownElementNewAddress);
            selectOptionNewAddress.selectByVisibleText("New Address");

            checkOutPage.enterFirstName(firstName);
            checkOutPage.enterLastName(lastName);
            checkOutPage.enterAddress(address);
            checkOutPage.enterCity(city);

            //select country

            WebElement dropdownElementNew = driver.findElement(By.xpath("//select[@id='billing:country_id']"));
            Select selectOptionNew = new Select(dropdownElementNew);
            selectOptionNew.selectByVisibleText("United Kingdom");
            checkOutPage.enterPostcode(postcode);
            checkOutPage.enterTelephone(telephone);

            // check"Ship to different address"
            driver.findElement(By.xpath("//label[@for='billing:use_for_shipping_no']")).click();

            driver.findElement(By.xpath(".//*[@id='billing-buttons-container']/button")).click();

            Thread.sleep(2000);

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            WebElement dropDownElementNewInformation = driver.findElement(By.xpath("//select[@id='shipping-address-select']"));
            Select selectOptionNewInformation = new Select(dropDownElementNewInformation);
            selectOptionNewInformation.selectByVisibleText("New Address");
            Thread.sleep(2000);


            //6b. Enter Shipping Information, and click Continue
            checkOutPage.enterShippingFirstName(firstname2);
            checkOutPage.enterShippingLastName(lastname2);
            checkOutPage.enterShippingAddress(address2);
            checkOutPage.enterShippingCity(city2);
            checkOutPage.enterShippingTelephone(telephone2);
            driver.findElement(By.xpath(".//*[@id='shipping-buttons-container']/button")).click();

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            //6c. In Shipping Method, Click Continue

            driver.findElement(By.xpath(".//*[@id='shipping-method-buttons-container']/button")).click();

            Thread.sleep(2000);

            // 6d. In Payment Information select 'Check/Money Order' radio button. Click Continue
            driver.findElement(By.xpath("//input[@title='Check / Money order']")).click();

            Thread.sleep(3000);

            driver.findElement(By.xpath(".//*[@id='payment-buttons-container']/button")).click();

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(3000);

            // 6e. Click 'PLACE ORDER' button
            driver.findElement(By.xpath(".//*[@id='review-buttons-container']/button")).click();

            Thread.sleep(3000);

            // 7. Verify Oder is generated. Note the order number
            String orderNum = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/p[1]/a")).getText();
            System.out.println("*** Your order number for your record = " + orderNum);
            String actualMessage = checkOutPage.getOrderRecievedMessage();
            String expectedMessage = "THANK YOU FOR YOUR PURCHASE!";

            if(expectedMessage.equals(actualMessage)){
                System.out.println("Order sent succeed");
            }else{
                System.out.println( "Order failed!");
            }
            try {
                takeScreenshot(driver, "TC08.png");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {

        }
    }
}
