package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;


public class ApplicationManager {

    private final Properties properties;
    WebDriver driver;
    private String browser;
    private Helpers helpers;


    /**
     * @param browser - передача параметра Браузера в конструкторе
     */
    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
        if (Objects.equals(browser, BrowserType.CHROME)) {
            driver = new ChromeDriver(capabilities);
        } else {
            driver = new FirefoxDriver(capabilities);
        }

        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        driver.get(properties.getProperty("web.BaseURL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        helpers = new Helpers(driver);
    }

    public void stop() {
        driver.quit();
    }

    public Helpers helpers() {
        return helpers;
    }


}
