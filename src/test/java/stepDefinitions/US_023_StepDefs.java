package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.CommonPage;
import utility.ConfigurationReader;

public class US_023_StepDefs extends CommonPage {
    @Given("User clicks login with google button")
    public void userClicksLoginWithGoogleButton() {
         getHomePage().clickGoogleLoginButton();
    }

    @And("User account information should be entered")
    public void userAccountInformationShouldBeEntered() {
        getHomePage().inputGmailAccount(ConfigurationReader.getProperty("google_user_email"),
                                        ConfigurationReader.getProperty("google_user_password"));
    }

    @Then("Verify that the project page can be accessible")
    public void verifyThatTheProjectPageCanBeAccessible() {
        getHomePage().verifyProjectPage();
    }
}
