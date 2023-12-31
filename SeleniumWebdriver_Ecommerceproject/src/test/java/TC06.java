import POM.CartPage;
import POM.CheckOutPage;
import POM.LoginPage;
import driver.driverFactory;
import org.example.Register;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TC06 {
    public  TC06() {}
    private static  final  String imagesPath = "src/main/resources";
    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(TC06.imagesPath + fileName));
    }

    @Test
    public void TestCase06(){
        //LOGIN
        String email = "thanhncse172947@fpt.edu.vn";
        String password = ":Hiro:138";

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
            CartPage cartPage = new CartPage(driver);
            CheckOutPage checkOutPage = new CheckOutPage(driver);
            //2. Click on my account link
            registerPage.clickMyAccountLink();
            Thread.sleep(2000);

            for(String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            Thread.sleep(2000);
            //3. Login in application using previously created credential
            loginPage.EnterEmail(email);
            loginPage.EnterPassword(password);
            loginPage.clickLoginBtn();

            for(String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            // 4.Click on MY WISHLIST link
            driver.findElement(By.linkText("MY WISHLIST")).click();
            Thread.sleep(3000);

            for(String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            //5. In next page, Click ADD TO CART link
            driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
            Thread.sleep(3000);
            for(String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //6. Enter general shipping country, state/province and zip for the shipping cost estimate
            WebElement countryDropdownElement = driver.findElement(By.xpath("//select[@id='country']"));
            Select countrySelectOption = new Select(countryDropdownElement);
            countrySelectOption.selectByVisibleText("United States");
            // Choose region dropdown
            WebElement regionDropdownElement = driver.findElement(By.xpath("//select[@id='region_id']"));
            Select regionSelectOption = new Select(regionDropdownElement);
            regionSelectOption.selectByVisibleText("California");
            cartPage.enterPostcodeInput(postcode);

            // Step7. Click Estimate;
            driver.findElement(By.xpath(".//*[@id='shipping-zip-form']/div/button")).click();
            //debug
            Thread.sleep(2000);

            //Step 8. Verify Shipping cost generated
            String sFlatRate = "Flat Rate";
            String flatRate = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dt")).getText();
            try {
                System.out.println("sFlatRate = "+sFlatRate);
                System.out.println("flatRate = "+flatRate);
                Assertions.assertEquals(sFlatRate, flatRate);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String sFlatRatePrice = "Fixed - $5.00";
            String flatRatePrice = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dd/ul/li/label")).getText();
            try {
                System.out.println("sFlatRatePrice = "+sFlatRatePrice);
                System.out.println("flatRatePrice = "+flatRatePrice);
                Assertions.assertEquals(sFlatRatePrice, flatRatePrice);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                takeScreenshot(driver, "TC06+1.png");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // Step 9. Select Shipping Cost (already selected as default), Update Total
            driver.findElement(By.id("s_method_flatrate_flatrate")).click();
            driver.findElement(By.xpath("//button[@title='Update Total']")).click();
            //debug
            Thread.sleep(2000);

            // Step 10. Verify shipping cost is added to total
            String vFlatRatePrice = "$5.00";
            String shippingCostIncluded = driver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span")).getText();

            try {
                System.out.println("vFlatRatePrice = "+vFlatRatePrice);
                System.out.println("shippingCostIncluded = "+shippingCostIncluded);
                Assertions.assertEquals(vFlatRatePrice, shippingCostIncluded);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Screenshot
            try {
                takeScreenshot(driver, "TC06+2.png");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 11. Click PROCEED TO CHECKOUT
            checkOutPage.clickCheckOutButton();

            WebElement dropDownElementNewAddress = driver.findElement(By.xpath("//select[@id='billing-address-select']"));
            Select selectOptionNewAddress = new Select(dropDownElementNewAddress);
            selectOptionNewAddress.selectByVisibleText("New Address");

            //debug
            Thread.sleep(2000);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);


            //Step 12a. Enter Billing Information, and click Continue
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


            //Step 12b. Enter Shipping Information, and click Continue
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

            //Step 13. In Shipping Method, Click Continue

            driver.findElement(By.xpath(".//*[@id='shipping-method-buttons-container']/button")).click();

            Thread.sleep(2000);

            // Step 14. In Payment Information select 'Check/Money Order' radio button. Click Continue
            driver.findElement(By.xpath("//input[@title='Check / Money order']")).click();

            Thread.sleep(3000);

            driver.findElement(By.xpath(".//*[@id='payment-buttons-container']/button")).click();

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(3000);

            // Step 15. Click 'PLACE ORDER' button
            driver.findElement(By.xpath(".//*[@id='review-buttons-container']/button")).click();

            Thread.sleep(3000);

            // 16. Verify Oder is generated. Note the order number
            String orderNum = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/p[1]/a")).getText();
            System.out.println("*** Your order number for your record = " + orderNum);


            // this will take screenshot after success
            try {
                takeScreenshot(driver, "TC06+3.png");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
