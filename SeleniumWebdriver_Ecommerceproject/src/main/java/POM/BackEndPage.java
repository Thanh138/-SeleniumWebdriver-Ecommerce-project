package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BackEndPage {
        WebDriver driver;
        By inputUserName = By.xpath("//input[@id='username']");
        By inputPassWord = By.xpath("//input[@id='login']");
        By btnLogin = By.xpath("//input[@title='Login']");

        By btnClose = By.xpath("//span[normalize-space()='close']");
        By drpSale = By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)");

        By inputOrderId = By.xpath("//input[@id='sales_order_grid_filter_real_order_id']");
        By fromDate = By.cssSelector("body > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > table:nth-child(1) > thead:nth-child(2) > tr:nth-child(2) > th:nth-child(3) > div:nth-child(1) > div:nth-child(1) > input:nth-child(2)");

        By toDate = By.cssSelector("body > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > table:nth-child(1) > thead:nth-child(2) > tr:nth-child(2) > th:nth-child(3) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)");
        By Orders = By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > ul:nth-child(1) > li:nth-child(1) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1) > span:nth-child(1)");

        By Search = By.xpath("//span[contains(text(),'Search')]");
        public void enterUserName(String userName){
            WebElement element = driver.findElement(inputUserName);
            element.clear();
            element.sendKeys(userName);
        }

        public void enterPassWord(String passWord){
            WebElement element = driver.findElement(inputPassWord);
            element.clear();
            element.sendKeys(passWord);
        }

        public void clickLogin(){
            WebElement element = driver.findElement(btnLogin);
            element.click();
        }
        public void chooseSale() {
            WebElement element = driver.findElement(drpSale);
            element.click();
        }

        public void clickOrders(){
            WebElement element = driver.findElement(Orders);
            element.click();
        }

        public void clickClose(){
            WebElement element = driver.findElement(btnClose);
            element.click();
        }

        public void enterOrderId(String id){
            WebElement element = driver.findElement(inputOrderId);
            element.clear();
            element.sendKeys(id);
        }

        public void enterFromDate(String date){
            WebElement element = driver.findElement(fromDate);
            element.clear();
            element.sendKeys(date);
        }

    public void enterToDate(String date){
        WebElement element = driver.findElement(toDate);
        element.clear();
        element.sendKeys(date);
    }

        public void clickSearch(){
            WebElement element = driver.findElement(Search);
            element.click();
        }

        public BackEndPage(WebDriver driver){
            this.driver = driver;
        }
}
