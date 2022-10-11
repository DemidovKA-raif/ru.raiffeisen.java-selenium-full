package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    protected WebDriver driver;
    protected String baseUrl;
    protected StringBuffer verificationErrors = new StringBuffer();
    protected JavascriptExecutor js;
    private boolean acceptNextAlert = true;

    protected void sendKeys(String login, String locator) {
        driver.findElement(By.name(locator)).click();
        driver.findElement(By.name(locator)).clear();
        driver.findElement(By.name(locator)).sendKeys(login);
    }
}
