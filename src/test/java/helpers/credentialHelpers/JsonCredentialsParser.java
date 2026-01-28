package helpers.credentialHelpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.util.List;

public class JsonCredentialsParser {

    private static final Logger logger = LoggerFactory.getLogger(JsonCredentialsParser.class);

    //Cache credentials to load once, not at every test
    private static volatile List<CredentialDTO> cachedCredentials;

    private JsonCredentialsParser() {
    }

    public static CredentialDTO getCredential(String targetUsername) {
        return getCachedCredentials().stream()
                .filter(c -> c.getUsername().equals(targetUsername))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found: " + targetUsername));
    }

    // The method tests actually call
    public static List<CredentialDTO> getCachedCredentials() {
        // Only load the file if we haven't done it yet.
        if (cachedCredentials == null) {
            logger.info("First time access: Parsing JSON...");
            cachedCredentials = loadCredentials();
        }
        return cachedCredentials;
    }

    private static List<CredentialDTO> loadCredentials() {
        logger.info("Loading credentials from JSON...");
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream is = JsonCredentialsParser.class
                    .getClassLoader()
                    .getResourceAsStream("credential.json");

            if (is == null) {
                throw new IllegalStateException("credential.json not found");
            }

            CredentialDTO[] credentials =
                    mapper.readValue(is, CredentialDTO[].class);

            List<CredentialDTO> credentialList = List.of(credentials);

            logger.info("Credentials loaded successfully ({} users)", credentialList.size());
            return credentialList;

        } catch (Exception e) {
            throw new RuntimeException("Unable to load credential.json", e);
        }
    }
}