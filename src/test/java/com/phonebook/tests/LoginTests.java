package com.phonebook.tests;

import com.phoneBook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests  extends TestBase{

    @BeforeMethod
    public void ensurePrecondition(){
        //if login link is not present
        if (!app.getUser().isLoginLinkPresent()) {
            //click on Sign Out button
            app.getUser().clickOnSingOutButton();
        }
    }

    @Test
    public void loginPositiveTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("juri@mail.com")
                .setPassword("Qwerty007$"));

        app.getUser().clickOnLoginButton();

        //Assert Sigh Out is Present
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void loginNegativeTestWithOutEmail() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User().setPassword("Qwerty007$"));

        app.getUser().clickOnLoginButton();

        //Assert Sigh Out is Present
        Assert.assertTrue(app.getUser().isAlertAppears());
    }

}
