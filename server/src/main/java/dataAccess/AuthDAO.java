package dataAccess;

public class AuthDAO {
  public AuthDAO(String authToken, String username) {
    this.authToken=authToken;
    this.username=username;
  }

  public String getAuthToken() {
    return authToken;
  }

  public String getUsername() {
    return username;
  }

  public void setAuthToken(String authToken) {
    this.authToken=authToken;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  String authToken;
  String username;



  // create
  //read
  //update
  //delete

}
