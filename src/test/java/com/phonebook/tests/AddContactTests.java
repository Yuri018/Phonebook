package com.phonebook.tests;

import com.phoneBook.models.Contact;
import com.phoneBook.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase{
    //precondition
    @BeforeMethod
    public void precondition(){
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSingOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("juri@mail.com")
                .setPassword("Qwerty007$"));
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addContactPositiveTests(){
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(
                new Contact()
                        .setName("Smidt")
                        .setLastName("Georg")
                        .setPhone("12345678901")
                        .setEmail("smidt@mail.com")
                        .setAddress("Berlin")
                        .setDescription("user"));

        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreateByText("Smidt"));
    }

    @AfterMethod
   public void postCondition(){
        app.getContact().removeContact();
    }

    @DataProvider
    public Iterator<Object[]>addContact(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"oliver", "Kan", "12345678902", "kan@mail.com", "Berlin", "User"});
        list.add(new Object[]{"oliver1", "Kan", "12345678902", "kan1@mail.com", "Berlin", "User"});
        list.add(new Object[]{"oliver2", "Kan", "12345678902", "kan2@mail.com", "Berlin", "User"});
        return list.iterator();
    }

    @Test(dataProvider = "addContact")
    public void addContactPositiveTestFromDataProvider(String name, String lastName, String phone, String email
    , String address, String desc){
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(
                new Contact()
                        .setName(name)
                        .setLastName(lastName)
                        .setPhone(phone)
                        .setEmail(email)
                        .setAddress(address)
                        .setDescription(desc));

        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreateByText(name));
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

    @Test(dataProvider = "addContactFromCSV")
    public void addContactPositiveTestFromDataProviderWithFile(Contact contact){
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(contact);

        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreateByText(contact.getName()));
    }

}
