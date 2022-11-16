package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class NewWindowTest extends BaseTestAdmin {


    @Test
    public void openNewWindow() {

        WebDriverWait wait = new WebDriverWait(driver, 2/*seconds*/);

        click(By.xpath("//*/text()[normalize-space(.)='Countries']/parent::*"));
        wait.until(visibilityOf(driver.findElement((By.xpath("//a[contains(text(),'Add New Country')]"))))).click();
        wait.until(visibilityOf(driver.findElement(By.xpath("//input[@value='1']")))).click();
        sendKeys("iso_code_2", "PF");
        windowSet(wait, "//td/a[1]");

        sendKeys("iso_code_3", "PFF");

        sendKeys("name", "Pflyandeya");

        sendKeys("tax_id_format", "Test");
        windowSet(wait, "//tr[6]/td/a/i");

        sendKeys("address_format",
                "%company " +
                        "%firstname %lastname " +
                        "%address1 " +
                        "%address2 " +
                        "%postcode %city " +
                        "%zone_name " +
                        "%country_name ");
        windowSet(wait, "//td/a[2]");

        sendKeys("postcode_format", "Test2");
        windowSet(wait, "//tr[8]/td/a/i");

        sendKeys("currency_code", "PFFF");
        windowSet(wait, "//tr[9]/td/a/i");

        sendKeys("phone_code", "69");
        windowSet(wait, "//tr[10]/td/a/i");

        click(By.name("save"));
    }

    private void windowSet(WebDriverWait wait, String xpathExpression) {
        String windowHandle = driver.getWindowHandle(); //запоминаем идентификатор текущего окна
        Set<String> windowHandles = driver.getWindowHandles();//запоминаем идентификаторы открытых окон
        click(By.xpath(xpathExpression));
        String newWindow = wait.until(anyWindowOtherThan(windowHandles)); // ожидаем появления нового окна
        driver.switchTo().window(newWindow); // переключаемся в новое окно
        driver.close();
        driver.switchTo().window(windowHandle); // переключаемся в первоначальное окно
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }
}

