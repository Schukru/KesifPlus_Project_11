package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;
import utility.API.MyProjects;
import utility.DB.DatabaseUtilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static stepDefinitions.backendStepDefs.Week6_Api_StepDefs.myProjectName;

import static utility.API.MySelf.myProjects;
import static utility.DB.DatabaseUtilities.ddb;

public class db_StepDefs {

    @Given("verify that the project was created through DB")
    public void verifyThatTheProjectWasCreatedThroughDB() {
        String tableName = "Projects_stg";
        MyProjects.ProjectValues projectValues = myProjects.getMyProjectValues(myProjectName);
        String myDbId = projectValues.getDb_id();

        Map<String, String > expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#k", "dbId");

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":v", AttributeValue.builder().s(myDbId).build());

        QueryRequest queryRequest = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression("#k = :v")
                .expressionAttributeNames(expressionAttributeNames)
                .expressionAttributeValues(expressionAttributeValues)
                .build();

        QueryResponse queryResponse = ddb.query(queryRequest);
        List<Map<String, AttributeValue>> items = queryResponse.items();

        if (items.isEmpty()){
            System.out.println("dbId bulunamadı");
        } else {
            for (Map<String, AttributeValue> item : items) {
                System.out.println("database sorgu sonucu :");
                item.forEach((key, value) -> System.out.println(key + ": "+ value.s()));
            }
        }

        Assert.assertTrue(items.stream()
                .anyMatch(item -> myProjectName.equals(item.get("name").s())));

    }

    @Given("delete the project through DB")
    public void deleteTheProjectThroughDB() {
        MyProjects.ProjectValues projectValues = myProjects.getMyProjectValues(myProjectName);
        String myDbId = projectValues.getDb_id();

        DatabaseUtilities.deleteProjectItem(myProjectName, "dbId", myDbId);

    }

    @When("verify that the project was deleted through DB")
    public void verifyThatTheProjectWasDeletedThroughDB() {
        String tableName ="Projects_stg";

        MyProjects.ProjectValues projectValues = myProjects.getMyProjectValues(myProjectName);
        String myDbId = projectValues.getDb_id();

        Map<String, String > expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#k", "dbId");

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":v", AttributeValue.builder().s(myDbId).build());

        QueryRequest queryRequest = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression("#k = :v")
                .expressionAttributeNames(expressionAttributeNames)
                .expressionAttributeValues(expressionAttributeValues)
                .build();

        QueryResponse queryResponse = ddb.query(queryRequest);
        List<Map<String, AttributeValue>> items = queryResponse.items();

        Assert.assertTrue(items.isEmpty());

    }
}
