package serverTests;

import dataAccess.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import service.ClearService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTest {

  @Test
  public void testClearAll() throws DataAccessException, SQLException {
    // Arrange: Set up your test environment
    ClearService clearService = new ClearService();

    // Act: Call the clearAll method
    clearService.clearAll(new MemoryUserDAO(), new MemoryAuthDAO(), new MemoryGameDAO());

    //
    assertTrue(clearService.gameAccess.listGames().isEmpty(), "Game database should be empty");
//    assertTrue(clearService.userAccess.listGames().isEmpty(), "Game database should be empty");
//    assertTrue(clearService.userAccess.listGames().isEmpty(), "Game database should be empty");
  }
}
