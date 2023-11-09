package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutPage {
    WebDriver driver;
    //Billing
    By checkOutButton = By.xpath("//button[@title='Proceed to Checkout']");
    By firstNameInputLocator = By.id("billing:firstname");
    By lastnameInputLocator = By.id("billing:lastname");
    By addressInputLocator = By.id("billing:street1");
    By cityInputLocator = By.id("billing:city");
    By postcodeInputLocator = By.id("billing:postcode");
    By telephoneInputLocator = By.id("billing:telephone");

    //Shipping
    By shippingFirstName = By.id("shipping:firstname");
    By shippingLastName = By.id("shipping:lastname");
    By shippingAddress = By.id("shipping:street1");
    By shippingCity = By.id("shipping:city");
    By shippingTelephone = By.id("shipping:telephone");

     By OrderRecievedMessage = By.cssSelector(".sub-title");
     By orderNumberMessage = By.cssSelector(
            "body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > p:nth-child(3)");

    By MobileLink = By.xpath("//a[normalize-space()='Mobile']");

    By AddIphone = By.xpath("//li[1]//div[1]//div[3]//button[1]//span[1]//span[1]");

    By CounponCode = By.xpath("//input[@id='coupon_code']");

    By ApplyLink = By.xpath("//span[contains(text(),'Apply')]");

    By grandtotalLink = By.cssSelector("strong span[class='price']");


    //Constructor with required parameter as a WedDriver

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    //methods billing
    public void clickCheckOutButton(){
        driver.findElement(checkOutButton).click();
    }


    public void enterFirstName(String firstName){
        WebElement firstNameElement = driver.findElement(firstNameInputLocator);
        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);

    }
    public void enterLastName(String lastName){
        WebElement lastNameElement = driver.findElement(lastnameInputLocator);
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);

    }

    public void enterAddress(String address){
        WebElement addressElement = driver.findElement(addressInputLocator);
        addressElement.clear();
        addressElement.sendKeys(address);
    }

    public void enterCity(String city){
        WebElement cityElement = driver.findElement(cityInputLocator);
        cityElement.clear();
        cityElement.sendKeys(city);
    }

    public void enterPostcode(String postcode){
        WebElement postcodeElement = driver.findElement(postcodeInputLocator);
        postcodeElement.clear();
        postcodeElement.sendKeys(postcode);
    }

    public void enterTelephone(String telephone){
        WebElement telephoneElement = driver.findElement(telephoneInputLocator);
        telephoneElement.clear();
        telephoneElement.sendKeys(telephone);
    }

    //methods Shipping

    public void enterShippingFirstName(String firstname2){
        WebElement shippingFirstNameElement = driver.findElement(shippingFirstName);
        shippingFirstNameElement.clear();
        shippingFirstNameElement.sendKeys(firstname2);
    }

    public void enterShippingLastName(String lastname2){
        WebElement shippingLastNameElement = driver.findElement(shippingLastName);
        shippingLastNameElement.clear();
        shippingLastNameElement.sendKeys(lastname2);
    }
    public void enterShippingAddress(String address2){
        WebElement shippingAddressElement = driver.findElement(shippingAddress);
        shippingAddressElement.clear();
        shippingAddressElement.sendKeys(address2);
    }

    public void enterShippingCity(String city2){
        WebElement shippingCityElement = driver.findElement(shippingCity);
        shippingCityElement.clear();
        shippingCityElement.sendKeys(city2);
    }

    public void enterShippingTelephone(String telephone2){
        WebElement shippingTelephoneElement = driver.findElement(shippingTelephone);
        shippingTelephoneElement.clear();
        shippingTelephoneElement.sendKeys(telephone2);
    }

    public String getOrderRecievedMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(OrderRecievedMessage)).getText();
    }

    public String getOrderNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumberMessage)).getText();
    }

    public void clickMobileLink(){
        WebElement element = driver.findElement(MobileLink);
        element.click();
    }

    public void clickAddIphone(){
        WebElement element = driver.findElement(AddIphone);
        element.click();
    }

    public void enterCode(String code){
        WebElement element = driver.findElement(CounponCode);
        element.clear();
        element.sendKeys(code);
    }

    public String getgrandtotal(){
        String grandtotal = driver.findElement(grandtotalLink).getText();
        return grandtotal;
    }
    public void clickApply(){
        WebElement element = driver.findElement(ApplyLink);
        element.click();
    }


}
