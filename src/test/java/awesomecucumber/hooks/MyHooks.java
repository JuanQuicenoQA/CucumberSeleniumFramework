package awesomecucumber.hooks;

import awesomecucumber.factory.DriverFactory;
import awesomecucumber.helper.GenericFunctions;
import awesomecucumber.runners.MyRunnerTest;
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


    @Before
    public void before(Scenario scenario){
        driver = DriverFactory.initializeDriver(System.getProperty("browser", MyRunnerTest.browserName));
        scenario.log("*** " + MyRunnerTest.browserName + " Browser ***" + "\n");
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
