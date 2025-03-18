package Day2;

import org.json.JSONObject;
import org.testng.annotations.Test;

import javax.swing.plaf.IconUIResource;
import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/* Different ways to create POST requests
1) post request body using Hashmap
2) Post request body using Org.json
3) Post request body using POJO class
4) Post request using json file data
 */
@Test(priority = 1)
public class WaysToCreatePostRequest {
    //1) post request body using Hashmap
    public void testPostUsingHashmap() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("firstName", "Tom");
        data.put("lastName", "Sharapova");

        String courseArray[] = {"c#", "C++"};
        data.put("courses", courseArray);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/employees")

                .then()
                .statusCode(201)
                .body("firstName", equalTo("Tom"))
                .body("lastName", equalTo("Sharapova"))
                .header("Content-Type", "application/json")
                .log().all();
    }
//    @Test(priority = 2)
//    public void testDelete(){
//        given()
//                .when()
//                .delete("http://localhost:3000/employees/4")
//
//                .then()
//                .statusCode(204);
//    }

    @Test(priority = 2)
    public void testPostUsingJsonLibrary(){
        JSONObject data = new JSONObject();
        data.put("firstName", "Tom");
        data.put("lastName", "Sharapova");

        String courseArray[] = {"c#", "C++"};
        data.put("courses", courseArray);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/employees/")

                .then()
                .statusCode(201)
                .body("firstName", equalTo("Tom"))
                .body("lastName", equalTo("Sharapova"))
                .header("Content-Type", "application/json")
                .log().all();
    }

    @Test(priority = 3)
    public void testPOJORequest(){
        POJO_PostRequest data = new POJO_PostRequest();
        data.setFirstName("Tom");
        data.setLastName("Sharapova");

        String courseArray[] = {"c#", "C++"};
        data.setCourses(courseArray);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/employees/")

                .then()
                .statusCode(201)
                .body("firstName", equalTo("Tom"))
                .body("lastName", equalTo("Sharapova"))
                .header("Content-Type", "application/json")
                .log().all();
    }
}
