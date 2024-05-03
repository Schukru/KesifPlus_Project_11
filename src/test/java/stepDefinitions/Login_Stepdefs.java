package stepDefinitions;

import enums.Enum_1;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.ConfigurationReader;

import java.util.List;

import static stepDefinitions.Hooks.driver;

public class Login_Stepdefs {

    private String user1_email = "user1@gmail.com";

    @Given("Go to main page")
    public void GoToMainPage() {
        driver.get("https://www.mobilyaplan.com/");
    }

    @When("version1 calissin")
    public void version1Calissin() {
        System.out.println("Version 1 çalıştı");
    }

    // scenario 2 datayi configReader dan cekerek
    @When("Configuration properties kullanarak")
    public void configurationPropertiesKullanarak() {
        System.out.println("user name :" + ConfigurationReader.getProperty("user1_email")
                + " password : " + ConfigurationReader.getProperty("user1_password"));
    }

    @When("version2 calissin")
    public void version2Calissin() {
        System.out.println("Version 2 çalıştı");
    }

    @When("version3 calissin")
    public void version3Calissin() {
        System.out.println("Version 3 çalıştı");
    }

    @When("version4 calissin")
    public void version4Calissin() {
        System.out.println("Version 4 çalıştı");
    }

    @Given("tag kullanarak")
    public void tagKullanarak() {
        System.out.println("tag kullanarak cagirildi");
        driver.get("https://www.mobilyaplan.com/");
    }








    @When("login with user1 credential")
    public void loginWithUser1Credential() {
        Enum_1.USER1.login();
    }

//    @Then("verify that user become login")
//    public void verifyThatUserBecomeLogin() {
//    }

    // scenario 1 datayi step icerisinde olusturarak
    @When("Step Definition icinde user and password")
    public void stepDefinitionIcindeUserAndPassword() {
        String user1_password = "user1Passsword";
        System.out.println("user name :" + user1_email + " password : " + user1_password);
    }

    // scenario 3 datalari step icerisinde gondererek
    @Given("Scenariom icinde string olarak {string}")
    public void scenariomIcindeStringOlarak(String arg0) {
        System.out.println(arg0);
    }

    @Given("Scenariom icinde int olarak {int}")
    public void scenariomIcindeIntOlarak(int arg0) {
        System.out.println(arg0);
    }

    @Given("Scenariom icinde double olarak {double}")
    public void scenariomIcindeDoubleOlarak(double arg0) {
        System.out.println(arg0);
    }

    @Given("Scenariom icinde boolean olarak {string}")
    public void scenariomIcindeBooleanOlarak(String arg0) {
        System.out.println(Boolean.parseBoolean(arg0));
    }

    @Given("Scenariom icinde genel olarak {string} , {int} , {double} ve {string}")
    public void scenariomIcindeGenelOlarakVe(String arg0, int arg1, double arg2, String arg3) {
        System.out.println(arg0);
        System.out.println(arg1);
        System.out.println(arg2);
        System.out.println(Boolean.parseBoolean(arg3));
    }


    // scenario 4 datatable kullanarak
    @Given("Data table kullanarak")
    public void data_table_kullanarak(DataTable dataTable) {
        List<String> usernames = dataTable.column(0);
        List<String> password = dataTable.column(1);

        for (int i = 0; i < usernames.size(); i++) {
            System.out.print("usernames.get(" + i + ") = " + usernames.get(i) + "   ---   ");
            System.out.println("password.get(" + i + ") = " + password.get(i));
        }
    }

    // scenario 5 scenario outline kullanarak
    @Given("Scenariom Outline {string} and {int} {double} {string}")
    public void scenariom_outline_and(String string, int int1, double double1, String string2) {
        System.out.println("username : " + string + " rakam : " + int1 + " kusurat : " + double1 + " dogru : " + Boolean.parseBoolean(string2));
    }


    @Then("Sadece {int} değerlerini kullanabiliriz")
    public void sadece_değerlerini_kullanabiliriz(Integer rakam) {
        System.out.println("rakam : " + rakam);
    }


    @Given("Enum kullanarak")
    public void enumKullanarak() {
        Enum_1.USER4.login();
    }

    @When("Step Definition icinde user and passwords")
    public void stepDefinitionIcindeUserAndPasswords() {
        System.out.println("deneme");
    }

}