package com.phonebook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase {
    //precondition
    @BeforeMethod
    public void precondition() {
        if (!isElementPresent(By.cssSelector("[href='/login']"))) {
            //click on Sign Out button
            click(By.xpath("//button[.='Sign Out']"));
        }
        //click on Login link
        click(By.cssSelector("[href='/login']"));
        //enter email
        type(By.name("email"), "juri@mail.com");
        //enter password
        type(By.name("password"), "Qwerty007$");
        //click on the Login button
        click(By.name("login"));

        //add contact
        //click ADD link
        click(By.cssSelector("[href='/add']"));
        //enter Name
        type(By.cssSelector("input:nth-child(1)"), "Smidt");
        //enter last name
        type(By.cssSelector("input:nth-child(2)"), "Georg");
        //enter phone
        type(By.cssSelector("input:nth-child(3)"), "12345678901");
        //enter email
        type(By.cssSelector("input:nth-child(4)"), "smidt@mail.com");
        //enter address
        type(By.cssSelector("input:nth-child(5)"), "Berlin");
        //enter description
        type(By.cssSelector("input:nth-child(6)"), "user");
        //click Save button
        click(By.cssSelector(".add_form__2rsm2 button"));
    }

    @Test
    public void removeContactTest() {

        int sizeBefore = sizeOfContacts();
        //click on the card
        click(By.cssSelector(".contact-item_card__2SOIM"));

        click(By.xpath("//button[.='Remove']"));

        pause(1000);

        int sizeAfter = sizeOfContacts();

        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }

    public int sizeOfContacts() {
        if (isElementPresent(By.cssSelector(".contact-item_card__2SOIM"))) {
            return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        }
        return 0;
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }


}
