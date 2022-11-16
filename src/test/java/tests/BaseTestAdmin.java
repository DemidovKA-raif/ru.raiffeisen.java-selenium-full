package tests;

import appmanager.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class BaseTestAdmin extends HelperBase {


    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "http://localhost/litecart/admin/login.php?redirect_url=%2Flitecart%2Fadmin%2F";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        js = (JavascriptExecutor) driver;
        driver.get(baseUrl);
        sendKeys("username", "admin");
        sendKeys("password", "admin");
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


    public void fieldFormProduct() throws InterruptedException {
        String time = time();
        generalPage(time);
        informationPage();
        pricesPage();

        WebElement elementForTable = driver.findElement(By.xpath("//a[contains(text(),'" + time + "')]"));
        assertTrue(elementForTable.isDisplayed());
    }

    public void pricesPage() throws InterruptedException {
        click(By.xpath("//a[contains(text(),'Prices')]"));
        WebElement purchasePrice = driver.findElement(By.name("purchase_price"));
        for (int i = 0; i < 10; i++) {
            purchasePrice.sendKeys(Keys.UP);
        }
        WebElement currencyCode = driver.findElement(By.name("purchase_price_currency_code"));
        scriptExecutor("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", currencyCode);
        WebElement taxClass = driver.findElement(By.name("tax_class_id"));
        scriptExecutor("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", taxClass);
        sendKeys("prices[USD]", "10");
        sendKeys("prices[EUR]", "10");
        click(By.name("save"));
    }


    public void informationPage() throws InterruptedException {
        click(By.xpath("//a[contains(text(),'Information')]"));
        Thread.sleep(1000); //выполняется и без слипов, сделал из-за предупреждения в задании

        WebElement manufacturer = driver.findElement(By.name("manufacturer_id"));
        manufacturer.click();
        scriptExecutor("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", manufacturer);
        WebElement supplier = driver.findElement(By.name("supplier_id"));
        supplier.click();
        scriptExecutor("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", supplier);
        sendKeys("keywords", "fatduck");
        sendKeys("short_description[en]", "by duck, don t ...");
        WebElement description = driver.findElement(By.cssSelector("div.trumbowyg-editor"));
        description.sendKeys("duck1");
        sendKeys("head_title[en]", "duck2");
        sendKeys("meta_description[en]", "duck3");
    }


    public void generalPage(String value) throws InterruptedException {
        click(By.xpath("//span[contains(text(),'Catalog')]"));
        Thread.sleep(1000);

        click(By.xpath("//a[@class='button' and contains(text(),' Add New Product')]"));
        click(By.name("status"));
        WebElement name = driver.findElement(By.name("name[en]"));
        name.sendKeys(value);
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
        newFile.sendKeys(new File("src/test/resources/duck.jpeg").getAbsolutePath());

    }
}
