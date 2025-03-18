package Day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathAndQueryParams {
    //https://reqres.in/api/users?page=2$id=5

    @Test
    public void testQueryAndPathParameters(){
        given()
                .pathParam("myPath", "users")
                .queryParam("page", 2)
                .queryParam("id", "5")

                .when()
                .get("https://reqres.in/api/{myPath}")

                .then()
                .statusCode(200)
                .log().all();
    }
}
