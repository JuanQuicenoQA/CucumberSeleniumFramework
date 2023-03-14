package awesomecucumber.hooks;

import awesomecucumber.factory.DriverFactory;
import awesomecucumber.helper.GenericFunctions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class MyHooks extends GenericFunctions {
    private static WebDriver driver;
    Random random = new Random();
    String[] availableBrowsers = {"Chrome", "Edge", "Firefox"};
    String strRandomBrowser = availableBrowsers[random.nextInt(availableBrowsers.length)];

    @Before
    public void before(Scenario scenario){
        driver = DriverFactory.initializeDriver(System.getProperty("browser", strRandomBrowser));
        scenario.log("*** " + strRandomBrowser + " Browser ***" + "\n");
    }

    @After(order=1) //Cucumber hook - runs for each scenario
    public static void quitDriver() throws Exception {
        driver.quit();
    }

    @After(order = 2) // Cucumber After Hook with order 1
    public void takeScreenShotOnFailedScenario(@NotNull Scenario scenario) throws Exception {
        if ((scenario.isFailed())) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed scenario screenshot");
        }
    }
}
