package serviceTests;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import org.junit.jupiter.api.Test;
import service.ClearService;
import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTest {

  @Test
  public void testClearAll() {
    // Arrange: Set up your test environment
    ClearService clearService = new ClearService();

    clearService.clearAll(new UserDAO(), new AuthDAO(), new GameDAO());

    assertTrue(clearService.gameAccess.listGames().isEmpty(), "Game database should be empty");
  }
}
