package tests;

import org.testng.annotations.*;

    public class OpenBrowserTest extends BaseTest {

        @Test
        public void testAddRequest() throws Exception {
            sendKeys("admin", "username");
            sendKeys("admin", "password");
            }

    }

