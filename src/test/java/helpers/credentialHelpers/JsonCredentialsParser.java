package helpers.credentialHelpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonCredentialsParser {
    private static final Logger logger = LoggerFactory.getLogger(JsonCredentialsParser.class);

    private JsonCredentialsParser() {}

    public static CredentialDTO parseJson() {
        logger.info("Parsing json...");
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream is = JsonCredentialsParser.class
                    .getClassLoader()
                    .getResourceAsStream("credential.json");

            if (is == null) {
                throw new IllegalStateException("credential.json not found");
            }

            return mapper.readValue(is, CredentialDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Unable to parse credential.json", e);
        }
    }
}
