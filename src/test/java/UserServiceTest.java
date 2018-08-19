import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import dg.pegasus.User;
import dg.pegasus.UserNotFoundException;
import dg.pegasus.UserRepository;
import dg.pegasus.UserService;

/**
 * Class for all unit tests related to the UserService class.
 * @author gianninasd
 */
@RunWith(MockitoJUnitRunner.class)
 public class UserServiceTest {

  @InjectMocks
  UserService userService = new UserService();

  @Mock
  UserRepository userRepository;

  // test data
  private User sampleUser;

  @Before
  public void setup() {
    sampleUser = new User();
    sampleUser.setId("dd7262ad-f713-4d36-bcb3-fe9b5e75a74d");
  }

  @Test
  public void testLoad() {
    when(userRepository.findById("dd7262ad-f713-4d36-bcb3-fe9b5e75a74d")).thenReturn(Optional.of(sampleUser));

    User user = userService.load("dd7262ad-f713-4d36-bcb3-fe9b5e75a74d");
    assertNotNull( user );
    assertEquals("dd7262ad-f713-4d36-bcb3-fe9b5e75a74d", user.getId());
  }

  @Test(expected=UserNotFoundException.class)
  public void testNotFound() {
    assertNotNull( userService.load("000-destruct") );
  }

  @Test
  public void testCreate() {
    User dummyUser = new User();
    dummyUser.setName("Mary K.");
    dummyUser.setUserName("mary");
    dummyUser.setEmail("mary@gmail.com");
    dummyUser.setStatus("PENDING");
    dummyUser.setPassword("password");

    when(userRepository.save(dummyUser)).thenReturn(dummyUser);
    User user = userService.create(dummyUser);

    assertNotNull( user );
    assertNotNull(user.getId());
    assertEquals("API", user.getCreatedBy());
    assertEquals("API", user.getModifiedBy());
    assertEquals(dummyUser.getName(), user.getName());
    assertEquals(dummyUser.getEmail(), user.getEmail());
    assertNotNull( user.getCreationDate() );
    assertNotNull( user.getModificationDate() );
    assertNotNull( user.getPassword() );
    assertNotNull( user.getPasswordSalt() );
  }

  @Test
  public void testUpdate() {
    String userId = "dd7262ad-f713-4d36-bcb3-fe9b5e75a74d";

    User existingUser = new User();
    existingUser.setId(userId);
    existingUser.setName("Mary K.");
    existingUser.setUserName("mary");
    existingUser.setPassword("password");
    existingUser.setPasswordSalt("passwordSalt");
    existingUser.setEmail("mary@gmail.com");
    existingUser.setStatus("PENDING");
    existingUser.setModifiedBy("jimmy");

    when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

    // create date from 5 mins ago
    Calendar justBefore = Calendar.getInstance();
    justBefore.set(Calendar.MILLISECOND, 0);

    User user2Edit = new User();
    user2Edit.setName("mary22");
    user2Edit.setUserName("mary22");
    user2Edit.setEmail("mary22@gmail.com");
    user2Edit.setStatus("ENABLED");
    user2Edit.setModifiedBy("adminUser");

    when(userRepository.save(user2Edit)).thenReturn(user2Edit);

    User user = userService.update(userId, user2Edit);

    assertNotNull( user );
    assertEquals(userId, user.getId());
    assertEquals("adminUser", user.getModifiedBy());
    assertNotNull( user.getPassword() );
    assertNotNull( user.getPasswordSalt() );
    assertNotNull( user.getModificationDate() );
    assertTrue(user.getModificationDate().after(justBefore.getTime()));
  }
}