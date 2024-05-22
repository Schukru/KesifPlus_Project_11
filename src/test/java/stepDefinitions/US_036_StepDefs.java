package stepDefinitions;

import enums.Enum_1;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;

import static pages.ProjectsPage.projectName;

public class US_036_StepDefs extends CommonPage {
    @Given("User login with Email Address")
    public void userLoginWithEmailAddress() {
        getHomePage().loginWithEmail(Enum_1.USER2.getEmail(), Enum_1.USER2.getPassword());
    }

    @And("Creates a new Project")
    public void createsANewProject() {
        getProjectsPage().createNewProject();
    }

    @And("Open the project")
    public void openTheProject() {
        getProjectsPage().openProject(projectName);
    }

    @And("Add {string} to the room")
    public void addToTheRoom(String menuTitle) {
        getProjectsPage().addFurniture(menuTitle);
    }

    @Then("Verify total cost is changed")
    public void verifyTotalCostIsChanged() {
        getProjectsPage().verifyCost();
    }

    @Then("Delete current project")
    public void deleteCurrentProject() {
        getProjectsPage().deleteProject();
    }

}
