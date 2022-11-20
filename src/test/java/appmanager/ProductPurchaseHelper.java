package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ProductPurchaseHelper extends HelperBase {


    public ProductPurchaseHelper(WebDriver driver) {
        super(driver);
    }

    public void addProduct(int quantity) {
        WebDriverWait wait = new WebDriverWait(driver, 2/*seconds*/);
        for (int i = 1; i < quantity; i++) {
            productSelection(wait);
            if (isElementPresent(By.xpath("//select"))) { //если попадается желтая утра, то у нее обязательный Селект всплывает, не стал обрабатывать его
                i--;
                wait.until(visibilityOf(driver.findElement(By.xpath("//img[@alt='My Store']")))).click();
                wait.until(visibilityOf(driver.findElement(By.xpath("//div[@class='image-wrapper']")))).click();
            }
            wait.until(visibilityOf(driver.findElement(By.name("add_cart_product")))).click();
            wait.until(visibilityOf(driver.findElement(By.xpath("//span[@class='quantity' and contains(text(), '" + i + "')]"))));
            click(By.xpath("//img[@alt='My Store']"));
        }
    }

    public void workToBasket(int a) {
        WebDriverWait wait = new WebDriverWait(driver, 2/*seconds*/);
        click(By.xpath("//a[contains(text(),'Checkout »')]"));
        for (int i = 1; i < a; i++) {
            wait.until(visibilityOf(driver.findElement(By.name("quantity")))).click();
            wait.until(visibilityOf(driver.findElement(By.name("quantity")))).sendKeys(Keys.DOWN);
            String nameDuck = driver.findElement(By.xpath("//form[@name='cart_form']//strong")).getText();

            isElementPresent(By.xpath("//div[@id='order_confirmation-wrapper']//td[contains(text(),'" + nameDuck + "')]"));
            wait.until(visibilityOf(driver.findElement(By.name("update_cart_item")))).click();
            isElementNotPresent(By.xpath("//div[@id='order_confirmation-wrapper']//td[contains(text(),'" + nameDuck + "')]"));

            WebElement webElement = driver.findElement(By.xpath("//table[@class='dataTable rounded-corners']"));
            List<WebElement> webElements = webElement.findElements(By.xpath("//div[@id='order_confirmation-wrapper']/table/tbody/tr[2]"));
            wait.until(stalenessOf(webElements.get(0)));
        }
    }

    public void productSelection(WebDriverWait wait) {
        wait.until(visibilityOf(driver.findElement(By.xpath("//div[@class='image-wrapper']")))).click();
    }

    boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    boolean isElementNotPresent(By locator) {
        return driver.findElements(locator).size() == 0;
    }
}
