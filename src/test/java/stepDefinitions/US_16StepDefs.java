package stepDefinitions;

import io.cucumber.java.en.*;
import pages.CommonPage;
import utility.ConfigurationReader;

import static utility.UI.Utilities.waitFor;

public class US_16StepDefs extends CommonPage {

    @Given("user clicks on Giriş Yap")
    public void user_clicks_on_giriş_yap() throws InterruptedException {

        Thread.sleep(5000);

        getProfilePage().girisYapBtn.click();

    }
    @When("user clicks on E-postanızla kaydolun")
    public void user_clicks_on_e_postanızla_kaydolun() throws InterruptedException {
        Thread.sleep(3000);
        getProfilePage().emailIleGırısYapBtn.click();
    }
    @Then("user verifies Kaydolun header is seen")
    public void user_verifies_kaydolun_header_is_seen() {
        getProfilePage().header.isDisplayed();
    }
    @Then("user enters E-posta and Şifre")
    public void user_enters_e_posta_and_şifre() throws InterruptedException {

        getProfilePage().emailInputBox.sendKeys(ConfigurationReader.getProperty("userRgstr_email"));
        Thread.sleep(2000);
        getProfilePage().sifreInputBox.sendKeys(ConfigurationReader.getProperty("user1_password"));
        Thread.sleep(3000);
    }
    @Then("user clicks on Kaydolun button")
    public void user_clicks_on_kaydolun_button() throws InterruptedException {
     Thread.sleep(10000);
     getProfilePage().kaydolunBtn.click();
    }
    @Then("user clicks on Profile button")
    public void user_clicks_on_profile_button() {
    waitFor(3000);
    getProfilePage().profileIcon.click();
    }
    @Then("user should be navigated to Profile page")
    public void user_should_be_navigated_to_profile_page() {

    }
    @Then("user clicks on Profile image")
    public void user_clicks_on_profile_image() {

    }
    @Then("user uploads profile image")
    public void user_uploads_profile_image() {

    }
    @Then("user should see profile image is loaded")
    public void user_should_see_profile_image_is_loaded() {

    }
    @Then("user changes profile image")
    public void user_changes_profile_image() {

    }
    @Then("user should see pop-up message Profil Guncellendi")
    public void user_should_see_pop_up_message_profil_guncellendi() {

    }
    @Then("user should type in Hakkımızda section")
    public void user_should_type_in_hakkımızda_section() {

    }
    @Then("user should see the text Testers test")
    public void user_should_see_the_text_testers_test() {

    }
    @Then("user should see pop-up message Profil güncellendi")
    public void user_should_see_pop_up_message_profil_güncellendi() {

    }

}
