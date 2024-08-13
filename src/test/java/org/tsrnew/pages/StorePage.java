package org.tsrnew.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.tsrnew.utilities.Utilities;
import static org.tsrnew.hooks.Hooks.driver;

public class StorePage extends Utilities {

    public static void loadStorePage() {
        driver.get("https://askomdch.com/store/");
    }

    public static void setMinPrice(Integer minPrice) {
        ((JavascriptExecutor) driver).executeScript("document.getElementById('min_price').style.display='block';");
        WebElement minPriceElement = driver.findElement(By.id("min_price"));

        minPriceElement.clear();
        minPriceElement.sendKeys(minPrice.toString());
    }

    public static void setMaxPrice(Integer maxPrice) {
        ((JavascriptExecutor) driver).executeScript("document.getElementById('max_price').style.display='block';");
        WebElement maxPriceElement = driver.findElement(By.id("max_price"));
        //WebElement userButton = driver.findElement(By.xpath('//'));


        maxPriceElement.clear();
        maxPriceElement.sendKeys(maxPrice.toString());
    }

    public static void clickFilterButton() {
        WebElement filterButton = driver.findElement(By.xpath("//button[@class='button']"));
        filterButton.click();
    }

    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public static boolean isWithInPriceRange() {
        List<WebElement> discounted_prices_list = driver.findElements(By.cssSelector("main div.ast-woocommerce-container ul.products li.product span.price ins span.woocommerce-Price-amount bdi"));
        List<WebElement> normal_prices_list = driver.findElements(By.cssSelector("main div.ast-woocommerce-container ul.products li.product span.price:not(:has(del)) span.woocommerce-Price-amount bdi"));

        return isItInPriceRange(discounted_prices_list);

    }
}
