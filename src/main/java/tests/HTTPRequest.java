package tests;

import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class HTTPRequest {

    int id;

    // Test to get users
    @Test(priority = 1)
    public void getUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)  // Checking for a 200 status code
                .body("page", equalTo(2))  // Ensure the 'page' field is 2
                .log().all();  // Log the full response
    }

    // Test to create a new user and extract the 'id' from the response
    @Test(priority = 2)
    public void createUser() {
        // Using type-safe HashMap
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Pavan");
        data.put("job", "trainer");

        // Extract the 'id' from the response and store it in the 'id' variable
        id = given()
                .contentType("application/json")
                .body(data)  // The body of the request is the 'data' HashMap
                .when()
                .post("https://reqres.in/api/users")  // API endpoint to create a user
                .jsonPath().getInt("id");  // Extract the 'id' from the response body

        // You can optionally log the response to check if the 'id' was returned
        System.out.println("Created user with ID: " + id);
    }

    // Test to update the user with the 'id' retrieved in the previous test
    @Test(priority = 3, dependsOnMethods = {"createUser"})
    public void updateUser() {
        // Using type-safe HashMap to update user details
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "John");
        data.put("job", "Teacher");


        given()
                .contentType("application/json")
                .body(data)

                .when()
                .put("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    public void deleteUser(){
        given()
                .when()
                .delete("https://reqres.in/api/users" + id)

                .then()
                .statusCode(204)
                .log().all();
    }

}
