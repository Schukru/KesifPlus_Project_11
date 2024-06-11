package utility.API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import utility.ConfigurationReader;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MySelf {

    public static RequestSpecification requestSpecification;
    public static Response response;
    public static String userId;
    private String token ="";
    public static MyProjects myProjects = new MyProjects();

    public MySelf(String email, String password){
        requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.baseUri(ConfigurationReader.getProperty("baseURI"));

        this.token = getToken(email, password);

        System.out.println("token = " + token);
        requestSpecification.header("Authorization", "Bearer " + token);

        userId = getUserId();
    }

    public String getToken(String email, String password){
        String loginEndpoint = "/login/sign-in";

        response = given()
                .spec(requestSpecification)
                .body("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
                .when()
                .post(loginEndpoint);

        response.then()
                .statusCode(200)
                .body("redirectUrl", equalTo("/projects"));
//                .time(Matchers.lessThan(500L));

        Map<String, String> cookies = response.getCookies();
        token = cookies.get("token");

        Assert.assertNotNull(token);
        return token;
    }

    public String getUserId(){
        String endpoint = "/user/myself";

        response = given()
                .spec(requestSpecification)
                .when()
                .get(endpoint);

        response.then()
                .statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        System.out.println("jsonPath.getString(\"id\") = " + jsonPath.getString("id"));
        System.out.println("jsonPath.get(\"id\") = " + jsonPath.get("id"));

        return jsonPath.getString("id");
    }


}
