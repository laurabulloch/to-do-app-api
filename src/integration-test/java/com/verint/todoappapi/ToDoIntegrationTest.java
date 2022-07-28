package com.verint.todoappapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;

class ToDoIntegrationTest {
        private final static String BASE_URI = "http://localhost";

        @BeforeEach
        public void setUp() {
                RestAssured.baseURI = BASE_URI;
                RestAssured.port = 8080;

                given()
                        .body(ToDoDTOBuilder.builder().name("Item 1").build())
                        .contentType(ContentType.JSON)
                        .when().post("/to-dos");
                given()
                        .body(ToDoDTOBuilder.builder().name("Item 2").build())
                        .contentType(ContentType.JSON)
                        .when().post("/to-dos");
        }

        @Test
        void getListToDos() {

                when().get("/to-dos")
                        .then().statusCode(200)
                        .body("id",hasItems(1, 2))
                        .body("name", hasItems("Item 1", "Item 2"));
        }
        @Test
        void postShouldReturnNewItem() {
                given()
                        .body(ToDoDTOBuilder.builder().name("Item 3").build())
                        .contentType(ContentType.JSON)
                        .when().post("/to-dos")
                        .then().statusCode(200)
                        .body("name", containsString("Item 3"));
        }
        @Test
        void deleteToDos() {
                when().delete("/to-dos/1")
                        .then().statusCode(204);
        }
}