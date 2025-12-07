package tests_mobile;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.ErrorScreen;
import screens.SplashScreen;

import static utils.UserFactory.*;

public class TestsRegistration extends TestBase {
    AuthenticationScreen authenticationScreen;

    @BeforeMethod
    public void goToAuthenticationScreen() {
        new SplashScreen(driver);
        authenticationScreen = new AuthenticationScreen(driver);

    }

    @Test
    public void testRegistrationPositive() {
        User user = positiveUser();
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnRegistration();
        Assert.assertTrue(new ContactListScreen(driver)
                .validateContactListScreenOpenAfterRegistration("No Contacts. Add One more!", 10));
    }

    @Test
    public void testRegistrationNegative_emptyEmail() {
        User user = positiveUser();
        user.setUsername("");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorText("username=must not be blank", 10));
    }

//HW_15

    //@@
    @Test
    public void testRegistrationNegative_wrongEmail() {
        User user = positiveUser();
        user.setUsername("asq@@m.com");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorText("username=must be a well-formed email address", 10));
    }

    // 7 char
    @Test
    public void testRegistrationNegative_wrongPassword() {
        User user = positiveUser();
        user.setPassword("1234Aa!");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorText("password= At least 8 characters;", 10));
    }

    // WO letters
    @Test
    public void testRegistrationNegative_wrongPassword_WO_Letters() {
        User user = positiveUser();
        user.setPassword("1234567!");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorText("password= At least 8 characters;", 10));
    }

}
