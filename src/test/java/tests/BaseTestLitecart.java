package tests;

import appmanager.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static org.testng.Assert.fail;

public class BaseTestLitecart extends HelperBase {

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
        driver = new ChromeDriver(capabilities);
        baseUrl = "http://localhost/litecart/en/";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(baseUrl);
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
