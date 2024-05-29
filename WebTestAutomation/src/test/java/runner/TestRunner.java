package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import org.junit.Test;
//import org.junit.runner.RunWith;

//import static org.junit.Assert.assertEquals;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.testng.annotations.DataProvider;

//@RunWith(Cucumber.class)
    @CucumberOptions(features = "src/test/resources/features",
            glue = { "stepDefinition" },
            plugin = { "pretty","html:target/cucumber-html-report","json:cucumber.json" },
            monochrome = true,
            publish = true
    )

    public class TestRunner extends AbstractTestNGCucumberTests {

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
            return super.scenarios();
        }
    }
