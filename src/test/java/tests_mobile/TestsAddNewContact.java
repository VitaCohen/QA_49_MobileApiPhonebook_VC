package tests_mobile;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.ErrorScreen;
import utils.ContactFactory;

public class TestsAddNewContact extends  TestBase{
    AuthenticationScreen authenticationScreen;
    ContactListScreen contactListScreen;
    AddNewContactScreen addNewContactScreen;

    @BeforeMethod
    public void login(){
        authenticationScreen = new AuthenticationScreen(driver);
        User user = new User("iv@mail.com", "123456Aa!");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnLogin();
        contactListScreen = new ContactListScreen(driver);
        contactListScreen.clickBtnAdd();
        addNewContactScreen = new AddNewContactScreen(driver);

    }


    @Test
    public  void addNewContactPositiveTest(){
        Contact contact = ContactFactory.positiveContact();
        addNewContactScreen.typeContact(contact);
        addNewContactScreen.clickBtnCreateContact();
        Assert.assertTrue(contactListScreen.validatePopUpMessage("Contact was added", 10));


    }

    @Test
    public  void addNewContactNegativeTest_emptyLastName(){
        Contact contact = ContactFactory.positiveContact();
        contact.setLastName("");
        addNewContactScreen.typeContact(contact);
        addNewContactScreen.clickBtnCreateContact();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorText("lastName=must not be blank", 10));


    }



}
