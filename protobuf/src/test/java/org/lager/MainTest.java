package org.lager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void test () {
        String email = "j@baeldung.com";
        int id = new Random().nextInt();
        String name = "Michael Program";
        String number = "01234567890";
        AddressBookProtos.Person person =
                AddressBookProtos.Person.newBuilder()
                        .setId(id)
                        .setName(name)
                        .setEmail(email)
                        .addNumbers(number)
                        .build();

        System.out.println(person);

        assertEquals(person.getEmail(), email);
        assertEquals(person.getId(), id);
        assertEquals(person.getName(), name);
        assertEquals(person.getNumbers(0), number);
    }

    @Test
    @DisplayName("")
    void writeRead() throws IOException {
        //Given
        String filePath = "test.txt";
        String email = "j@baeldung.com";
        int id = new Random().nextInt();
        String name = "Michael Program";
        String number = "01234567890";
        AddressBookProtos.Person person =
                AddressBookProtos.Person.newBuilder()
                        .setId(id)
                        .setName(name)
                        .setEmail(email)
                        .addNumbers(number)
                        .build();

        AddressBookProtos.AddressBook addressBook
                = AddressBookProtos.AddressBook.newBuilder().addPeople(person).build();

        //When
        FileOutputStream fos = new FileOutputStream(filePath);
        addressBook.writeTo(fos);

        AddressBookProtos.AddressBook deserialized
                = AddressBookProtos.AddressBook.newBuilder()
                .mergeFrom(new FileInputStream(filePath)).build();

        //Then

        assertEquals(deserialized.getPeople(0).getEmail(), email);
        assertEquals(deserialized.getPeople(0).getId(), id);
        assertEquals(deserialized.getPeople(0).getName(), name);
        assertEquals(deserialized.getPeople(0).getNumbers(0), number);
    }
}