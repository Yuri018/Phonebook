package com.phoneBook.fw;

import com.phoneBook.models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider
    public Iterator<Object[]>addContact(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"oliver", "Kan", "12345678902", "kan@mail.com", "Berlin", "User"});
        list.add(new Object[]{"oliver1", "Kan", "12345678902", "kan1@mail.com", "Berlin", "User"});
        list.add(new Object[]{"oliver2", "Kan", "12345678902", "kan2@mail.com", "Berlin", "User"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addContactFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));
        String line = reader.readLine();

        while (line != null) {

            String[] split = line.split(",");

            list.add(new Object[]{new Contact().setName(split[0]).setLastName(split[1]).setPhone(split[2])
                    .setEmail(split[3]).setAddress(split[4]).setDescription(split[5])});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
