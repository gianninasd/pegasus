import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;
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

    when(userRepository.save(dummyUser)).thenReturn(fullUser());
    User user = userService.create(dummyUser);

    assertNotNull( user );
    assertEquals("dd7262ad-f713-4d36-bcb3-fe9b5e75a74d", user.getId());
    assertEquals("API", user.getCreatedBy());
    assertEquals("API", user.getModifiedBy());
    assertEquals(dummyUser.getName(), user.getName());
    assertEquals(dummyUser.getEmail(), user.getEmail());
    assertNotNull( user.getCreationDate() );
    assertNotNull( user.getModificationDate() );
    assertNotNull( user.getPassword() );
    assertNotNull( user.getPasswordSalt() );
  }

  /**
   * Returns a fully created user object.
   */
  private User fullUser() {
    User user = new User();
    user.setName("Mary K.");
    user.setUserName("mary");
    user.setEmail("mary@gmail.com");
    user.setStatus("PENDING");

    user.setId("dd7262ad-f713-4d36-bcb3-fe9b5e75a74d");
    user.setCreatedBy("API");
    user.setCreationDate(new Date());
    user.setModifiedBy("API");
    user.setModificationDate(new Date());

    user.setPassword("password");
    user.setPasswordSalt("salt");

    return user; 
  }
}