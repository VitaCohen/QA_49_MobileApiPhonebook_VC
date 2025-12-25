package tests_mobile;

import io.restassured.response.Response;
import manager.AuthenticationController;
import models.Contact;
import models.ContactsDto;
import models.TokenDto;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.EditContactScreen;
import screens.ErrorScreen;
import utils.BaseApi;
import utils.ContactFactory;

import static manager.ContactController.getAllUserContacts;
import static utils.BaseApi.ADD_NEW_CONTACT;

public class TestsEditContact extends TestBase {
    AuthenticationScreen authenticationScreen;
    ContactListScreen contactListScreen;
    EditContactScreen editContactScreen;
    User user = new User("iv@mail.com", "123456Aa!");

    @BeforeMethod
    public void login() {
        authenticationScreen = new AuthenticationScreen(driver);
        User user = new User("iv@mail.com", "123456Aa!");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnLogin();
        contactListScreen = new ContactListScreen(driver);

    }

    @Test
    public void editContactPositiveTest() {
        contactListScreen.openContactMiddle();
        editContactScreen = new EditContactScreen(driver);
        editContactScreen.typeEditContactForm(ContactFactory.positiveContact());
        Assert.assertTrue(contactListScreen.validatePopUpMessage("Contact was updated!", 10));

    }

    private ContactsDto getAllUserContactsForValidate(User user) {
        TokenDto tokenDto = AuthenticationController.requestRegLogin(user, BaseApi.LOGIN)
                .as(TokenDto.class); // restAssured
        Response response = getAllUserContacts(ADD_NEW_CONTACT, tokenDto.getToken());
        System.out.println(response.getStatusLine());
        if (response.getStatusCode() == 200)
            return response.as(ContactsDto.class);
        else
            throw new IllegalArgumentException("Smth wrong" + response.statusLine());
    }

    @Test
    public void editContactPositiveTest_validateApi() {
        Contact contact = ContactFactory.positiveContact();
        contactListScreen.openContactMiddle();
        editContactScreen = new EditContactScreen(driver);
        editContactScreen.typeEditContactForm(contact);
        //Assert.assertTrue(contactListScreen.validatePopUpMessage("Contact was updated!", 10));
        ContactsDto contactsDto = getAllUserContactsForValidate(user);
        boolean flag = false;
        for (Contact contact1 : contactsDto.getContacts()) {
            if (contact1.equals(contact))
                flag = true;
        }
        Assert.assertTrue(flag);
    }


    @Test
    public void editContactNegativeTest_wrongLastName() {
        Contact contact = ContactFactory.positiveContact();
        contact.setLastName("");
        contactListScreen.openContactMiddle();
        editContactScreen = new EditContactScreen(driver);
        editContactScreen.typeEditContactForm(contact);
        Assert.assertTrue(new ErrorScreen(driver).validateErrorText("lastName=must not be blank", 10));
    }

    //HW_16
    @Test
    public void editFirstContactPositiveTest() {
        contactListScreen.editFirstContact();
        editContactScreen = new EditContactScreen(driver);
        String name = "UpdateName";
        editContactScreen.updateName(name);
        Assert.assertTrue(contactListScreen.validatePopUpMessage("Contact was updated!", 10));

    }

    @Test
    public void editMiddleContactPositiveTest() {
        contactListScreen.editMiddleContact();
        editContactScreen = new EditContactScreen(driver);
        String lastName = "UpdateLastName";
        editContactScreen.updateLastName(lastName);
        Assert.assertTrue(contactListScreen.validatePopUpMessage("Contact was updated!", 10));

    }


}
