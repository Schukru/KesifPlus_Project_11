package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;

import java.util.List;

public class US_007_StepDefs extends CommonPage {
    @Given("User click or hover over the Daha Fazla menu")
    public void userClickOrHoverOverTheDahaFazlaMenu() {
        getHomePage().displayDahaFazlaMenu();
    }

    @Then("Verify that all menu options are visible and clickable")
    public void verifyThatAllMenuOptionsAreVisibleAndClickable(DataTable dataTable) {
        List<List<String>> menuItems = dataTable.asLists(String.class);
        getHomePage().verifyDahaFazlaMenu(menuItems);
    }

    @When("Click {string} option in Daha Fazla menu")
    public void clickOptionInDahaFazlaMenu(String dropDownTitle) {
        getHomePage().clickDropDownOption(dropDownTitle);
    }

    @Then("Verify that different support options are available in the Destek page")
    public void verifyThatDifferentSupportOptionsAreAvailableInTheDestekPage(DataTable dataTable) {
        List<List<String>> pageOptions = dataTable.asLists(String.class);
        getHomePage().verifyPageOptions(pageOptions);

    }

    @Then("Verify that footer section is available in current page")
    public void verifyThatFooterSectionIsAvailableInCurrentPage(DataTable dataTable) {
        List<List<String>> footerOptions = dataTable.asLists(String.class);
        getHomePage().verifyFooterSection(footerOptions);
    }

    @Then("Verify that {string} are visible and clickable")
    public void verifyThatAreVisibleAndClickable(String option) {
        getHomePage().verifyOptionForOutline(option);
    }
}
