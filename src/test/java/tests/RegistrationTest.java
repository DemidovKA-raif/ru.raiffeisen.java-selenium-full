package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class RegistrationTest extends TestBase {


    @Test
    public void registrationTest() throws InterruptedException {
        app.registrationHelper().fieldFormRegistrationTest();
    }

}
