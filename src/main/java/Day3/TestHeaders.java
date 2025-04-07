package Day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestHeaders {
    @Test(priority = 1)
    public void testHeaders(){
        given()
                .when()
                .get("https://www.google.com")

                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .header("Content-Encoding", "gzip")
                .and()
                .header("Server", "gws")
                .log().all();

    }
    @Test(priority = 2)
    public void getHeaders(){
        Response res = given()
                .when()
                .get("https://google.com");

//                String headerValue = res.getHeader("Content-type");
//                System.out.println("The value of the header is: " + headerValue);

        // get all headers info
        Headers myHeaders = res.getHeaders();
        for(Header headers : myHeaders){
            System.out.println(headers.getName() + ": " + headers.getValue());
        }


    }
}
