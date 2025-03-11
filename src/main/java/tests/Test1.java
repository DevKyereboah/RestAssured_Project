package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test1 {
    public void test1(){}
    Response response =
                         RestAssured.get("https://reqres.in/api/users?page=2");
    System.out.p

    response.getStatusCode();
    response.getTime();

}

