package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    WebDriver driver;
    By OrderLink = By.xpath("//a[normalize-space()='My Orders']");
    By ViewOrder = By.xpath("//tr[@class='first odd']//a[contains(text(),'View Order')]");
    By PrintOrder = By.xpath("//a[@class='link-print']");

    By GrandTotal = By.cssSelector("strong span[class='price']");
    By ReOrder = By.xpath("//tr[@class='first odd']//a[@class='link-reorder'][normalize-space()='Reorder']");

    By ChangeQty = By.xpath("//input[@title='Qty']");
    By UpdateQty = By.xpath("//button[@title='Update']");
    public OrderPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickOrderLink(){
        driver.findElement(OrderLink).click();
    }

    public void clickViewOrder(){
        driver.findElement(ViewOrder).click();
    }

    public void clickPrintOrder(){
        driver.findElement(PrintOrder).click();
    }

    public void clickReOrder(){
        driver.findElement(ReOrder).click();
    }

    public String getGrandTotal(){
        return  driver.findElement(GrandTotal).getText();
    }

    public void ChangeQty(){
        WebElement element = driver.findElement(ChangeQty);
        element.clear();
        element.sendKeys("10");
    }

    public void UpdateQty(){
        driver.findElement(UpdateQty).click();
    }
}
