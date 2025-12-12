package tests_mobile;

import models.User;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import utils.android_utils.SwipeUtils;
import utils.enums.Direction;

import java.time.Duration;

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
        contactListScreen.deleteContactMiddle();

    }

    @Test
    public void deleteFirstContactTest() {
        contactListScreen.deleteFirstContact();

    }



}
