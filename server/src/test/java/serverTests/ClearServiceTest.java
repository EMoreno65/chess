package serverTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import service.ClearService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTest {

  @Test
  public void testClearAll() {
    // Arrange: Set up your test environment
    ClearService clearService = new ClearService();

    // Act: Call the clearAll method
    clearService.clearAll();

    //
    assertTrue(clearService.gameAccess.listGames().isEmpty(), "Game database should be empty");
  }
}
