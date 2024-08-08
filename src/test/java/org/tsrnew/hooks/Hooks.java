package org.tsrnew.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.tsrnew.factory.DriverFactory;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setup() {
        driver = DriverFactory.initializeDriver();
    }

    @After
    public void tearDown() {
            driver.quit();
    }
}