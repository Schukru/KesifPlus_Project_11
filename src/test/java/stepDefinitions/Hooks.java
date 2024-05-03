package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import pages.CommonPage;
import utility.ConfigurationReader;
import utility.DB.DatabaseUtilities;
import utility.Driver;
import utility.UI.Utilities;

public class Hooks {

    public static WebDriver driver;
    public static boolean isHeadless = false;
    public static String browserType = "chrome";

    public static boolean isFullScreen = true;
    public static int width;
    public static int height;

    public static CommonPage commonPage;
    public static Actions actions;


    @Before(value = "@headless", order = 0)
    public void setIsHeadless() {
        isHeadless = true;
    }

    @Before(value = "@firefox", order = 0)
    public void setIsFirefox() {
        browserType = "firefox";
    }


    @Before(value = "@iPhone12", order = 0)
    public void setiPhone12() {
        isFullScreen = false;
        width = 390;
        height = 844;
    }

    @Before(order = 1, value = "@UI")
    public void setup() {

        driver = Driver.getDriver();
        commonPage = new CommonPage() {
        };
        actions = new Actions(driver);

        driver.get(ConfigurationReader.getProperty("urlTest"));
        Utilities.waitForPageToLoad(15);
    }

    @After(value = "@UI")
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshots");
        }
       // Driver.closeDriver();
//        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
//        localStorage.clear();
//        driver.navigate().refresh();
    }


    @Before("@user1")
    public void denemeLogin() {
        System.out.println(
                "email : " + ConfigurationReader.getProperty("user1_email") +
                        " password : " + ConfigurationReader.getProperty("user1_password")
        );
    }

    @Before("@DB")
    public void setupDatabase() {
        DatabaseUtilities.createMYSQLConnection();
    }

    @After("@DB")
    public void closeDatabase() {
        DatabaseUtilities.closeConnection();

    }

}