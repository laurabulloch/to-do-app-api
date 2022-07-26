package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mapstruct.factory.Mappers.getMapper;
@ContextConfiguration(classes = JpaContext.class)
class ToDoIntegrationTest {
        @Mock
        private ToDoRepository toDoRepository;
        private ToDoService toDoService;

        private final static String BASE_URI = "http://localhost:8080";
        ToDoDTO item1 = ToDoDTOBuilder.builder().id(1L).name("Item 1").build();

        @BeforeEach
        public void setUp() {
                assertNotNull(toDoRepository);

                toDoService = new ToDoService(toDoRepository, getMapper(ToDoMapper.class));
                RestAssured.baseURI = BASE_URI;
                toDoService.create(item1);
        }

        @Test
        void connectToToDos() {
        RestAssured.baseURI = BASE_URI;

        given().contentType(ContentType.JSON).when().get("/to-dos").then().statusCode(200);
        }
        @Test
        void getListToDos() {
                toDoService.create(ToDoDTOBuilder.builder().id(2L).name("Item 2").build());

                when().get("/to-dos")
                        .then().statusCode(200)
                        .body("id",hasItems(1L, 2L))
                        .body("name", hasItems("Item 1", "Item 2"));
        }
        @Test
        void postShouldReturnNewItem() {
                given()
                        .body(ToDoDTOBuilder.builder().id(3L).name("Item 3").build())
                        .contentType(ContentType.JSON)
                        .when().post("/to-dos")
                        .then().statusCode(201)
                        .body("id",hasItems(3L))
                        .body("name", hasItems("Item 3"));
        }
        @Test
        void deleteToDos() {
                when().delete("/to-dos", 2)
                        .then().statusCode(204);
        }
        @Test
        void postShouldReturnBadRequestWithoutBody() {
                when()
                        .post("/to-dos")
                        .then()
                        .statusCode(400);
        }
        @Test
        public void deleteItemShouldBeBadRequestIfNonExistingID() {
                when()
                        .delete("/to-dos", 4)
                        .then()
                        .statusCode(404);
        }
}