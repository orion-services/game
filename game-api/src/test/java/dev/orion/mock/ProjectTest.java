package dev.orion.mock;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(MockTest.class)
public class ProjectTest {

    @Test
    @Order(1)
    public void testListPlayers() {
        given()
        .port(8080)
        .when().get("/game/api/v1/playerlist")
        .then()
           .statusCode(200)
           .body(is("{\"name\":\"Guilherme\"}"));
    }

}

