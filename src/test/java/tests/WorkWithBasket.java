package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class WorkWithBasket extends BaseTestLitecart {

    @Test
    public void byDuck() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 2/*seconds*/);
        int a = 4;
        for (int i = 1; i < a; i++) {
            wait.until(visibilityOf(driver.findElement(By.xpath("//div[@class='image-wrapper']")))).click();
            if (isElementPresent(By.xpath("//select"))) { //если попадается желтая утра, то у нее обязательный Селект всплывает, не стал обрабатывать его
                i--;
                wait.until(visibilityOf(driver.findElement(By.xpath("//img[@alt='My Store']")))).click();
                wait.until(visibilityOf(driver.findElement(By.xpath("//div[@class='image-wrapper']")))).click();
            }
            wait.until(visibilityOf(driver.findElement(By.name("add_cart_product")))).click();
            wait.until(visibilityOf(driver.findElement(By.xpath("//span[@class='quantity' and contains(text(), '" + i + "')]"))));
            click(By.xpath("//img[@alt='My Store']"));
        }
        click(By.xpath("//a[contains(text(),'Checkout »')]"));
        for (int i = 1; i < a; i++) {
            wait.until(visibilityOf(driver.findElement(By.name("quantity")))).click();
            wait.until(visibilityOf(driver.findElement(By.name("quantity")))).sendKeys(Keys.DOWN);
            String nameDuck = driver.findElement(By.xpath("//form[@name='cart_form']//strong")).getText();
            isElementPresent(By.xpath("//div[@id='order_confirmation-wrapper']//td[contains(text(),'" + nameDuck + "')]"));
            wait.until(visibilityOf(driver.findElement(By.name("update_cart_item")))).click();
            isElementNotPresent(By.xpath("//div[@id='order_confirmation-wrapper']//td[contains(text(),'" + nameDuck + "')]"));
            Thread.sleep(2000); //стыдно за слип, но не придумал варианта иного, так как может быть одна утка с двумя позициями и исчезновение элемента не подойдет
        }
    }

    boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    boolean isElementNotPresent(By locator) {
        return driver.findElements(locator).size() == 0;
    }
}

