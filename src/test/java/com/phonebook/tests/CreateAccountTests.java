package com.phonebook.tests;

import com.phoneBook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase{


    @BeforeMethod
    public void ensurePrecondition(){
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSingOutButton();
        }
    }

    @Test
    public void createExistedAccountNegativeTest(){

        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("juri@mail.com")
                .setPassword("Qwerty007$"));
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isAlertAppears());
    }

}
