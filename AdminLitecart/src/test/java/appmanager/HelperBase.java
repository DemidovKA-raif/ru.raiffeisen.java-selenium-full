package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Date;

public class HelperBase {


    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver driver;
//    protected String baseUrl;
    protected StringBuffer verificationErrors = new StringBuffer();
    protected JavascriptExecutor js;


    protected void scriptExecutor(String Script, WebElement Arguments) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(Script, Arguments);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

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
