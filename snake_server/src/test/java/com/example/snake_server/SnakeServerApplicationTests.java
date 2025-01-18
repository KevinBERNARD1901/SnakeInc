package com.example.snake_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;

@SpringBootTest
@ActiveProfiles("test")
public class SnakeServerApplicationTests {

	// URI url_base = RestAssured.baseURI = "";


	@Test
    public void testGetScores() {
        String snake = "anaconda";
        given()
        .when()
            .get("http://localhost:8080/api/v1/scores?snake={snake}", snake)
        .then()
            .statusCode(200)
            .body("size()", Matchers.greaterThan(0));
    }

    @Test
	public void testPostScore() {
        String scoreJson = "{\"snake\":\"anaconda\", \"score\":100}";

        given()
        .contentType("application/json")
        .body(scoreJson)
        .when()
            .post("http://localhost:8080/api/v1/score")
        .then()
            .statusCode(200)
            .body("score", Matchers.equalTo(100));
    }

    @Test
    public void testPostScoreBadRequest() {
        String scoreJson = "{\"snake\":\"test\", \"score\":100}";

        given()
        .contentType("application/json")
        .body(scoreJson)
        .when()
            .post("http://localhost:8080/api/v1/score")
        .then()
            .statusCode(400);
    }
}