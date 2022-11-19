package appmanager;

import io.netty.channel.kqueue.AcceptFilter;
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

import static org.testng.Assert.fail;

public class ApplicationManager {
    private DucksHelper ducksHelper;
    private RegistrationHelper registrationHelper;
    private ProductPurchaseHelper productPurchaseHelper;

    private final Properties properties;
    WebDriver driver;
    private String browser;
    private AcceptFilter verificationErrors;

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
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
//        driver = new ChromeDriver(capabilities);
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        driver.get(properties.getProperty("web.BaseURL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.name("login")).click();

        productPurchaseHelper = new ProductPurchaseHelper(driver);
        registrationHelper = new RegistrationHelper(driver);
        ducksHelper = new DucksHelper(driver);
    }

    public void stop() {
        driver.quit();
    }

    public RegistrationHelper registrationHelper() {
        return registrationHelper;
    }

    public ProductPurchaseHelper productPurchaseHelper() {
        return productPurchaseHelper;
    }

    public DucksHelper ducksHelper() {
        return ducksHelper;
    }
}
