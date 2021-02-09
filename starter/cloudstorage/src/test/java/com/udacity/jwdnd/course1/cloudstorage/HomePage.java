package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {
    private final WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "show")
    private WebElement noteModal;

    @FindBy(id = "showCredoModal")
    private WebElement credentialModalButton;

    @FindBy(id = "credoModalForm")
    private WebElement credentialModal;


    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmitButton;

    @FindBy(id = "userTable")
    private WebElement noteTable;

    @FindBy(id = "credentialTable")
    private WebElement credoTable;

    @FindBy(id = "credential-url")
    private WebElement credoUrl;

    @FindBy(id = "credential-username")
    private WebElement credoUsername;

    @FindBy(id = "credential-password")
    private WebElement credoPassword;

    @FindBy(id = "credentialSubmit")
    private WebElement credoSubmitButton;

    public String getCredoUrl() {
        return this.credoUrl.getAttribute("value");
    }

    public String getCredoUsername() {
        return this.credoUsername.getAttribute("value");
    }

    public String getCredoPassword() {
        return this.credoPassword.getAttribute("value");
    }


    public void logout() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutButton);
    }

    public void accessNotesTab() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", noteTab);
    }

    public void accessCredentialTab() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialTab);
    }

    public void getNoteModal() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", noteModal);
    }

    public void getCredentialModal() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialModalButton);
    }

    public void writeTitle(String title) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + title + "';", noteTitle);
    }

    public void writeDescription(String desc) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + desc + "';", noteDescription);
    }

    public void writeToCredential(String url, String username, String password) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + url + "';", credoUrl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", credoUsername);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", credoPassword);
    }

    public String getTitleInList(Integer noteId) {
        accessNotesTab();
        wait = new WebDriverWait(driver, 3, 10);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("noteTitle of row " + noteId), "Testing"));
        return this.noteTable.findElement(By.id("noteTitle of row " + noteId)).getText();

    }


    public String getDescription(Integer noteId) {
        return this.noteTable.findElement(By.id("notedesc of row " + noteId)).getText();
    }

    public String getUrlInList(Integer credoId) throws InterruptedException {
        accessCredentialTab();
        wait = new WebDriverWait(driver, 3, 10);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("Url of row " + credoId), "www."));
        return this.credoTable.findElement(By.id("Url of row " + credoId)).getText();
    }

    public String getUsernameInList(Integer credoId) {
        return this.credoTable.findElement(By.id("Username of row " + credoId)).getText();
    }

    public String getPasswordInList(Integer credoId) {
        return this.credoTable.findElement(By.id("Password of row " + credoId)).getText();
    }


    public void getNoteforEditing(Integer noteId) {
        this.noteTable.findElement(By.id("edtbtn of row " + noteId)).click();

    }

    public void getCredentialForEditing(Integer credoId) {
        accessCredentialTab();
        wait = new WebDriverWait(driver, 3, 100);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cedtbtn of row " + credoId)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credoTable.findElement(By.id("cedtbtn of row " + credoId)));
    }

    public void recordNewNote(String title, String description) {
        accessNotesTab();
        getNoteModal();
        writeTitle(title);
        writeDescription(description);
        this.noteSubmitButton.submit();
        ResultPage result = new ResultPage(driver);
        result.returnHomeAfterMessage();
    }

    public void editExistingNote(String changedTitle, String changedDescription, Integer noteId) {
        accessNotesTab();
        getNoteforEditing(noteId);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", noteTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", noteDescription);
        writeTitle(changedTitle);
        writeDescription(changedDescription);
        noteSubmitButton.submit();
        ResultPage result = new ResultPage(driver);
        result.returnHomeAfterMessage();
        accessNotesTab();
    }


    public void editExistingCredential(String changedUrl, String changedUsername, String changedPassword, Integer credentialId) {
        accessCredentialTab();
        getCredentialForEditing(credentialId);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", credoUrl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", credoUsername);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", credoPassword);
        writeToCredential(changedUrl, changedUsername, changedPassword);
        credoSubmitButton.submit();
        ResultPage result = new ResultPage(driver);
        result.returnHomeAfterMessage();
        accessCredentialTab();
    }

    public void deleteExistingCredential(Integer credoId) {
        accessCredentialTab();
        wait = new WebDriverWait(driver, 3, 100);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cdltbtn of row " + credoId)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credoTable.findElement(By.id("cdltbtn of row " + credoId)));
        ResultPage result =new ResultPage(driver);
        result.returnHomeAfterMessage();
        accessCredentialTab();
    }

    public void deleteExistingNote(Integer noteId) {
        accessNotesTab();
        wait = new WebDriverWait(driver, 3, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("dltbtn of row " + noteId)));
        this.noteTable.findElement(By.id("dltbtn of row " + noteId)).click();
        ResultPage result =new ResultPage(driver);
        result.returnHomeAfterMessage();
    }

    public boolean noteExists(String title) {
        List<WebElement> noteTitles = this.noteTable.findElements(By.xpath("//*[contains(@id, \'noteTitle of row\')]"));
        boolean exists = false;
        for (WebElement w : noteTitles) {
            if (w.getText().equals(title)) {
                exists = true;
            }
        }
        return exists;
    }

    public boolean credentialExists(String url) {
        List<WebElement> noteTitles = this.credoTable.findElements(By.xpath("//*[contains(@id, \'Url of row\')]"));
        boolean exists = false;
        for (WebElement w : noteTitles) {
            if (w.getText().equals(url)) {
                exists = true;
            }
        }
        return exists;
    }

    public void recordNewCredential(String url, String username, String password) {
        accessCredentialTab();
        getCredentialModal();
        writeToCredential(url, username, password);
        this.credoSubmitButton.submit();
        ResultPage result = new ResultPage(driver);
        result.returnHomeAfterMessage();
    }
}
