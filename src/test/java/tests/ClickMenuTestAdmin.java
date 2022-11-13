package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ClickMenuTestAdmin extends BaseTestAdmin {

    @Test
    public void testFirst() throws InterruptedException {
        for (int i = 1; i < 17; i++) {
            WebElement elementMenuHead = driver.findElement(By.xpath("//li[@id='app-'][" + i + "]"));
            elementMenuHead.click();
            int hStyle = driver.findElements(By.xpath("//h1")).size();
            assertEquals(hStyle, 1);
            for (int s = 1; s < 10; s++) {
                boolean elementPresent = isElementPresent(By.xpath(".//*[contains(@id, 'doc')][" + s + "]"));
                if (!elementPresent) {
                    break;
                }
                WebElement li = driver.findElement(By.xpath(".//*[contains(@id, 'doc')][" + s + "]"));
                li.click();
                int hStyleSubtitle = driver.findElements(By.xpath("//h1")).size();
                assertEquals(hStyleSubtitle, 1);
            }
        }
    }


    boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}