import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class Day1 {
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
        System.out.println(id);
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
    }

    @Test(priority = 4, dependsOnMethods = {"update"})
    public void deleteUser(){
        given()
                .when()
                .delete("https://reqres.in/api/users/" + id)
                .then().statusCode(204)
                .log().all();
    }

}

