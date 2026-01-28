package apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UsersApiClient {

    private static final Logger logger = LoggerFactory.getLogger(UsersApiClient.class);

    private static final String BASE_URL =
            "https://jsonplaceholder.typicode.com";

    public static List<UserDTO> getUsers() {

        logger.info("Calling GET /users");

        Response response = RestAssured
                .given()
                .baseUri(BASE_URL)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.jsonPath().getList("", UserDTO.class);
    }
}
