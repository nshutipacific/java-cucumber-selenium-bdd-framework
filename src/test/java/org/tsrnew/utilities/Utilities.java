package org.tsrnew.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.tsrnew.hooks.Hooks.driver;

public class Utilities {
    public static boolean isItInPriceRange(List<WebElement> pricesList) {
        for (WebElement price : pricesList) {
            int freshPrice = Integer.parseInt(price.getText().replace("$", "").replace(",", "").replace(".00", ""));
            // Assert the price is within the expected range
            return freshPrice >= 30 && freshPrice <= 90;
        }
        return false;
    }
}
