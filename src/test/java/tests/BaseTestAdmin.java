package tests;

import appmanager.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static org.testng.Assert.fail;

public class BaseTestAdmin extends HelperBase {

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "http://localhost/litecart/admin/login.php?redirect_url=%2Flitecart%2Fadmin%2F";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        js = (JavascriptExecutor) driver;
        driver.get(baseUrl);
        sendKeys("username","admin");
        sendKeys("password","admin");
        click(By.name("login"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    protected void fieldFormProduct() {
        click(By.xpath("//span[contains(text(),'Catalog')]"));
        click(By.xpath("//a[@class='button' and contains(text(),' Add New Product')]"));
        sendKeys("name[en]", time());
        sendKeys("code", "1234567");
        click(By.xpath("//input[@data-name='Rubber Ducks']"));
        click(By.xpath("//input[@data-name='Subcategory']"));
        WebElement category_id = driver.findElement(By.name("default_category_id"));
        scriptExecutor("arguments[0].selectedIndex = 2; arguments[0].dispatchEvent(new Event('change'))", category_id);
        String productGroups = "1-3";
        click(By.xpath("//input[@name='product_groups[]' and (@value='" + productGroups + "')]"));
        sendKeys("quantity", "100");
        WebElement calendar = driver.findElement(By.name("date_valid_from"));
        sendKeys("date_valid_from", "20.08.2021");
        sendKeys("date_valid_to", "20.08.2023");


        WebElement newFile = driver.findElement(By.name("new_images[]"));
    }
}
