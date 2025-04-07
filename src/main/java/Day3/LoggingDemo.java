package Day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoggingDemo {
    @Test
    public void testlogs(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")

                .then()
                //.log().body();
                //.log().cookies();
                //.log().headers();
                .log().all();

    }
}
