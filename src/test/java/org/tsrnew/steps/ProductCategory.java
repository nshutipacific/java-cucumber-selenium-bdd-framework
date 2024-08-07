package org.tsrnew.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;
import static org.tsrnew.hooks.Hooks.driver;

public class ProductCategory {
    @When("the user clicks on the category dropdown")
    public void theUserClicksOnTheCategoryDropdown() {
        WebElement dropdown = driver.findElement(By.id("product_cat"));
        dropdown.click(); // Open the dropdown
        
    }
    
    @When("the user selects {string} from the dropdown")
    public void the_user_selects_category_from_the_dropdown(String category) {
        //Executing the second step

        List<WebElement> options = driver.findElements(By.tagName("option")); // Get all options from the dropdown
        for (WebElement option : options) {


            if (Objects.equals(option.getText().replaceAll("\\s+\\(.*\\)", "").replace("’", "'").trim(), category)) {
                option.click();
                break;
            }
        }
    }

    @Then("the product listing should display only products from the {string} category")
    public void the_product_listing_should_display_only_products_from_the_category(String category) {
        //System.out.println("Executing the third step");
        String currentURL = driver.getCurrentUrl();
        //System.out.println(currentURL);

        String normalizedCategory = category.toLowerCase().replace(" ", "-").replace("'", "");
        //System.out.println(normalizedCategory);

        // Check if the URL contains the normalized category
        Assert.assertTrue("URL does not contain the expected category: " + normalizedCategory,
                currentURL.contains("product-category/" + normalizedCategory));

        // Check the category title
        String categoryTitle = driver.findElement(By.cssSelector("h1.woocommerce-products-header__title.page-title")).getText();
        Assert.assertEquals("Category title does not match the expected category: " + category, category, categoryTitle);

        // Check products' categories
        List<WebElement> products = driver.findElements(By.cssSelector("html > body > div > div:nth-of-type(1) > div > div:nth-of-type(2) > main > div > ul > li > div > span.ast-woo-product-category"));
        for (WebElement product : products) {
            System.out.println(product.getText().toLowerCase());
            assertTrue("Product of "+ product.getText() + "Category does not belong to the expected category: " + category,
                    category.toLowerCase().startsWith(product.getText().toLowerCase()));
        }
    }
}