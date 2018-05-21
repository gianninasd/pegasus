import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import dg.pegasus.PasswordUtils;

/**
 * Class for all unit tests related to Password generation.
 * @author gianninasd
 */
public class PasswordUtilsTest {

  @Test
  public void testPassword() throws Exception {
    String pwd = PasswordUtils.hashN("clearPassword", "yzfcpZO0L1OZELrWpkSvAuU14iekE5");
    //System.out.println("1>> " + pwd);

    assertNotNull(pwd);
    assertEquals("Yzg4NWY3MmQxNDQ2YmNjMDAyNTZlOWEyZDgwNWY2NDdkOWU1MTBmOTI4N2RiYTNkMzM0MmYzNjk4ZDJhOWE1OQ==", pwd);
  }
}