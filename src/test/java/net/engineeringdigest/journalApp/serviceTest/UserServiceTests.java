package net.engineeringdigest.journalApp.serviceTest;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Disabled
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,4,6",
            "2,5,8"
    })
    public void testSum(int a, int b, int result){
        Assertions.assertEquals(result, a + b);
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveUser(User user){
        assertTrue(userService.saveNewUser(user));
    }
}
