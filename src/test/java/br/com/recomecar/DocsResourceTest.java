package br.com.recomecar;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.containsString;

import static io.restassured.RestAssured.given;

@QuarkusTest
class DocsResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(containsString("Projeto: Bilhete Fácil"));
    }

}