import driver.driverFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TC01 {
    public  TC01() {}
    private static  final  String imagesPath = "src/main/resources";
    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(TC01.imagesPath + fileName));
    }

    @Test
    public void TestCase01() {
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // 2. Open target page - Login Form
            driver.get("http://live.techpanda.org/index.php/");
            // 3: Verify Title of the page
            String expectedTitle = "Home page";
            String actualTitle = driver.getTitle();
            if (actualTitle.contains(expectedTitle)) {
                System.out.println("Title matches: " + actualTitle);
            } else {
                System.out.println("Title doesn't match: " + actualTitle);
            }
            // 4: Click on MOBILE menu
            WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
            mobileMenu.click();
            try {
                takeScreenshot(driver, "TC01+1.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 5: Select SORT BY dropdown as name
            WebElement sortDropdown = driver.findElement(By.xpath("//select[@title='Sort By']"));
            Select sortSelect = new Select(sortDropdown);
            sortSelect.selectByVisibleText("Name");
            // 6: Take a screenshot after sort
            try {
                takeScreenshot(driver, "TC01+2.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 7. Quit browser session
        driver.quit();
    }
}
