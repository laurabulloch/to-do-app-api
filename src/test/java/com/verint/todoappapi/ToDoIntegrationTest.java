package com.verint.todoappapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

;

public class ToDoIntegrationTest {
        private final static String BASE_URI = "http://localhost:8080";

        @Test
        void connectToToDos() {
        RestAssured.baseURI = BASE_URI;

        given().contentType(ContentType.JSON).when().get("/to-dos").then().statusCode(200);
}
        @Test
        void ListToDos() {
                RestAssured.baseURI = BASE_URI;

                given().param("id", 1, "name", "Item 1").when().get("/to-dos").then()
                        .assertThat().log().all().statusCode(200)
                        .body("id",equalTo(1))
                        .body("name",equalTo("Item 1"));
        }
}