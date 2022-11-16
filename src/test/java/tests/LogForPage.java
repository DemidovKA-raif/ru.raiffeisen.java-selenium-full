package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LogForPage extends BaseTestAdmin {


    @Test
    public void controlError() {
        WebDriverWait wait = new WebDriverWait(driver, 2/*seconds*/);

        click(By.xpath("//*/text()[normalize-space(.)='Catalog']/parent::*"));
        click(By.xpath("//a[contains(text(),'Rubber Ducks')]"));
        click(By.xpath("//a[contains(text(),'Subcategory')]"));
        String text = driver.findElement(By.cssSelector("tr.footer > td")).getText();
        String removeNumeric = removeNumeric(text);
        int substring = Integer.parseInt(removeNumeric.substring(removeNumeric.length() - 2));

        for (int i = 5; i < substring + 5; i++) {
            wait.until(visibilityOf(driver.findElement(By.xpath("//tr[" + i + "]/td[3]/a")))).click();
            String logEntries = String.valueOf(driver.manage().logs().get("browser").getAll());
            Assert.assertFalse(text.contains(logEntries));
            click(By.name("cancel"));
        }
    }

    public static String removeNumeric(String str) {
        return str.replaceAll("[^\\d.]", "");
    }
}
