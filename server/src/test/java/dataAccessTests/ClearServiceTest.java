package dataAccessTests;

import dataAccess.*;
import org.junit.jupiter.api.Test;
import service.ClearService;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTest {

  @Test
  public void testClearAll() throws DataAccessException, SQLException {
    // Arrange: Set up your test environment
    ClearService clearService = new ClearService();

    clearService.clearAll(new MemoryUserDAO(), new MemoryAuthDAO(), new MemoryGameDAO());

    assertTrue(clearService.gameAccess.listGames().isEmpty(), "Game database should be empty");
  }
}
