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


    @Test
    public void testDuckName() {
        WebElement campaigns = driver.findElement(By.xpath("//*[@id='box-campaigns']//*[contains(@class, 'name')]"));
        String price = driver.findElement(By.xpath("//*[@id='box-campaigns']//*[contains(@class, 'price-wrapper')]")).getText();
        String normalPrice = driver.findElement(By.xpath("//*[@id='box-campaigns']//*[contains(@class, 'regular-price')]")).getText();
        String salePrice = driver.findElement(By.xpath("//*[@id='box-campaigns']//*[contains(@class, 'campaign-price')]")).getText();

        String attributeForTable = campaigns.getAttribute("innerText");

        int s = Integer.parseInt(removeNumeric(normalPrice));
        int s1 = Integer.parseInt(removeNumeric(salePrice));

        equals(s > s1);
        campaigns.click();

//        String color = normalPrice.getCssValue("color");
//        System.out.println(color);
        String attributeForPage = driver.findElement(By.xpath("//h1[@class='title']")).getAttribute("textContent");
        String priceForPage = driver.findElement(By.xpath("//div[@itemprop='offers']")).getText();
        String normalPricePage = driver.findElement(By.xpath("//s[@class='regular-price']")).getText();
        String salePricePage = driver.findElement(By.xpath("//strong[@class='campaign-price']")).getText();

        int q = Integer.parseInt(removeNumeric(normalPricePage));
        int q1 = Integer.parseInt(removeNumeric(salePricePage));

        equals(q > q1);
        assertEquals(attributeForTable, attributeForPage);
        assertEquals(price, priceForPage);
    }

    public static String removeNumeric(String str) {
        return str.replaceAll("[^\\d]", "");
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
