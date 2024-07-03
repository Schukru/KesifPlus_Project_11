package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.ConfigurationReader;
import utility.Driver;
import utility.UI.Utilities;

import java.util.List;

import static stepDefinitions.Hooks.driver;

public class HomePage extends CommonPage {

    public void displayDahaFazlaMenu() {
        WebElement btn = getMainButton("Daha Fazla");
        hoverAndWaitButton(btn, 2);
        btn.click();
        Utilities.waitForClickability(getDropDownButton("Hakkımızda"), 5);
    }

    public WebElement getMainButton(String option) {
        WebElement element = Driver.getDriver().findElement(By.xpath(
                "//*[@data-testid='linkElement']//p[.='" + option + "']"));
        return element;
    }

    public void hoverAndWaitButton(WebElement element, int time) {
        Utilities.hover(element);
        Utilities.waitFor(time);
        Utilities.hover(element);
    }

    public void verifyDahaFazlaMenu(List<List<String>> titles) {
        String title;
        for (int i = 0; i < titles.size(); i++) {
            title = titles.get(i).get(0);
            System.out.println("title = " + title);
            Assert.assertTrue(isOptionClickable(title));
        }
    }

    public boolean isOptionClickable(String option) {
        WebElement btn = getDropDownButton(option);
        return (btn.isDisplayed() &&
                btn.isEnabled());
    }

    public WebElement getDropDownButton(String option) {
        WebElement element = Driver.getDriver().findElement(By.xpath(
                "//ul[@id='comp-kybbnmeimoreContainer']//p[.='" + option + "']"
        ));
        return element;
    }

    public void clickDropDownOption(String title){
        WebElement element = getDropDownButton(title);
        element.click();
    }

    public void verifyPageOptions(List<List<String>> options){
        options.stream()
                .map(optionList -> optionList.get(0))
                .forEach(option -> Assert.assertTrue(isOptionVisible(option)));
    }

    public boolean isOptionVisible(String option){
        WebElement btn = getDestekPageOption(option);
        System.out.println("Destek options = " + option);
        return btn.isDisplayed();
    }

    public WebElement  getDestekPageOption(String title){
        WebElement element = Driver.getDriver().findElement(By.xpath(
                "(//div[@data-testid='columns']//span[contains(text(), '" + title + "')])[1]"
        ));
        return element;
    }

    public void verifyFooterSection(List<List<String>> options) {
        // Way 1  -> Structural Method
        String option;
        for (int i = 0; i < options.size(); i++) {
            option = options.get(i).get(0);
            Assert.assertTrue(isFooterOptionFunctional(option));
        }

        // Way 2  -> Functional Method
//        options.stream()
//                .map(optionList -> optionList.get(0))
//                .forEach(option -> Assert.assertTrue(isFooterOptionFunctional(option)));
    }

    public boolean isFooterOptionFunctional(String title){
        WebElement btn = getFooterOption(title);
        System.out.println("Footer title = " + title);
        return (btn.isEnabled() &&
                btn.isDisplayed());
    }

    public WebElement getFooterOption(String title){
        WebElement element = Driver.getDriver().findElement(By.xpath(
                "//footer[@id='SITE_FOOTER']//*[contains(text(), '" + title + "')]"));
        return element;
    }

    public void verifyOptionForOutline(String option){
        Assert.assertTrue(isOptionClickable(option));
    }


    // Sukru US_023
    @FindBy (xpath = "//button[@type='button']")
    private WebElement googleLoginButton;
    @FindBy (xpath = "//input[@type='email']")
    private WebElement inputGmail;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement inputPassword;
    @FindBy (xpath = "//span[contains(text(), 'Next')]")
    private WebElement nextButton;
    @FindBy (xpath = "//button//span[contains(text(), 'Proje Olustur')]")
    private WebElement projeOlusturButton;
    @FindBy (xpath = "//*[contains(text(), 'mobilyaplan.test.user@gmail.com')]")
    private WebElement myEmailText;

    public void clickGoogleLoginButton(){
        googleLoginButton.click();
    }

    public void inputGmailAccount(){
        Utilities.sendText(inputGmail, ConfigurationReader.getProperty("google_user_email"));
        nextButton.click();

        Utilities.sendText(inputPassword, ConfigurationReader.getProperty("google_user_password"));
        nextButton.click();
    }

    public void verifyProjectPage(){
        Utilities.waitForClickability(projeOlusturButton, 10);
        Assert.assertEquals("https://stg.mobilyaplan.app/projects", driver.getCurrentUrl());
        Assert.assertTrue(myEmailText.isEnabled());
    }


    //  US 036 sukru

    @FindBy(css = "input[name='email']")
    private WebElement inputEmail;
    @FindBy(css = "input[name='password']")
    private WebElement inputEmailPassword;
    @FindBy(xpath = "//span[.='Login']")
    private WebElement loginButton;

    public void loginWithEmail(String eMail, String password){
        Utilities.sendText(inputEmail, eMail);
        Utilities.sendText(inputPassword, password);
        loginButton.click();
    }

}
