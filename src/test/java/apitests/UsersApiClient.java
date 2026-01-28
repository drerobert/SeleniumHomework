package apitests;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UsersApiClient {

    /// CONSTANTS
    private static final Logger logger = LoggerFactory.getLogger(UsersApiClient.class);
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String USERS_ENDPOINT = "/users";

    private static final OkHttpClient client;
    private static final ObjectMapper objectMapper;

    //Load only once
    static {
        //add timeouts if for some reason the service is slow
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(5, 5, TimeUnit.MINUTES)) // Parallel safe pool
                .build();


        objectMapper = new ObjectMapper();
    }

    public static List<UserDTO> getUsers() {
        logger.info("Getting users from api endpoint: {}",USERS_ENDPOINT);
        String url = BASE_URL + USERS_ENDPOINT;

        Request request = new Request.Builder()
                .url(url)
                .build();

        // execute() is thread-safe.
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("API error: " + response.code());
            }

            assert response.body() != null;
            String responseBody = response.body().string();
            UserDTO[] usersArray = objectMapper.readValue(responseBody, UserDTO[].class);

            logger.info("Users loaded!");

            return Arrays.asList(usersArray);
        } catch (IOException e) {
            throw new RuntimeException("Network error during /users call", e);
        }
    }
}
