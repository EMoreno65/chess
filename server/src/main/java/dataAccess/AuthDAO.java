package dataAccess;

import model.AuthData;
import model.UserData;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AuthDAO {
  public AuthDAO() {
  // create
  //read
  //update
  //delete

}
  private static ArrayList<AuthData> myTokens = new ArrayList<>();

  public AuthData createAuth(UserData user) throws DataAccessException {
    String newToken = user + "/" + LocalDateTime.now();
    AuthData myToken = new AuthData(newToken, user.getUsername());
    if (!myTokens.contains(myToken)){
      myTokens.add(myToken);
      return myToken;
    }
    else{
      throw new DataAccessException("Token already exists");
    }
  }

  public void clearAll() {
    myTokens = new ArrayList<>();
    myTokens.clear();
  }
}
