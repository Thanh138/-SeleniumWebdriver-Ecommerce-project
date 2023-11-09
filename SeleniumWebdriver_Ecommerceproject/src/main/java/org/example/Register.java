package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class Register {
    WebDriver driver;

    By myAccountLink = By.xpath("(//a[@title='My Account'][normalize-space()='My Account'])[2]");
    By crateAccountLink = By.xpath("//a[@title='Register']");
    By firstnameInput = By.xpath("//input[@id='firstname']");
    By lastnameInput = By.xpath("//input[@id='lastname']");
    By MiddleNameInput = By.xpath("//input[@id='middlename']");
    By emailInput = By.xpath("//input[@id='email_address']");
    By passwordInput = By.xpath("//input[@id='password']");
    By confirmPasswordInput = By.xpath("//input[@id='confirmation']");
    By registerButton = By.cssSelector("button[title='Register']");
    By getRegisterButton = By.xpath("//button[@title='Register']");
    public Register(WebDriver driver){
        this.driver = driver;
    }

    public void clickMyAccountLink() {
        driver.findElement(myAccountLink).click();
    }

    public void clickCreateAccountLink() {

        driver.findElement(crateAccountLink).click();
    }

    public void enterFirstName(String firstName) {
        WebElement element = driver.findElement(firstnameInput);
        element.clear();
        element.sendKeys(firstName);
    }

    public void enterMiddleName(String middleName) {
        WebElement element = driver.findElement(MiddleNameInput);
        element.clear();
        element.sendKeys(middleName);
    }

    public void EnterLastName(String lastname) {
        WebElement element = driver.findElement(lastnameInput);
        element.clear();
        element.sendKeys(lastname);
    }
    public void enterEmail(String email) {
        WebElement element = driver.findElement(emailInput);
        element.clear();
        element.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement element = driver.findElement(passwordInput);
        element.clear();
        element.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        WebElement element = driver.findElement(confirmPasswordInput);
        element.clear();
        element.sendKeys(confirmPassword);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    public void getRegisterButton() {
        driver.findElement(getRegisterButton).click();
    }
}