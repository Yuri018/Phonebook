package com.phonebook.tests;

import com.phoneBook.models.Contact;
import com.phoneBook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSingOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("juri@mail.com")
                .setPassword("Qwerty007$"));
        app.getUser().clickOnLoginButton();

        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName("Smidt")
                .setLastName("Georg")
                .setPhone("12345678901")
                .setEmail("smidt@mail.com")
                .setAddress("Berlin")
                .setDescription("user"));
        app.getContact().clickOnSaveButton();
    }

    @Test
    public void removeContactTest() {

        int sizeBefore = app.getContact().sizeOfContacts();
        app.getContact().removeContact();
        app.getContact().pause(1000);
        int sizeAfter = app.getContact().sizeOfContacts();
        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }


}
