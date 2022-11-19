package testsAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.testng.Assert.assertEquals;

public class CountriesTest extends BaseTest {


    @Test
    public void countriesTest() {
        app.helpers().countries();
    }



    @Test
    public void sortGeoZoneTest() {
        app.helpers().sortZones();
    }

}
