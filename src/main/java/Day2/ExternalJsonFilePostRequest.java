package Day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ExternalJsonFilePostRequest {

    @Test
    public void testExternalJsonFile() throws FileNotFoundException {
        File f = new File(".//Body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);


        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/employees/")

                .then()
                .statusCode(201)
                .body("firstName", equalTo("Scott"))
                .body("lastName", equalTo("France"))
                .header("Content-Type", "application/json")
                .log().all();
    }
}
