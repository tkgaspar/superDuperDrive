package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;


public class ResultPage {

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="fileSuccess")
    private WebElement fileSuccessSpan;

    @FindBy(id="noteSuccess")
    private WebElement noteSuccessSpan;

    @FindBy(id="fileError")
    private WebElement fileErrorSpan;

    @FindBy(id="noteError")
    private WebElement noteErrorSpan;

    public String getFileSuccessMessage() {
        return fileSuccessSpan.getText();
    }

    public String getNoteSuccessMessage() {
        return noteSuccessSpan.getText();
    }

    public String getFileErrorMessage() {
        return fileErrorSpan.getText();
    }

    public String getNoteErrorSpan() {
        return noteErrorSpan.getText();
    }

}
