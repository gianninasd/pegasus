import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

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
}