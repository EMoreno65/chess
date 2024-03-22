package model.Request;

import model.GameData;

import java.util.List;

public class ListRequest {
  public ListRequest(String authToken) {
    this.authToken=authToken;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken=authToken;
  }
  String authToken;

  public String getErrorMessage() {
    return errorMessage;
  }

  String errorMessage;
}
