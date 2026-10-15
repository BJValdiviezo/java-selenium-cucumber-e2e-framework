package com.crowdar;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.crowdar.steps",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber-pretty.html",
        "json:target/cucumber-reports/CucumberTestReport.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // <--- Plugin fundamental para que Allure capture los datos
    }
)
public class TestRunner extends AbstractTestNGCucumberTests {
}