package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(WebDriver driver) {
        super(driver);
    }


    public void fieldFormRegistrationTest() throws InterruptedException {
        click(By.xpath("//a[contains(text(),'New customers click here')]"));
        sendKeys("firstname", "London");
        sendKeys("lastname", "Jack");
        sendKeys("address1", "Boston, Lenina street, 15/1");
        sendKeys("postcode", "12345");
        sendKeys("city", "Boston");
        click(By.xpath("//*[contains(@title, 'Select')]"));
        click(By.xpath("//li[contains(., 'United States')]"));
        WebElement dropList = driver.findElement(By.xpath("//select[@name='zone_code']"));
        scriptExecutor("arguments[0].selectedIndex = 18; arguments[0].dispatchEvent(new Event('change'))", dropList);
        String email = time() + "@gmail.com";
        sendKeys("email", email);
        sendKeys("phone", "1234577");
        String password = "696969";
        sendKeys("password", password);
        sendKeys("confirmed_password", password);
        click(By.name("create_account"));
        click(By.xpath("//a[contains(text(),'Logout')]"));

        sendKeys("email", email);
        sendKeys("password", password);
        click(By.name("login"));
        click(By.xpath("//a[contains(text(),'Logout')]"));
    }


}


