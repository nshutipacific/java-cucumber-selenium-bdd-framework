package org.tsrnew.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tsrnew.hooks.Hooks;
import org.tsrnew.utilities.Utilities;
import org.tsrnew.pages.StorePage;



import static org.junit.Assert.assertTrue;

public class PriceRangeFilter extends Utilities {
    @Given("a user is on the product page")
    public void a_user_is_on_the_product_page() {
        StorePage.loadStorePage();
    }

    @When("a user filter withing price range using range input")
    public void aUserFilterWithingPriceRangeUsingRangeInput() {
        StorePage.setMinPrice(30);
        StorePage.setMaxPrice(90);
    }

    @And("user click on filter button")
    public void userClickOnFilterButton() {
        StorePage.clickFilterButton();
    }

    @Then("user should see products within the price range")
    public void userShouldSeeProductsWithinThePriceRange() {
        Assert.assertEquals("The URL does not contain the specified prices", StorePage.getCurrentUrl(), "https://askomdch.com/store/?min_price=30&max_price=90");

        assertTrue("Products are not within 30-90 Price ranges", StorePage.isWithInPriceRange());

    }
}