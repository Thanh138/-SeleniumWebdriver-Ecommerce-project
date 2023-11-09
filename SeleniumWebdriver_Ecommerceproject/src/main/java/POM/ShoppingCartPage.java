package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCartPage {
    WebDriver driver;
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }
     By qty = By.xpath("//input[@title='Qty']");
     By grandTotal = By.cssSelector("strong span[class='price']");
     By update = By.xpath("//button[@title='Update']");

    public void setQty(String number){
        WebElement qtyElement = driver.findElement(qty);
        qtyElement.click();
        qtyElement.clear();
        qtyElement.sendKeys(number);
    }
    public String getGrandTotal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(grandTotal)).getText();
    }
    public void clickUpdate(){
        driver.findElement(update).click();
    }
}
