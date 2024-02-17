import org.junit.*;
import static org.junit.Assert.*;


public class ArgsValidationTest {
     public String path = "../Tests/cachedir/"; // Replace this path with the valid path to the cache directory

     @Test
     public void testCheckArgsValid() {
          String[] args = {path, "random"};
          assertTrue("Args should be valid", ArgsValidation.lengthChecker(args));
     }

     @Test
     public void testCheckArgsInvalidLength() {
          String[] args = {path};
          assertFalse("Args should be invalid due to incorrect length", ArgsValidation.checkArgs(args));
     }

     @Test
     public void testCheckArgsInvalidMode() {
          String[] args = {path, "invalid"};
          assertFalse("Args should be invalid due to incorrect mode", ArgsValidation.checkArgs(args));
     }
}
