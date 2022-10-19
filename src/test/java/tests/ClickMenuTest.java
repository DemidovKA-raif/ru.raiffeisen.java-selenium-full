package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class ClickMenuTest extends BaseTest {

@Test
    public void testFirst() throws InterruptedException {
    for (int i = 1; i < 17; i++) {
    WebElement elementMenuHead = driver.findElement(By.xpath("//li[@id='app-'][" + i + "]"));
        elementMenuHead.click();
        Thread.sleep(500);
        }
//    driver.findElements(By.xpath("//*[@class='docs']")).iterator().next().click();
}


//    @Test
//    public void testFirst() throws InterruptedException {
//
//        WebElement elementMenuHead = driver.findElement(By.id("box-apps-menu-wrapper"));
//        elementMenuHead.click();
//        Thread.sleep(500);
//
//
//
//        elementMenuHead.findElement(By.tagName("li")).click();
//        Thread.sleep(500);
//    }
//
//   public boolean areElementsPresent(By locator) {
//        return driver.findElements(locator).size() > 0;
//    }
}
