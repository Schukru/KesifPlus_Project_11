package pages;

import org.openqa.selenium.support.PageFactory;

import static stepDefinitions.Hooks.driver;

public abstract class CommonPage {

    public CommonPage() {
        PageFactory.initElements(driver, this);
    }

    private LoginPage loginPage;
    private HomePage homePage;

    private ProfilePage profilePage;

    public LoginPage getLoginPage() {
        if (loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public HomePage getHomePage() {
        if (homePage == null){
            homePage = new HomePage();
        }
        return homePage;
    }

    public ProfilePage getProfilePage() {
        if (profilePage == null){
            profilePage = new ProfilePage();
        }
        return profilePage;
    }





}