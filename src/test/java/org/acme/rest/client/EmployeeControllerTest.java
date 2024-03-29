package org.acme.rest.client;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class EmployeeControllerTest {

    @Test
    public void getEmployeeEndpoint() {
        given()
                .param("page", "1")
                .param("limit", "10")
                .param("sortBy", "id")
                .when().get("/employees")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json");
    }

}
