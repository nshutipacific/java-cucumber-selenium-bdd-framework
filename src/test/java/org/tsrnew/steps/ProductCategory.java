package org.tsrnew.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.tsrnew.hooks.Hooks.driver;

public class ProductCategory {
    @When("the user selects {string} from the dropdown")
    public void the_user_selects_category_from_the_dropdown(String category) {
        WebElement dropdown = driver.findElement(By.id("product_cat")); // Replace with the actual ID of the dropdown element
        dropdown.click(); // Open the dropdown

        List<WebElement> options = dropdown.findElements(By.tagName("option")); // Get all options from the dropdown
        for (WebElement option : options) {
            if (option.getText().equals(category)) {
                System.out.println("Selecting category: " + category);
                option.click();

                String url = driver.getCurrentUrl();
                if (url.contains("product-category=" + category.toLowerCase().replace(" ", "-"))) {
                    System.out.println("URL contains category: " + category);
                    //check url if contains category
                    Assert.assertEquals();

                } else {
                    throw new AssertionError("URL does not contain category: " + category);
                }
                break;
            }
        }
    }

    @Then("the product listing should display only products from the {string} category")
    public void the_product_listing_should_display_only_products_from_the_category(String category) {
        List<WebElement> productElements = driver.findElements(By.cssSelector(".product-item")); // Replace with the actual CSS selector for product items

        List<String> productCategories = productElements.stream()
                .map(element -> element.findElement(By.cssSelector(".product-category")).getText()) // Replace with the actual CSS selector for product category
                .toList();

        boolean allProductsMatchCategory = productCategories.stream().allMatch(cat -> cat.equals(category));

        if (!allProductsMatchCategory) {
            throw new AssertionError("Not all products are from the selected category: " + category);
        }
    }
}
