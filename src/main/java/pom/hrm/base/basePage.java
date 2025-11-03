package pom.hrm.base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import pom.hrm.utils.ConfigReader;

public class basePage {

    protected WebDriver driver; // accessible in subclasses
    protected ExtentReports extent;
	protected ExtentTest test;
	
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
    @BeforeSuite
    public void setupReport() {
		/* What happens here:
		 * ExtentSparkReporter → creates an HTML report file (ExtentReport.html).
		
		 * ExtentReports → collects all logs and test results.
		 
		 * attachReporter() → links the reporter to the reporting engine.
		
		 * When you run your tests, ExtentReports writes everything (logs, pass/fail
		 * results, etc.) into the Spark HTML file.
		 */

    	//Create the object to extent report using spark
    	String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
    	ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
    	//Set the Browser tab title
    	spark.config().setDocumentTitle("Extended_OrangeHRM_Report");
    	//Set the HTML report title
    	spark.config().setReportName("Selenium_Practice_Report");
    	
    	//Create the main Extent Report Object
    	extent = new ExtentReports();
    	//Links it with the "Spark" reporter
    	extent.attachReporter(spark);
    }
    @AfterSuite
    public void tearDownReport() {
        extent.flush();  // Writes report to disk
    }
}
