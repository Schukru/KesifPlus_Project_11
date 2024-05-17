package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.openqa.selenium.Keys;
import pages.CommonPage;
import pages.ProfilePage;
import utility.ConfigurationReader;
import utility.Driver;
import utility.UI.Utilities;

import java.lang.module.Configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stepDefinitions.Hooks.driver;


public class US_16StepDefs extends CommonPage {
    Faker fakeEmail= new Faker();
    Faker fakePwd= new Faker();
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

        getProfilePage().simulateHumanTyping(getProfilePage().emailInputBox, fakeEmail.internet().emailAddress());

        getProfilePage().simulateHumanTyping(getProfilePage().sifreInputBox, fakePwd.internet().password());




    }
    @Then("user clicks on Kaydolun button")
    public void user_clicks_on_kaydolun_button() throws InterruptedException {

        getProfilePage().kaydolunBtn.click();

    }
    @Then("user clicks on Profile button")
    public void user_clicks_on_profile_button() throws InterruptedException {

        getProfilePage().profileIcon.click();

        getProfilePage().profileLinkInDropdown.click();


    }
    @Then("user should be navigated to Profile page")
    public void user_should_be_navigated_to_profile_page() throws InterruptedException {

        Thread.sleep(3000);

        String currentProfilePageUrl= Driver.getDriver().getCurrentUrl();

        assertTrue(currentProfilePageUrl.contains("profile"));


    }

    @Then("user uploads profile image")
    public void user_uploads_profile_image() throws InterruptedException {

        Driver.getDriver().navigate().refresh();
        Thread.sleep(7000);

        String filePath= "C:\\Users\\sarab\\IdeaProjects\\KesifPlus_Project_11\\images\\Img-1.png";

        getProfilePage().uploadPhotoBtn.sendKeys(filePath);



    }
    @Then("user should see profile image is loaded")
    public void user_should_see_profile_image_is_loaded() throws InterruptedException {

        Thread.sleep(5000);

       boolean isImgUploaded= Boolean.parseBoolean(getProfilePage().uploadElmValue.getAttribute("data-img-loaded"));
       assertTrue(isImgUploaded);

     /*  Driver.getDriver().navigate().refresh();
       Thread.sleep(5000);

       Driver.getDriver().switchTo().frame(getProfilePage().popUpAlert);

        Utilities.waitForVisibility(getProfilePage().profilGuncellendiMsg, 10);

          String imgUploadedMsg= getProfilePage().profilGuncellendiMsg.getText();

       assertEquals(ConfigurationReader.getProperty("profilGuncellendiMsg"), imgUploadedMsg);

       Driver.getDriver().switchTo().parentFrame();*/

    }
    @Then("user changes profile image")
    public void user_changes_profile_image() throws InterruptedException {

        String filePath= "C:\\Users\\sarab\\IdeaProjects\\KesifPlus_Project_11\\images\\Img-2.png";
        getProfilePage().uploadPhotoBtn.sendKeys(filePath);

        Thread.sleep(5000);

        boolean isImgUploaded= Boolean.parseBoolean(getProfilePage().uploadElmValue.getAttribute("data-img-loaded"));
        assertTrue(isImgUploaded);



    }
    @Then("user should see pop-up message Profil Guncellendi")
    public void user_should_see_pop_up_message_profil_guncellendi() throws InterruptedException {
       /* Thread.sleep(10000);

        Driver.getDriver().switchTo().frame(getProfilePage().popUpAlert);

        Utilities.waitForVisibility(getProfilePage().profilGuncellendiMsg, 10);

        String imgUploadedMsg= getProfilePage().profilGuncellendiMsg.getText();

        assertEquals(ConfigurationReader.getProperty("profilGuncellendiMsg"), imgUploadedMsg);

        Driver.getDriver().switchTo().parentFrame();*/

    }
    @Then("user should type in Hakkımızda section")
    public void user_should_type_in_hakkımızda_section() throws InterruptedException {

         ProfilePage.scrollIntoViewJS(driver, getProfilePage().hakkindaTextArea);

         Thread.sleep(2000);
         getProfilePage().hakkindaTextArea.sendKeys(Keys.ENTER);

         Thread.sleep(2000);
         getProfilePage().textArea.sendKeys(ConfigurationReader.getProperty("textToSend"));



    }
    @Then("user should see the text Testers test")
    public void user_should_see_the_text_testers_test() throws InterruptedException {

        getProfilePage().yayinlaBtn.click();

        Thread.sleep(2000);

        String inputTextOnPage= getProfilePage().inputTextArea.getText();

        assertEquals(ConfigurationReader.getProperty("textToSend"), inputTextOnPage);



    }
    @Then("user should see pop-up message Profil güncellendi")
    public void user_should_see_pop_up_message_profil_güncellendi() {

     Utilities.waitForVisibility(getProfilePage().hakkindaGuncellendiPopUp, 3);

     String guncellendiHakkindaPopUp= getProfilePage().hakkindaGuncellendiPopUp.getText();

     assertEquals(ConfigurationReader.getProperty("hakkindaProfilGuncellendiMsg"), guncellendiHakkindaPopUp);

    }

}
