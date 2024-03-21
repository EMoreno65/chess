package Request;

import spark.Request;

public class RegisterRequest extends Request {

  public RegisterRequest(String username, String password, String email) {
    this.username=username;
    this.password=password;
    this.email=email;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  String username;
  String password;
  String email;
}
