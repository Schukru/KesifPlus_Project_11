package stepDefinitions.backendStepDefs;

import enums.Enum_1;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class api_StepDefs {
    private  String endpoint;
    private Response response;
    @Given("I have the login {string}")
    public void IHaveTheLogin(String baseURI) {
        endpoint = baseURI;
    }

    @When("I send a POST request with valid credentials")
    public void ISendAPOSTRequestWithValidCredentials() {
        Map<String, String> body = new HashMap<>();
        body.put("email", Enum_1.USER2.getEmail());
        body.put("password", Enum_1.USER2.getPassword());

        response = given()        // hazırla
                .contentType(ContentType.JSON)
                .body(body)
                .when()          // gönder
                .post(endpoint);
    }

    @Then("I should receive a successful response {string}")
    public void ıShouldReceiveASuccessfulResponse(String statusCode) {
        int code = Integer.parseInt(statusCode);
        response
                .then()           // yanıt üzerinde doğrulama zincirini başlat
                .statusCode(code)
                .body("redirectUrl", equalTo("/projects"));
    }
}
