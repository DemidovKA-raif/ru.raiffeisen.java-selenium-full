package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class DucksTest {
    protected WebDriver driver;
    protected String baseUrl;
    protected StringBuffer verificationErrors = new StringBuffer();


    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "http://localhost/litecart/en/";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(baseUrl);
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void testDuckStickers() {
        for (int i = 0; i < 11; i++) {
            WebElement ducks = driver.findElement(By.xpath("//a[@class='link' and contains(@title, 'Duck')]"));
            int sizeDuckStickers =
                    ducks.findElements(By.xpath(".//*[contains(@class, 'sticker')]")).size();
            System.out.println(sizeDuckStickers);
            assertEquals(sizeDuckStickers, 1);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
