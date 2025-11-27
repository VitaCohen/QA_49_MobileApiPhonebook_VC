package utils;

import dto.Contact;
import net.datafaker.Faker;
import org.checkerframework.checker.units.qual.C;

public class ContactFactory {

    static Faker faker = new Faker();

    public static void main(String[] args){
        System.out.println(positiveContact());
    }

    public  static Contact positiveContact(){
        return Contact.builder()
                .name(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .phone(faker.number().digits(14))
                .address(faker.address().fullAddress())
                .descriptions("it's my friend")
                .build();
    }



}
