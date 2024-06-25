package utility.DB;

import lombok.SneakyThrows;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import utility.ConfigurationReader;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUtilities {

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    public static ResultSet resultSet;
    public static DynamoDbClient ddb;

    /**
     * method database connection i olusturmak icin kullanildi
     *
     * @author omeryttnc
     * @since 10.02.2024
     */
    @SneakyThrows
    public static void createMYSQLConnection() {

        connection = DriverManager.getConnection(
                ConfigurationReader.getProperty("urlDb"),
                ConfigurationReader.getProperty("usernameDb"),
                ConfigurationReader.getProperty("passwordDb")
        );

    }

    @SneakyThrows
    public static void createSQLITEConnection() {
        connection = DriverManager.getConnection("jdbc:sqlite:src/test/resources/SqliteDatabase.db");
    }


    /**
     * method connectionlarin kapatilmasi icin kullanildi
     * kapatilacak connection statement resultSet
     *
     * @author omeryttnc
     * @since 10.02.2024
     */
    @SneakyThrows
    public static void closeConnection() {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    @SneakyThrows
    public static void executeUpdateStatement(String sql) { // insert update delete
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @SneakyThrows
    public static ResultSet executeQueryStatement(String sql) { // read
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }


    @SneakyThrows
    public void updatePreparedStatement(String sql, Object object1, Object object2, Object object3, Object object4) {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, object1);
        preparedStatement.setObject(2, object2);
        preparedStatement.setObject(3, object3);
        preparedStatement.setObject(4, object4);
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    public void updatePreparedStatement(String sql, Object object1, Object object2, Object object3) {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, object1);
        preparedStatement.setObject(2, object2);
        preparedStatement.setObject(3, object3);
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    public void updatePreparedStatement(String sql, Object object1, Object object2) {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, object1);
        preparedStatement.setObject(2, object2);
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    public void updatePreparedStatement(String sql, Object object1) {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, object1);
        preparedStatement.executeUpdate();
    }

    /**
     * method son eklenen urunu approve etmek icin kullanildi
     *
     * @author omeryttnc
     * @since 13.02.2024
     */
    public static void approveLastProduct() {
        executeUpdateStatement("UPDATE `hub_product` SET `product_listing_state` = 'APPROVED' WHERE `product_listing_state` LIKE 'IN_REVIEW' order BY id DESC limit 1;");
    }

    public static Connection getConnection(){
        return connection;
    }

    public static DynamoDbClient createDynamoDBConnection(String profileName){
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.builder()
                .profileName("default")
                .build();

        DynamoDbClientBuilder builder = DynamoDbClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.EU_CENTRAL_1);

        ddb = builder.build();
        return ddb;
    }

    public static void closeDynamoDBConnection(){
        ddb.close();
    }

    public static void deleteProjectItem(String projectName, String tableKey, String keyValue){
        Map<String, AttributeValue> keyToDelete = new HashMap<>();
        keyToDelete.put(tableKey, AttributeValue.builder().s(keyValue).build());

        DeleteItemRequest deleteItemRequest = DeleteItemRequest.builder()
                .tableName("Projects_stg")
                .key(keyToDelete)
                .build();

        try {
            ddb.deleteItem(deleteItemRequest);
            System.out.println("Item; " + keyValue + " was successfully deleted");
        } catch (AwsServiceException e) {
            throw new RuntimeException(e);
        }

    }
}

