package com.phonebook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase{


    @BeforeMethod
    public void ensurePrecondition(){
        //if login link is not present
        if (!isElementPresent(By.cssSelector("[href='/login']"))) {
            //click on Sign Out button
            click(By.xpath("//button[.='Sign Out']"));
        }
    }

//    @Test
//    public void createNewAccountPositiveTest(){
//
//        //click onLogin link
//        click(By.cssSelector("[href='/login']"));
//
//        //enter email
//        type(By.name("email"), "juri@mail.com");
//
//        //enter password
//        type(By.name("password"), "Qwerty007$");
//
//        //click on the Registration button
//        click(By.name("registration"));
//
//        //assert Sing Out button is present
//        Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']")));
//        //точка после открывающей квадратной скобки = методу text()
//
//    }

    @Test
    public void createExistedAccountNegativeTest(){

        //click onLogin link
        click(By.cssSelector("[href='/login']"));

        //enter email
        type(By.name("email"), "juri@mail.com");

        //enter password
        type(By.name("password"), "Qwerty007$");

        //click on the Registration button
        click(By.name("registration"));
        Assert.assertTrue(isAlertAppears());
    }

}
