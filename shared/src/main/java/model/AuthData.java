package model;

public record AuthData(String authToken, String username, String password) {
  public AuthData(String authToken, String username, String password) {
    this.authToken = authToken;
    this.username=username;
    this.password = password;
  }
  public String getAuthToken() {
    return authToken;
  }

  public String getusername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
