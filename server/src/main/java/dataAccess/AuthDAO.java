package dataAccess;

import model.AuthData;
import model.UserData;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public interface AuthDAO {

  public AuthData createAuth(UserData user) throws DataAccessException;

  public AuthData getAuthData(String authToken) throws DataAccessException;

  public boolean isValidToken(String authToken) throws DataAccessException;

  public void deleteAuth(String authToken) throws DataAccessException;

  public boolean isUserAuthenticated(String authToken) throws DataAccessException;

  public String findUserFromAuthToken(String authToken) throws DataAccessException;

  public void clearAll() throws DataAccessException;
}

// Determine what table should look like, write code for how to create the table in the DatabaseManagerClass in createDatabase method
// Create new DAOS, userSQL, return same result but should be getting it out of a database, find where I'm creating DAOs and change them to the new DAOS. Create database and the DAO classes should do the same thing but take in input from the database and returns it somewhere else
