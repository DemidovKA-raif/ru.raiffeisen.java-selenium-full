package tests;

import org.testng.annotations.Test;

public class DucksTest extends TestBase {

    @Test
    public void testDuckStickers() {
        app.ducksHelper().stickersAssert();
    }


    @Test
    public void testDuckName() {
        app.ducksHelper().ducksTest();
    }
}

