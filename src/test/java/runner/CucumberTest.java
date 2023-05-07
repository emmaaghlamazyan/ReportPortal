package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = "pretty", monochrome = true, tags = "@smoke", glue = "steps", features = "src/test/resources/features")
public class CucumberTest extends AbstractTestNGCucumberTests {}