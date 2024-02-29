package com.phonebook.tests;

import com.phoneBook.models.Contact;
import com.phoneBook.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

}
