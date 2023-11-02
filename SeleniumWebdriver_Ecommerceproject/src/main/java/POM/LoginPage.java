package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    WebDriver driver;
    By emailInputLocator = By.id("email");
    By passwordInputLocator = By.id("pass");

    By loginButton = By.xpath("//button[@id='send2']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void EnterEmail(String email) {
        WebElement emailElement = driver.findElement(emailInputLocator);
        emailElement.click();
        emailElement.sendKeys(email);
    }

    public void EnterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordInputLocator);
        passwordElement.click();
        passwordElement.sendKeys(password);
    }

    public void clickLoginBtn()  {
        driver.findElement(loginButton).click();
    }
}
