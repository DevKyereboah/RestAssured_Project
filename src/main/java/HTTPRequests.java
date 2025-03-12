import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class HTTPRequests {
    int id ;
    @Test(priority = 1)
    public void getUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                //.body("page", equalTo(2))
                .log().all();
    }

    @Test(priority = 2)
    public void createUser(){
        HashMap data = new HashMap();
        data.put("name", "morpheus");
        data.put("job", "leader");

        id =
                given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

//                .then()
//                .statusCode(201)
//                .log().all();
    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    public void update(){
        HashMap data = new HashMap();
        data.put("name", "john");
        data.put("job", "teacher");

        given()
                .contentType(("application/json"))
                .body(data)

                .when()
                .put("https://reqres.in/api/users/" +id)
                .then()
                .statusCode(200)
                .log().all();
        System.out.println("https://reqres.in/api/users/" +id);

        System.out.println("space");
    }

    @Test(priority = 4, dependsOnMethods = {"update"})
    public void deleteUser(){

                when()
                .delete("https://reqres.in/api/users/" + id)
                .then().statusCode(204);
    }

}

