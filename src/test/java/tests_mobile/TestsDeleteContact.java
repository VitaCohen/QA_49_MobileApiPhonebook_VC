package tests_mobile;

import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import utils.android_utils.SwipeUtils;
import utils.enums.Direction;

public class TestsDeleteContact extends TestBase {
    AuthenticationScreen authenticationScreen;
    ContactListScreen contactListScreen;

    @BeforeMethod
    public void login() {
        authenticationScreen = new AuthenticationScreen(driver);
        User user = new User("iv@mail.com", "123456Aa!");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnLogin();
        contactListScreen = new ContactListScreen(driver);

    }

    @Test
    public void deleteContactTest() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SwipeUtils.swipeScreen(driver, Direction.RIGHT);
    }

}
