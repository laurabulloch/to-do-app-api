package com.verint.todoappapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.test.context.ContextConfiguration;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;

@ContextConfiguration(classes = JpaContext.class)
class ToDoIntegrationTest {

        @BeforeEach
        public void setUp() {
                RestAssured.baseURI = "http://localhost:8080";

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
        void connectToToDos() {
        given().contentType(ContentType.JSON).when().get("/to-dos").then().statusCode(200);
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
        @Test
        void postShouldReturnBadRequestWithoutBody() {
                when()
                        .post("/to-dos")
                        .then()
                        .statusCode(415);
        }
        @Test
        public void deleteItemShouldBeBadRequestIfNonExistingID() {
                when()
                        .delete("/to-dos/999")
                        .then()
                        .statusCode(404);
        }
}