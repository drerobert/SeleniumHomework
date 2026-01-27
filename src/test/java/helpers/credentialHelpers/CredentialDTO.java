package helpers.credentialHelpers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CredentialDTO {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public CredentialDTO() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
