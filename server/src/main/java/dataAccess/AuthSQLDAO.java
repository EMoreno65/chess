package dataAccess;

import model.AuthData;
import model.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AuthSQLDAO implements AuthDAO{

  public AuthSQLDAO() throws DataAccessException{
    configureDatabase();
  }
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
    String sql = "SELECT auth_token, username, password FROM auth_data WHERE auth_token = ?";
    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setString(1, authToken);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          String username = resultSet.getString("username");
          String password = resultSet.getString("password");
          return new AuthData(authToken, username, password);
        }
      }
    } catch (SQLException ex) {
      throw new DataAccessException("Error retrieving authentication data");
    }
    return null; // Return null if no authentication data found for the provided token
  }

  @Override
  public boolean isValidToken(String authToken) throws DataAccessException {
    String sql = "SELECT COUNT(*) FROM auth_data WHERE auth_token = ?";
    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setString(1, authToken);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          int count = resultSet.getInt(1);
          return count > 0;
        }
      }
    } catch (SQLException ex) {
      throw new DataAccessException("Error checking token validity");
    }
    return false;
  }

  @Override
  public void deleteAuth(String authToken) throws DataAccessException {
    String sql = "DELETE FROM auth_data WHERE auth_token = ?";
    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setString(1, authToken);
      int rowsAffected = preparedStatement.executeUpdate();
      if (rowsAffected == 0) {
        throw new DataAccessException("Token not found: " + authToken);
      }
    } catch (SQLException ex) {
      throw new DataAccessException("Error deleting authentication data");
    }
  }


  @Override
  public boolean isUserAuthenticated(String authToken) throws DataAccessException {
    String sql = "SELECT COUNT(*) FROM auth_data WHERE auth_token = ?";
    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setString(1, authToken);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          int count = resultSet.getInt(1);
          return count > 0;
        }
      }
    } catch (SQLException ex) {
      throw new DataAccessException("Error checking user authentication");
    }
    return false;
  }

  @Override
  public String findUserFromAuthToken(String authToken) throws DataAccessException {
    String sql = "SELECT username FROM auth_data WHERE auth_token = ?";
    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setString(1, authToken);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          return resultSet.getString("username");
        }
      }
    } catch (SQLException ex) {
      throw new DataAccessException("Error finding user from authentication token");
    }
    return null;
  }


  // Method to generate a random token
  public String generateToken() {
    return UUID.randomUUID().toString();
  }

  @Override
  public void clearAll() throws DataAccessException {
    String sql = "DELETE FROM auth_data";
    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      throw new DataAccessException("Error clearing authentication data");
    }
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

  private void configureDatabase() throws DataAccessException {
    DatabaseManager.createDatabase();
    try (var conn = DatabaseManager.getConnection()) {
      for (var statement : createStatements) {
        try (var preparedStatement = conn.prepareStatement(statement)) {
          preparedStatement.executeUpdate();
        }
      }
    } catch (SQLException ex) {
      throw new DataAccessException(String.format("Unable to configure database: %s", ex.getMessage()));
    }
  }
  // Table of AuthData
  // Create Database
  // Make table exist
  // Plus other responsibilities of DAO

}
