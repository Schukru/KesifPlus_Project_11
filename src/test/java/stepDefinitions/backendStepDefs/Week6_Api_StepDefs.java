package stepDefinitions.backendStepDefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utility.API.MyProjects;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.nullValue;
import static utility.API.MySelf.myProjects;
import static utility.API.MySelf.requestSpecification;

public class Week6_Api_StepDefs {
    public static String myProjectName;

    @Given("user create a new project through api")
    public void userCreateANewProjectThroughApi() {
        Faker faker = new Faker();
        myProjectName = faker.name().firstName();

        String endpoint = "/project";

        Response response = given()
                .spec(requestSpecification)
                .body("{\"name\":\"" + myProjectName + "\"}")
                .when()
                .post(endpoint);

        response.then()
                .statusCode(200);

        System.out.println("myProjectName = " + myProjectName);
    }

    @When("verify that the project was created")
    public void verifyThatTheProjectWasCreated() {
        MyProjects.ProjectValues projectValues = myProjects.getMyProjectValues(myProjectName);
        System.out.println("projectValues.getDb_id() = " + projectValues.getDb_id());
        System.out.println("projectValues.getId() = " + projectValues.getId());
        System.out.println("projectValues.getCreated_at() = " + projectValues.getCreated_at());
        Assert.assertNotNull(projectValues);
    }

    @And("get wall-colon values to check")
    public void getWallColonValuesToCheck() {
        MyProjects.ProjectValues projectValues = myProjects.getMyProjectValues(myProjectName);
        String endpoint = "/wall-colon/list?project_id=" + projectValues.getDb_id();

        Response response = given()
                .spec(requestSpecification)
                .when()
                .get(endpoint);

        response.then()
                .statusCode(200)
                .body("items", empty())
                .body("partitionKey", nullValue())
                .body("sortValue", nullValue());
    }

    @And("change wall-colon values in the room")
    public void changeWallColonValuesInTheRoom() {
        MyProjects.ProjectValues projectValues = myProjects.getMyProjectValues(myProjectName);
        String endpoint = "/wall-colon/" + projectValues.getDb_id();

        String jsonBody = """
    [
    {
        "id": "c983d78b26b",
        "color": "#DAC0A3",
        "image": "",
        "duvar_id": "29521ace5f8",
        "pozisyonu": "kolon",
        "yerden_yuksekligi": 0,
        "position_dikey": [
            0,
            1.5,
            0
        ],
        "rotation_dikey": [
            0,
            0,
            0
        ],
        "position": [
            -1.2076845013301385,
            1.5,
            1.7763568394002505e-15
        ],
        "rotation": [
            0,
            0,
            0
        ],
        "en": 0.5,
        "boy": 3,
        "derinlik": 0.5,
        "X": -1.2076845013301385,
        "Y": 1.5,
        "Z": 1.7763568394002505e-15,
        "x0": 0,
        "y0": 0.25,
        "z0": 0,
        "activity": false
    },
    {
        "id": "83d78b26b0d",
        "color": "#DAC0A3",
        "image": "",
        "duvar_id": "983d78b26b0",
        "pozisyonu": "kiris",
        "yerden_yuksekligi": 2,
        "position_dikey": [
            3,
            2,
            1
        ],
        "rotation_dikey": [
            0,
            0,
            0
        ],
        "position": [
            0,
            0,
            0
        ],
        "rotation": [
            0,
            0,
            0
        ],
        "en": 6,
        "boy": 0.5,
        "derinlik": 0.5,
        "X": 0,
        "Y": 2.75,
        "Z": 1,
        "x0": 0,
        "y0": 0,
        "z0": 0
    },
    {
        "id": "d78b26b0dc9",
        "color": "#DAC0A3",
        "image": "",
        "duvar_id": "3d78b26b0dc",
        "pozisyonu": "ekDuvar",
        "yerden_yuksekligi": 0,
        "position_dikey": [
            0,
            1.5,
            0
        ],
        "rotation_dikey": [
            0,
            0,
            0
        ],
        "position": [
            1.6410056024411235,
            1.5,
            -1.3877837366727803
        ],
        "rotation": [
            0,
            0,
            0
        ],
        "en": 0.1,
        "boy": 3,
        "derinlik": 2,
        "X": 1.6410056024411235,
        "Y": 1.5,
        "Z": -1.3877837366727803,
        "x0": 0,
        "y0": 0.25,
        "z0": 0,
        "activity": false
    }
]
                """;
        Response response = given()
                .spec(requestSpecification)
                .body(jsonBody)
                .when()
                .post(endpoint);

        response.then()
                .statusCode(200);

        JsonPath jsonResponse = response.jsonPath();
        List<String> positions = jsonResponse.getList("pozisyonu");
        Assert.assertTrue(positions.contains("kiris"));
        Assert.assertTrue(positions.contains("kolon"));
        Assert.assertTrue(positions.contains("ekDuvar"));
    }

    @And("delete the project")
    public void deleteTheProject() {
        MyProjects.ProjectValues projectValues = myProjects.getMyProjectValues(myProjectName);
        String endpoint = "/project?id="+ projectValues.getDb_id() + "&createdAt=" + projectValues.getCreated_at() ;

        Response response = given()
                .spec(requestSpecification)
                .when()
                .delete(endpoint);

        response.then()
                .statusCode(200);
    }

    @Then("verify that the project was deleted")
    public void verifyThatTheProjectWasDeleted() {
        MyProjects.ProjectValues projectValues = myProjects.getMyProjectValues(myProjectName);
        Assert.assertNull(projectValues);
    }
}
