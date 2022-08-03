package com.verint.todoappapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.*;

class ToDoIntegrationTest {
        private static Integer nextId;
        private static Integer startId;
        @BeforeAll
        public void setUp() {
                if(System.getProperty("url") == null){
                        //RestAssured.baseURI = "http://localhost:8080";
                        RestAssured.baseURI = "https://integration-tests.herokuapp.com";
                }
                else{
                        RestAssured.baseURI = System.getProperty("url");
                }

                startId = given()
                        .contentType(ContentType.JSON)
                        .body(ToDoDTOBuilder.builder().name("Item 1").build())
                        .when()
                        .post("/todos")
                        .then()
                        .extract().path("id");

                nextId = startId + 1;

        }

        @Test
        @Order(1)
        void postShouldReturnNewItem() {

                given()
                        .body(ToDoDTOBuilder.builder().name("Item 2").build())
                        .contentType(ContentType.JSON)
                        .when().post("/to-dos")
                        .then().statusCode(200)
                        .body("name", containsString("Item 2"));

        when().get("/to-dos")
                        .then().statusCode(200)
                        .body("id",hasItems(nextId))
                        .body("name", hasItems("Item 1", "Item 2"));

        }

        @Test
        @Order(2)
        void getListToDos() {
                when().get("/to-dos")
                        .then().statusCode(200)
                        .body("id",hasItems(nextId))
                        .body("name", hasItems("Item 1", "Item 2"));
        }

        @Test
        @Order(3)
        void deleteToDos() {
                when().delete("/to-dos/" + nextId.toString())
                        .then().statusCode(204);

                when().get("/to-dos")
                        .then().statusCode(200)
                        .body("id", not(nextId));
        }
        @AfterAll
        public static void cleanUp(){
                given()
                        .when().delete("/to-dos/" + startId.toString());
        }
}