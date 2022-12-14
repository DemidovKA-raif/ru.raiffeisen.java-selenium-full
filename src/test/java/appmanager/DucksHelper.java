package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DucksHelper extends HelperBase{

    public DucksHelper(WebDriver driver) {
        super(driver);
    }

    public void ducksTest() {
        WebElement campaigns = driver.findElement(By.xpath("//*[@id='box-campaigns']//*[contains(@class, 'name')]"));
        String price = driver.findElement(By.xpath("//*[@id='box-campaigns']//*[contains(@class, 'price-wrapper')]")).getText();
        WebElement regularPrice = driver.findElement(By.xpath("//*[@id='box-campaigns']//*[contains(@class, 'regular-price')]"));
        WebElement campaignPrice = driver.findElement(By.xpath("//*[@id='box-campaigns']//*[contains(@class, 'campaign-price')]"));
        String normPrice = regularPrice.getText();
        String salePrice = campaignPrice.getText();
        String attributeForTable = campaigns.getAttribute("innerText");

        String regularPriceCssValue = regularPrice.getCssValue("font-size");
        String campaignPriceCssValue = campaignPrice.getCssValue("font-size");
        double removeNumeric4 = Double.parseDouble(removeNumeric(regularPriceCssValue));
        double removeNumeric3 = Double.parseDouble(removeNumeric(campaignPriceCssValue));
        equals(removeNumeric4 >removeNumeric3);

        double s = Double.parseDouble(removeNumeric(normPrice));
        double s1 = Double.parseDouble(removeNumeric(salePrice));

        String colorNormalPriceStartPage = regularPrice.getCssValue("color");
        parserFirst(colorNormalPriceStartPage); //отправляем полученный цвет в странном формате на разбивку и очистку

        String colorSalePriceStartPage = campaignPrice.getCssValue("color");
        parserSecond(colorSalePriceStartPage);//отправляем полученный цвет в странном формате на разбивку и очистку

        String styleTest1 = campaignPrice.getCssValue("font-weight");
        int styleTest1Int = Integer.parseInt(styleTest1);
        assertTrue(styleTest1Int >= 700);//хардкод, но не понимаю, зачем это вывносить куда-либо

        String styleTest = regularPrice.getCssValue("text-decoration");
        assertTrue(styleTest.contains("line-through"));//хардкод, но не понимаю, зачем это вывносить куда-либо

        equals(s > s1);
        campaigns.click();

        String attributeForPage = driver.findElement(By.xpath("//h1[@class='title']")).getAttribute("textContent");
        String priceForPage = driver.findElement(By.xpath("//div[@itemprop='offers']")).getText();
        WebElement normalPricePage = driver.findElement(By.xpath("//s[@class='regular-price']"));
        String normalPricePageText = normalPricePage.getText();
        WebElement salePricePage = driver.findElement(By.xpath("//strong[@class='campaign-price']"));
        String salePricePageText = salePricePage.getText();

        String normalPricePageCssValue = normalPricePage.getCssValue("color");
        parserFirst(normalPricePageCssValue);

        String salePricePageCssValue = salePricePage.getCssValue("color");
        parserSecond(salePricePageCssValue);

        String styleTestPage1 = salePricePage.getCssValue("font-weight");
        int styleTestPageInt = Integer.parseInt(styleTestPage1);
        assertTrue(styleTestPageInt >= 700);//хардкод, но не понимаю, зачем это выносить куда-либо

        String styleTestPage2 = normalPricePage.getCssValue("text-decoration");
        assertTrue(styleTestPage2.contains("line-through"));//хардкод, но не понимаю, зачем это выносить куда-либо

        String sizeTitleNormalPricePage = normalPricePage.getCssValue("font-size");
        String sizeTitleSalePricePage = salePricePage.getCssValue("font-size");

        double removeNumeric1 = Double.parseDouble(removeNumeric(sizeTitleNormalPricePage));
        double removeNumeric2 = Double.parseDouble(removeNumeric(sizeTitleSalePricePage));
        equals(removeNumeric2 > removeNumeric1);


        double q = Double.parseDouble(removeNumeric(normalPricePageText));
        double q1 = Double.parseDouble(removeNumeric(salePricePageText));

        equals(q > q1);
        assertEquals(attributeForTable, attributeForPage);
        assertEquals(price, priceForPage);
    }

    public void parserSecond(String colorSalePriceStartPage) {
        String[] parts = colorSalePriceStartPage.split(" ");
        String res1 = parts[1];
        String res2 = parts[2];
        String s1 = removeNumeric(res1);
        String s2 = removeNumeric(res2);
        assertEquals("0", s1, s2);

    }

    public void parserFirst(String colorNormalPriceStartPage) {
        String[] parts = colorNormalPriceStartPage.split(" ");
        String res0 = parts[0];
        String res1 = parts[1];
        String res2 = parts[2];
        String s = removeNumeric(res0);
        String s1 = removeNumeric(res1);
        String s2 = removeNumeric(res2);
        assertEquals(s, s1, s2);
    }

    public static String removeNumeric(String str) {
        return str.replaceAll("[^\\d.]", "");
    }



    public void stickersAssert() {
        for (int i = 0; i < 11; i++) {
            WebElement ducks = driver.findElement(By.xpath("//a[@class='link' and contains(@title, 'Duck')]"));
            int sizeDuckStickers =
                    ducks.findElements(By.xpath(".//*[contains(@class, 'sticker')]")).size();
            System.out.println(sizeDuckStickers);
            assertEquals(sizeDuckStickers, 1);
        }
    }
}
