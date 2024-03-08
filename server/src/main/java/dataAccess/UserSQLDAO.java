package dataAccess;

import model.AuthData;
import model.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSQLDAO implements UserDAO{

  public UserSQLDAO() throws DataAccessException{
    configureDatabase();
  }
  // Table of UserData
  // convert to SQL using executeUpdate?
  public void createUser(UserData user) throws DataAccessException {
    String checkSql = "SELECT COUNT(*) FROM user_data WHERE username = ?";
    String insertSql = "INSERT INTO user_data (username, password, email) VALUES (?, ?, ?)";

    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement checkStatement = conn.prepareStatement(checkSql);
         PreparedStatement insertStatement = conn.prepareStatement(insertSql)) {
      checkStatement.setString(1, user.getUsername());
      ResultSet resultSet = checkStatement.executeQuery();
      resultSet.next();
      int count = resultSet.getInt(1);
      if (count > 0) {
        throw new DataAccessException("Error: Username already exists");
      }

      // Insert new user
      insertStatement.setString(1, user.getUsername());
      insertStatement.setString(2, user.getPassword());
      insertStatement.setString(3, user.getEmail());
      insertStatement.executeUpdate();
    } catch (SQLException ex) {
      throw new DataAccessException("Error: " + ex.getMessage());
    }
  }

  public UserData getUser(String username) throws DataAccessException {
    String sql = "SELECT username, password, email FROM user_data WHERE username = ?";
    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setString(1, username);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          String password = resultSet.getString("password");
          String email = resultSet.getString("email");
          return new UserData(username, password, email);
        }
      }
    } catch (SQLException ex) {
      throw new DataAccessException("Error retrieving authentication data");
    }
    return null; // Return null if no authentication data found for the provided token
  }

  public String getPassword(String username) throws DataAccessException{
    String sql="SELECT password FROM user_data WHERE username = '?'";
    try (Connection conn=DatabaseManager.getConnection();
         PreparedStatement preparedStatement=conn.prepareStatement(sql)) {
      preparedStatement.setString(2, username);
      try (ResultSet resultSet=preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          return resultSet.getString("password");
        }
      }
    } catch (SQLException ex) {
      throw new DataAccessException("Error finding password based off username");
    }
    return null;
  }

  public boolean verifyPassword(UserData user, String providedPassword) throws DataAccessException {
    String username = user.getUsername();
    String sql = "SELECT password FROM user_data WHERE username = ?";
    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setString(1, username);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          String storedPassword = resultSet.getString("password");
          return storedPassword.equals(providedPassword);
        }
      }
    } catch (SQLException ex) {
      throw new DataAccessException("Error verifying password");
    }
    return false;
  }



  // Write get Password method that does the same thing as get User


  @Override
  public void clearAll() throws DataAccessException {
    String sql = "TRUNCATE TABLE user_data";
    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      throw new DataAccessException("Error clearing authentication data");
    }
  }

  private final String[] createStatements = {
          """
            CREATE TABLE IF NOT EXISTS user_data (
                username VARCHAR(100) NOT NULL,
                password VARCHAR(100) NOT NULL,
                email VARCHAR(100) NOT NULL,
                PRIMARY KEY (username)
                     
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
}
