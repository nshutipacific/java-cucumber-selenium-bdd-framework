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

import java.util.List;

import static org.junit.Assert.assertTrue;

public class PriceRangeFilter extends Utilities {
    private final WebDriver driver;

    public PriceRangeFilter() {
        this.driver = Hooks.driver; // Get the driver from Hooks
    }

    @Given("a user is on the product page")
    public void a_user_is_on_the_product_page() {
        driver.get("https://askomdch.com/store/");

        String expectedTitle = "Products – AskOmDch";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @When("a user filter withing price range using range input")
    public void aUserFilterWithingPriceRangeUsingRangeInput() {
        ((JavascriptExecutor) driver).executeScript("document.getElementById('min_price').style.display='block';");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('max_price').style.display='block';");

        WebElement minPrice = driver.findElement(By.id("min_price"));
        WebElement maxPrice = driver.findElement(By.id("max_price"));

        minPrice.clear();
        maxPrice.clear();

        minPrice.sendKeys("30");
        maxPrice.sendKeys( "90");
    }

    @And("user click on filter button")
    public void userClickOnFilterButton() {
        WebElement filterButton = driver.findElement(By.xpath("//button[@class='button']"));
        filterButton.click();
    }

    @Then("user should see products within the price range")
    public void userShouldSeeProductsWithinThePriceRange() {
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("min_price=30&max_price=90"));

        List<WebElement> discounted_prices_list = driver.findElements(By.cssSelector("main div.ast-woocommerce-container ul.products li.product span.price ins span.woocommerce-Price-amount bdi"));
        List<WebElement> normal_prices_list = driver.findElements(By.cssSelector("main div.ast-woocommerce-container ul.products li.product span.price:not(:has(del)) span.woocommerce-Price-amount bdi"));

        assertTrue(isWithInPriceRange(discounted_prices_list));
        assertTrue(isWithInPriceRange(normal_prices_list));
    }
}