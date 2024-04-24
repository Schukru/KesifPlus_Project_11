package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "html:target/reports/html/failed_cucumber-html_reports.html",
                "json:target/reports/json/failed_json-reports/cucumber.json",
                "junit:target/reports/xml/failed_xml-report/cucumber.xml"
        },
        features = "@target/reports/rerun.txt",
        glue = "stepDefinitions"
)

public class FailedRunner {
}