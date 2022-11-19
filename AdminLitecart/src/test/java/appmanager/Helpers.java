package appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class Helpers extends HelperBase {

    public Helpers(WebDriver driver) {
        super(driver);
    }

    public void fieldFormProduct() throws InterruptedException {
        String time = time();
        generalPage(time);
        informationPage();
        pricesPage();

        WebElement elementForTable = driver.findElement(By.xpath("//a[contains(text(),'" + time + "')]"));
        Assert.assertTrue(elementForTable.isDisplayed());
    }

    public void pricesPage() throws InterruptedException {
        click(By.xpath("//a[contains(text(),'Prices')]"));
        WebElement purchasePrice = driver.findElement(By.name("purchase_price"));
        for (int i = 0; i < 10; i++) {
            purchasePrice.sendKeys(Keys.UP);
        }
        WebElement currencyCode = driver.findElement(By.name("purchase_price_currency_code"));
        scriptExecutor("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", currencyCode);
        WebElement taxClass = driver.findElement(By.name("tax_class_id"));
        scriptExecutor("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", taxClass);
        sendKeys("prices[USD]", "10");
        sendKeys("prices[EUR]", "10");
        click(By.name("save"));
    }


    public void informationPage() throws InterruptedException {
        click(By.xpath("//a[contains(text(),'Information')]"));
        Thread.sleep(1000); //выполняется и без слипов, сделал из-за предупреждения в задании

        WebElement manufacturer = driver.findElement(By.name("manufacturer_id"));
        manufacturer.click();
        scriptExecutor("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", manufacturer);
        WebElement supplier = driver.findElement(By.name("supplier_id"));
        supplier.click();
        scriptExecutor("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", supplier);
        sendKeys("keywords", "fatduck");
        sendKeys("short_description[en]", "by duck, don t ...");
        WebElement description = driver.findElement(By.cssSelector("div.trumbowyg-editor"));
        description.sendKeys("duck1");
        sendKeys("head_title[en]", "duck2");
        sendKeys("meta_description[en]", "duck3");
    }


    public void generalPage(String value) throws InterruptedException {
        click(By.xpath("//span[contains(text(),'Catalog')]"));
        Thread.sleep(1000);

        click(By.xpath("//a[@class='button' and contains(text(),' Add New Product')]"));
        click(By.name("status"));
        WebElement name = driver.findElement(By.name("name[en]"));
        name.sendKeys(value);
        sendKeys("code", "1234567");
        click(By.xpath("//input[@data-name='Rubber Ducks']"));
        click(By.xpath("//input[@data-name='Subcategory']"));
        WebElement category_id = driver.findElement(By.name("default_category_id"));
        scriptExecutor("arguments[0].selectedIndex = 2; arguments[0].dispatchEvent(new Event('change'))", category_id);
        String productGroups = "1-3";
        click(By.xpath("//input[@name='product_groups[]' and (@value='" + productGroups + "')]"));
        sendKeys("quantity", "100");
        WebElement calendar = driver.findElement(By.name("date_valid_from"));
        sendKeys("date_valid_from", "20.08.2021");
        sendKeys("date_valid_to", "20.08.2023");

        WebElement newFile = driver.findElement(By.name("new_images[]"));
        newFile.sendKeys(new File("src/test/resources/duck.jpeg").getAbsolutePath());

    }

    public void clickMenu() {
        for (int i = 1; i < 17; i++) {
            WebElement elementMenuHead = driver.findElement(By.xpath("//li[@id='app-'][" + i + "]"));
            elementMenuHead.click();
            int hStyle = driver.findElements(By.xpath("//h1")).size();
            Assert.assertEquals(hStyle, 1);
            for (int s = 1; s < 10; s++) {
                boolean elementPresent = isElementPresent(By.xpath(".//*[contains(@id, 'doc')][" + s + "]"));
                if (!elementPresent) {
                    break;
                }
                WebElement li = driver.findElement(By.xpath(".//*[contains(@id, 'doc')][" + s + "]"));
                li.click();
                int hStyleSubtitle = driver.findElements(By.xpath("//h1")).size();
                Assert.assertEquals(hStyleSubtitle, 1);
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

    public void countries() {
        ArrayList<String> listCountries = new ArrayList<>();
        ArrayList<String> listZones = new ArrayList<>();
        click(By.xpath("//*/text()[normalize-space(.)='Countries']/parent::*"));
        int sizeTable = Integer.parseInt(driver.findElement(By.name("countries_form")).getAttribute("length"));
        for (int i = 2; i < sizeTable - 1; i++) {
            WebElement nameCountries = driver.findElement(By.xpath("//tr[" + i + "]/td[5]/a"));
            String nameCountriesText = nameCountries.getText();
            listCountries.add(nameCountriesText);
            String zones = driver.findElement(By.xpath("//tr[" + i + "]/td[6]")).getText();
            int textZonerInt = Integer.parseInt(String.valueOf(zones));
            if (textZonerInt > 0) {
                nameCountries.click();
                int size = driver.findElements(By.xpath("//td[3]")).size();
                for (int j = 2; j < size; j++) {
                    WebElement nameCountriesFotZones = driver.findElement(By.xpath("//tr[" + j + "]/td[3]"));
                    String fotZonesText = nameCountriesFotZones.getText();
                    listZones.add(fotZonesText);
                }
                click(By.xpath("//button[@name='cancel']"));

                ArrayList<String> listAfterForZones = new ArrayList<>(listZones); //создаем дубликат списка
                Collections.sort(listAfterForZones); //сортируем дубликат полученного списка

                Assert.assertEquals(listAfterForZones, listZones);
                listZones.clear();
                listAfterForZones.clear();
            }
        }
        ArrayList<String> listAfter = new ArrayList<>(listCountries);
        Collections.sort(listAfter);
        Assert.assertEquals(listCountries, listAfter);
    }


    public void sortZones() {
        WebElement goHome = driver.findElement(By.xpath("//*/text()[normalize-space(.)='Geo Zones']/parent::*"));
        goHome.click();
        int maxSizeCountries = driver.findElements(By.xpath("//tr[@class='row']")).size();
        for (int i = 1; i <= maxSizeCountries; i++) {
            String country = driver.findElement(By.xpath("//tr[@class='row'][" + i + "]/td/a")).getText();
            System.out.println(country);
            click(By.xpath("//a[contains(text(),'" + country + "')]"));
            WebElement table = driver.findElement(By.id("table-zones"));
            int zoneList = table.findElements(By.xpath(".//*[contains(@name, 'zone_code')]")).size();
            ArrayList<String> list = new ArrayList<>();

            for (int s = 2; s < zoneList + 2; s++) {
                WebElement getID = driver.findElement(By.xpath("//table[2]/tbody/tr[" + s + "]/td[1]"));
                int valueID = Integer.parseInt(getID.getText());
                WebElement text = driver.findElement(By.xpath(".//*[contains(@name, 'zones[" + valueID + "][zone_code]')]/option[@selected] "));
                String attribute = text.getText();
                list.add(attribute);
            }

            ArrayList<String> listAfterForZones = list; //создаем дубликат списка
            Collections.sort(listAfterForZones);
            list.equals(listAfterForZones);
            click(By.xpath("//button[@name='cancel']"));
            System.out.println(listAfterForZones);
            System.out.println("__________");
            System.out.println(list);
        }
    }

    public void errorTest() {
        WebDriverWait wait = new WebDriverWait(driver, 2/*seconds*/);

        click(By.xpath("//*/text()[normalize-space(.)='Catalog']/parent::*"));
        click(By.xpath("//a[contains(text(),'Rubber Ducks')]"));
        click(By.xpath("//a[contains(text(),'Subcategory')]"));
        String text = driver.findElement(By.cssSelector("tr.footer > td")).getText();
        String removeNumeric = removeNumeric(text);
        int substring = Integer.parseInt(removeNumeric.substring(removeNumeric.length() - 2));

        for (int i = 5; i < substring + 5; i++) {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tr[" + i + "]/td[3]/a")))).click();
            String logEntries = String.valueOf(driver.manage().logs().get("browser").getAll());

            Assert.assertFalse(text.contains(logEntries));
            click(By.name("cancel"));
        }
    }

    public static String removeNumeric(String str) {
        return str.replaceAll("[^\\d.]", "");
    }


    public void windowsOpen() {
        WebDriverWait wait = new WebDriverWait(driver, 2/*seconds*/);

        click(By.xpath("//*/text()[normalize-space(.)='Countries']/parent::*"));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement((By.xpath("//a[contains(text(),'Add New Country')]"))))).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@value='1']")))).click();
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

    public void windowSet(WebDriverWait wait, String xpathExpression) {
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
