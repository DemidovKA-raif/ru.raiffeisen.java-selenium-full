package testsAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ClickMenuTest extends BaseTest {

    @Test
    public void testFirst() throws InterruptedException {
        app.helpers().clickMenu();
    }


}