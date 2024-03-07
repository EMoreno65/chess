package dataAccess;

import model.AuthData;
import model.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class AuthSQLDAO implements AuthDAO{

//  public AuthSQLDAO() throws DataAccessException, ResponseException {
//    configureDatabase();
//  }
  @Override
  public AuthData createAuth(UserData user) throws DataAccessException {
    String newToken = generateToken();
    String sql = "INSERT INTO auth_data (auth_token, username, password) VALUES (?, ?, ?)";

    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setString(1, newToken);
      preparedStatement.setString(2, user.getUsername());
      preparedStatement.setString(3, user.getPassword());
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      throw new DataAccessException("Error creating authentication data");
    }
    return new AuthData(newToken, user.getUsername(), user.getPassword());
  }

  @Override
  public AuthData getAuthData(String authToken) throws DataAccessException {

  }

  @Override
  public boolean isValidToken(String authToken) throws DataAccessException {
    return false;
  }

  @Override
  public void deleteAuth(String authToken) throws DataAccessException {

  }

  @Override
  public boolean isUserAuthenticated(String authToken) {
    return false;
  }

  @Override
  public String findUserFromAuthToken(String authToken) {
    return null;
  }

  // Method to generate a random token
  private String generateToken() {
    return UUID.randomUUID().toString();
  }

  @Override
  public void clearAll() {

  }

  private final String[] createStatements = {
          """
            CREATE TABLE IF NOT EXISTS auth_data (
                auth_token VARCHAR(40) NOT NULL,
                username VARCHAR(40) NOT NULL,
                password VARCHAR(40) NOT NULL,
                PRIMARY KEY (auth_token)
            );
            """
  };

  private void configureDatabase() throws ResponseException, DataAccessException {
    DatabaseManager.createDatabase();
    try (var conn = DatabaseManager.getConnection()) {
      for (var statement : createStatements) {
        try (var preparedStatement = conn.prepareStatement(statement)) {
          preparedStatement.executeUpdate();
        }
      }
    } catch (SQLException ex) {
      throw new ResponseException(500, String.format("Unable to configure database: %s", ex.getMessage()));
    }
  }
  // Table of AuthData
  // Create Database
  // Make table exist
  // Plus other responsibilities of DAO

}
