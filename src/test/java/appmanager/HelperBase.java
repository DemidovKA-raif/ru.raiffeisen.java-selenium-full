package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class HelperBase {
    protected WebDriver driver;
    protected String baseUrl;
    protected StringBuffer verificationErrors = new StringBuffer();
    protected JavascriptExecutor js;
//    WebDriverWait wait = new WebDriverWait(driver, 2/*seconds*/);



    protected void scriptExecutor(String Script, WebElement Arguments) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(Script, Arguments);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

//    public void clickWait(By locator) {
//        wait.until(visibilityOf(driver.findElement((locator)))).click();
//    }

    public void sendKeys(String name, String value) {
        driver.findElement(By.name(name));
        driver.findElement(By.name(name)).clear();
        driver.findElement(By.name(name)).sendKeys(value);
    }

    public String time (){
        Date moment = new Date(); // Задаем количество миллисекунд Unix-time с того-самого-момента
       return String.valueOf(moment.getTime());
    }
}
