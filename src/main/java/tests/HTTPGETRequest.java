package tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class HTTPGETRequest {
   @Test
    public void testGETRequest() {
       RestAssured.get("https://api.github.com")
               .then()
               .statusCode(200);

       //https://api.github.com/
       //https://jsonplaceholder.typicode.com/
       //https://reqres.in
   }
}
