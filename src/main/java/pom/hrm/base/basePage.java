package pom.hrm.base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pom.hrm.utils.ConfigReader;

public class basePage {

    protected WebDriver driver; // accessible in subclasses

    public WebDriver initializeBrowser(String browser) {
        ConfigReader.initProperties();
        String br = (browser != null && !browser.isEmpty() ? browser : ConfigReader.getProperty("browser"));
        if (br == null) throw new IllegalArgumentException("Browser is not specified");
        br = br.toLowerCase();

        switch (br) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + br);
        }

        driver.manage().window().maximize();
        try {
            int timeout = 10;
            try { timeout = ConfigReader.getIntProperty("timeout"); } catch (Exception ignored) {}
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        } catch (Exception ignored) {}

        String url = ConfigReader.getProperty("url");
        if (url != null && !url.isEmpty()) driver.get(url);
        return driver;
    }

    public WebDriver getDriver() { return driver; }

    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
