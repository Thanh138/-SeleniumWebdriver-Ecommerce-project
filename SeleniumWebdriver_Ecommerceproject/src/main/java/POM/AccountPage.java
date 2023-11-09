package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    WebDriver driver;
    public AccountPage (WebDriver driver) {
        this.driver = driver;
    }
    By myOrder = By.linkText("MY ORDERS");
    By viewOrder = By.linkText("VIEW ORDER");
    By printOrder = By.linkText("Print Order");
    By reorder = By.linkText("REORDER");
    public void clickMyOrder() {
        driver.findElement(myOrder).click();
    }
    public void clickViewOrder(){
        driver.findElement(viewOrder).click();
    }
    public void clickPrintOrder(){
        driver.findElement(printOrder).click();
    }
    public void clickReOrder(){
        driver.findElement(reorder).click();
    }
}

