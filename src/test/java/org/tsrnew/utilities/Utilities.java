package org.tsrnew.utilities;

import org.openqa.selenium.WebElement;

import java.util.List;

public class Utilities {
    public boolean isWithInPriceRange(List<WebElement> pricesList) {
        for (WebElement price : pricesList) {
            int freshPrice = Integer.parseInt(price.getText().replace("$", "").replace(",", "").replace(".00", ""));
            // Assert the price is within the expected range
            return freshPrice >= 30 && freshPrice <= 90;
        }
        return false;
    }
}
