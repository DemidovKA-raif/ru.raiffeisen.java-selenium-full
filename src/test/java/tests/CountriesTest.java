package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.testng.Assert.assertEquals;

public class CountriesTest extends BaseTestAdmin {


    @Test
    public void countriesTest() {
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

//                System.out.println(listAfterForZones);
//                System.out.println(listZones);

                assertEquals(listAfterForZones, listZones);
                listZones.clear();
                listAfterForZones.clear();
            }
        }
        ArrayList<String> listAfter = new ArrayList<>(listCountries);
        Collections.sort(listAfter);
        assertEquals(listCountries, listAfter);
    }


    @Test
    public void sortGeoZoneTest() {
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
}
