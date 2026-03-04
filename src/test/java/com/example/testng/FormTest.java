package com.example.testng;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.pom.FormPom;
import com.example.utils.Driver;

public class FormTest {

    private WebDriver driver;

    private static final String URL = "https://demoqa.com/";

    private static final String FIRST_NAME = "Tcaci";
    private static final String LAST_NAME = "Valentin";
    private static final String EMAIL = "valentintcaci2@gmail.com";
    private static final String GENDER = "Male";
    private static final String NUMBER = "0605889555";
    private static final String DATE = "26 Apr 2006";
    private static final String SUBJECT = "Maths";
    private static final String HOBBY = "Sports";
    private static final String STATE = "Rajasthan";
    private static final String CITY = "Jaipur";

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
         driver = Driver.getLocalDriver();
//        driver = Driver.getRemoteDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void formTest() {

        System.out.println("\n--- START TEST ---\n");

        driver.get(URL);

        FormPom formPom = new FormPom(driver);

        formPom.clickForms();
        formPom.clickPracticeForms();

        formPom.setFirstName(FIRST_NAME);
        formPom.setLastName(LAST_NAME);
        formPom.setEmail(EMAIL);
        formPom.setGender(GENDER);
        formPom.setNumber(NUMBER);
        formPom.setDateOfBirth(DATE);
        formPom.setSubject(SUBJECT);
        formPom.setHobbies(HOBBY);
        formPom.setState(STATE);
        formPom.setCity(CITY);

        // IMPORTANT - închide reclama înainte de submit
        formPom.closeAdvert();

        formPom.clickSubmit();

        // ====== ASSERTURI ======

        Assert.assertEquals(
                formPom.getTableData("Student Name"),
                FIRST_NAME + " " + LAST_NAME
        );

        Assert.assertEquals(
                formPom.getTableData("Student Email"),
                EMAIL
        );

        Assert.assertEquals(
                formPom.getTableData("Gender"),
                GENDER
        );

        Assert.assertEquals(
                formPom.getTableData("Mobile"),
                NUMBER
        );

        Assert.assertEquals(
                formPom.getTableData("Subjects"),
                SUBJECT
        );

        Assert.assertEquals(
                formPom.getTableData("Hobbies"),
                HOBBY
        );

        Assert.assertEquals(
                formPom.getTableData("State and City"),
                STATE + " " + CITY
        );

        System.out.println("\n--- FINISH TEST ---\n");
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }
}