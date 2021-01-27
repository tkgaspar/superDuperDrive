package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id="inputFirstName")
    private WebElement firstNameInput;

    @FindBy(id="inputLastName")
    private WebElement lastNameInput;

    @FindBy(id="inputUsername")
    private WebElement userNameInput;

    @FindBy(id="inputPassword")
    private WebElement passwordInput;

    @FindBy(id="submit-button")
    private WebElement submitButton;

    @FindBy(id="success-msg")
    private WebElement loginSuccessDiv;

    @FindBy(id="linkToLoginPage")
    private WebElement linkToLogin;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signup(String fn,String ln,String username,String password){
        firstNameInput.sendKeys(fn);
        lastNameInput.sendKeys(ln);
        userNameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
    }














}
