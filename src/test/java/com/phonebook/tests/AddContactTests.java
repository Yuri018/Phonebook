package com.phonebook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AddContactTests extends TestBase{
    //precondition
    @BeforeMethod
    public void precondition(){
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
    }


    @Test
    public void addContactPositiveTests(){
        //click ADD link
        click(By.cssSelector("[href='/add']"));
        //enter Name
        type(By.cssSelector("input:nth-child(1)"),"Smidt");
        //enter last name
        type(By.cssSelector("input:nth-child(2)"),"Georg");
        //enter phone
        type(By.cssSelector("input:nth-child(3)"),"12345678901");
        //enter email
        type(By.cssSelector("input:nth-child(4)"),"smidt@mail.com");
        //enter address
        type(By.cssSelector("input:nth-child(5)"),"Berlin");
        //enter description
        type(By.cssSelector("input:nth-child(6)"),"user");
        //click Save button
        click(By.cssSelector(".add_form__2rsm2 button"));
        //assert Contact is added by text
        Assert.assertTrue(isContactCreateByText("Smidt"));
    }

   @AfterMethod
   public void postCondition(){
        click(By.cssSelector(".contact-item_card__2SOIM"));

        click(By.xpath("//button[.='Remove']"));
   }

    public boolean isContactCreateByText(String text) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
        for (WebElement element: contacts){
            if (element.getText().contains(text)){
                return true;
            }
        }
        return false;
    }

}
