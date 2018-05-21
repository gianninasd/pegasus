package dg.pegasus;

import java.security.MessageDigest;
import java.util.Base64;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility methods for working with hashing of passwords.
 * @author gianninasd
 */
public class PasswordUtils {

  private final static String ALGORITHM = "SHA-256";
  private final static int iterations = 1005;

  /**
   * Generates a random salt.
   */
  public static String generateSalt() {
    return RandomStringUtils.randomAlphanumeric(30);
  }

  /**
   * Given a value and salt, will return Base64 encoded hashed string that was hashed N times
   * @value the string to hash
   * @salt the salt to apply
   */
  public static String hashN( String value, String salt ) throws Exception {
    String hashValue = hash(value, salt);

    for( int i = 0; i < iterations; i++ ) {
      hashValue = hash( hashValue, salt );
    }

    return hashValue;
  }

  /**
   * Given a value and salt, will return Base64 encoded hashed string
   * @value the string to hash
   * @salt the salt to apply
   */
  public static String hash( String value, String salt ) throws Exception {
    MessageDigest md = MessageDigest.getInstance(ALGORITHM);
    md.update(salt.getBytes());
    byte[] bytes = md.digest(value.getBytes());

    // convert it to hexadecimal format
    StringBuilder sb = new StringBuilder();
    for( int i = 0; i < bytes.length; i++ ) {
      sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    }

    return Base64.getEncoder().encodeToString(sb.toString().getBytes());
  }
}