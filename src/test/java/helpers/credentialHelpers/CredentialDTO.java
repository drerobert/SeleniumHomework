package helpers.credentialHelpers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CredentialDTO {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public CredentialDTO() {}

    public CredentialDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
