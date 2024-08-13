package org.tsrnew.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/org/tsrnew/features",
        glue = {"org.tsrnew.steps", "org.tsrnew.hooks"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"}
)
public class Runner {
}
