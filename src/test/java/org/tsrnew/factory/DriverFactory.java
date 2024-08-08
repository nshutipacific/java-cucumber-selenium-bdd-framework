package org.tsrnew.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public static WebDriver initializeDriver(){
        WebDriver driver;

        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        return driver;
    }
}
