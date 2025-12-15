package tests_api;

import io.restassured.response.Response;
import manager.AuthenticationController;
import manager.ContactController;
import models.Contact;
import models.ResponseMessageDto;
import models.TokenDto;
import models.User;
import org.openqa.selenium.remote.tracing.opentelemetry.SeleniumSpanExporter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ContactFactory;

import static utils.PropertiesReader.*;

public class TestAddNewContact extends ContactController {


    TokenDto tokenDto;


    @BeforeClass
    public void login() {
        User user = new User(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        Response response = AuthenticationController.requestRegLogin(user, LOGIN);
        if (response.getStatusCode() == 200)
            tokenDto = response.body().as(TokenDto.class);
       else
            throw new IllegalArgumentException("Login status code = " + response.getStatusCode());

    }

@Test
    public  void  addNweContactPositiveTest(){
    System.out.println(tokenDto.toString());
    Contact contact = ContactFactory.positiveContact();
    Response response = requestAddNewContact(contact, ADD_NEW_CONTACT, tokenDto.getToken());
    System.out.println(response.getStatusLine());
    ResponseMessageDto responseMessageDto = response.body().as(ResponseMessageDto.class);
    System.out.println(responseMessageDto.getMessage());
    Assert.assertEquals(response.getStatusCode(), 200);
}


}
