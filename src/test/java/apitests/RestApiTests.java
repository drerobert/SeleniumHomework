package apitests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestApiTests {
    /// CONSTANTS
    private static final Logger logger = LoggerFactory.getLogger(RestApiTests.class);

    /// TESTS
    /**
     * Case 5.
     * GET the users
     * Log only names and emails
     * Validate first user email has '@' symbol
     */
    @Test
    @DisplayName("Case 5: REST API GET Users and verify email")
    public void case5_verifyEmailFromGetUsersApi() {
        List<UserDTO> users = UsersApiClient.getUsers();

        users.forEach(user ->
                logger.info("Name:{}  Email:{}", user.getName(), user.getEmail())
        );

        assertTrue(
                users.get(0).getEmail().contains("@"),
                "First user email should contain @"
        );
    }
}
