package Day3;

import io.restassured.response.Response;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class CookiesDemo{
    //@Test
    public void testCookies(){
        when()
                .get("https://google.com")

                .then()
                //MUST FAILED whiles cookies keep changing at every request
                .cookie("AEC", "AVcja2cypUJSw477vq4XvkvSSI7y7q3EwG6066own7tPxa9ZIooJyvUmRA")
                .log().all();
    }

    @Test
    public void getCookiesInfo(){
        Response res = when()
                .get("https://google.com");

        //get a single cookie
//        String cookie_value = res.getCookie("AEC");
//        System.out.println("value of cookie is: " + cookie_value);
//
        //get all cookies info
        Map<String, String> allCookies_value = res.getCookies();
        System.out.println(allCookies_value.keySet());

        for(String K : allCookies_value.keySet()){
            String cookie_value = res.getCookie(K);
            System.out.println(K + "       " + cookie_value);
        }

    }



}