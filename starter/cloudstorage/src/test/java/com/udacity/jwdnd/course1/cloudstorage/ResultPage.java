package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;


public class ResultPage {
    private final WebDriver driver;

    public ResultPage(WebDriver driver) {
        this.driver=driver;
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

    @FindBy(id="credoSuccess")
    private WebElement credoSuccessSpan;

    @FindBy(id="credoError")
    private WebElement credoErrorSpan;

    @FindBy (xpath=("//a[text()='here']"))
    private WebElement continueLink;

    @FindBy (id="noteSuccessLink")
    private WebElement noteSuccessLink;

    @FindBy (id="credoSuccessLink")
    private WebElement credoSuccessLink;

    public void returnHomeAfterMessage() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueLink);
    }

    public WebElement getCredoSuccessSpan() {
        return credoSuccessSpan;
    }

    public WebElement getCredoErrorSpan() {
        return credoErrorSpan;
    }

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
