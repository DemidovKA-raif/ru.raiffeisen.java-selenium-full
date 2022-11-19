package testsAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogForPage extends BaseTest {


    @Test
    public void controlError() {
        app.helpers().errorTest();
    }
}





