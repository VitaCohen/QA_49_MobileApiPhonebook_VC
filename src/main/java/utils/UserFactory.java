package utils;

import models.User;
import net.datafaker.Faker;

public class UserFactory {

    static Faker faker = new Faker();

    public static User positiveUser(){
        User user=new User(faker.internet().emailAddress(), "123456Qq!");
        return user;
    }


}
