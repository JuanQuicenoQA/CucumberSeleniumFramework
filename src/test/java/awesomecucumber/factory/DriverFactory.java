package awesomecucumber.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static WebDriver driver;
    public static WebDriver initializeDriver(String browser){
        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            default -> throw new IllegalStateException("Invalid Browser:" + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
