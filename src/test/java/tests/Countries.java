package tests;

import appmanager.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;

import static org.testng.Assert.fail;

public class Countries extends HelperBase {


    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "http://localhost/litecart/admin/?app=countries&doc=countries";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        js = (JavascriptExecutor) driver;
        driver.get(baseUrl);
        sendKeys("admin", "username");
        sendKeys("admin", "password");
        driver.findElement(By.name("login")).click();
    }


    @Test
    public void countriesTest() {
        for (int i = 2; i < 240; i++) {
            WebElement nameCountries = driver.findElement(By.xpath("//tr[" + i + "]/td[5]/a"));
            WebElement zones = driver.findElement(By.xpath("//tr[" + i + "]/td[6]"));
            String textZoner = zones.getText();
            int textZonerInt = Integer.parseInt(String.valueOf(textZoner));
//            System.out.println(textZonerInt);
            String textCountries = nameCountries.getText();
            if (textZonerInt > 0) {
                nameCountries.click();
                int size = driver.findElements(By.xpath("//td[3]")).size();
                for (int j = 2; j < size; j++) {
                    WebElement nameCountriesFotZones = driver.findElement(By.xpath("//tr[" + j + "]/td[3]"));
                    String fotZonesText = nameCountriesFotZones.getText();
                    ArrayList<String> list = new ArrayList<>();
                    list.add(fotZonesText);
//                    System.out.println(list);
                    ArrayList<String> listAfterForZones = list; //создаем дубликат списка
                    Collections.sort(listAfterForZones); //сортируем дубликат полученного списка

                    list.equals(listAfterForZones); //проверяем равенство списков
                }
                driver.findElement(By.xpath("//button[@name='cancel']")).click();
            }
            ArrayList<String> list = new ArrayList<>();
            list.add(textCountries);
//            System.out.println(list);
            ArrayList<String> listAfter = list;
            Collections.sort(listAfter);

            list.equals(listAfter);
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
