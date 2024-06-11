package utility.API;

import io.restassured.path.json.JsonPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static utility.API.MySelf.*;

public class MyProjects {
    private Map<String, ProjectValues> mapProjectValues = new HashMap<>();

    public class ProjectValues{
        private String id;
        private String db_id;
        private String created_at;

        public ProjectValues(String id, String dbId, String createdAt){
            this.id = id;
            this.db_id = dbId;
            this.created_at = createdAt;
        }

        public String getId() {
            return id;
        }

        public String getDb_id() {
            return db_id;
        }

        public String getCreated_at() {
            return created_at;
        }
    }

    public Map<String, ProjectValues> getMyAllProjects(){
        mapProjectValues.clear();
        String endpoint = "/project/list?userId=" + userId;

        response = given()
                .spec(requestSpecification)
                .when()
                .get(endpoint);

        response.then()
                .statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        List<String> names = jsonPath.getList("name");
        List<String> ids = jsonPath.getList("id");
        List<String> db_ids = jsonPath.getList("db_id");
        List<String> created_ats = jsonPath.getList("created_at");

        for(int i=0; i < names.size(); i++){
            ProjectValues details = new ProjectValues(ids.get(i), db_ids.get(i), created_ats.get(i));
            mapProjectValues.put(names.get(i), details);
        }
        return mapProjectValues;
    }

    public ProjectValues getMyProjectValues(String projectName){
        return getMyAllProjects().get(projectName);
    }

}
