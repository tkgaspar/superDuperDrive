package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "show")
    private WebElement noteModal;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmitButton;
    @FindBy(id = "userTable")
    private WebElement noteTable;

    private WebElement editNoteButton;
    private WebElement descriptionInList;
    private WebElement titleInList;
    private WebElement deleteNoteButton;

    public void logout() {
        this.logoutButton.click();
    }

    public void accesNotesTab() {
        this.noteTab.click();
    }

    public void getNoteModal() {
        this.noteModal.click();
    }

    public void writeTitle(String title) {
        this.noteTitle.sendKeys(title);
    }

    public void writeDescription(String desc) {

        this.noteDescription.sendKeys(desc);
    }

    public String getTitleInList(Integer noteId) {
        return this.noteTable.findElement(By.id("noteTitle of row " + noteId)).getText();
    }

    public String getDescription(Integer noteId) {
        return this.noteTable.findElement(By.id("notedesc of row " + noteId)).getText();
    }

    public void getNoteforEditing(Integer noteId) {
        this.noteTable.findElement(By.id("edtbtn of row " + noteId)).click();

    }

    public void recordNewNote(String title, String description) {
        accesNotesTab();
        getNoteModal();
        writeTitle(title);
        writeDescription(description);
        this.noteSubmitButton.submit();
    }

    public void editExistingNote(String changedTitle, String changedDescription, Integer noteId) throws InterruptedException {
        accesNotesTab();
        getNoteforEditing(noteId);
        this.noteTitle.clear();
        this.noteDescription.clear();
        writeTitle(changedTitle);
        writeDescription(changedDescription);
        this.noteSubmitButton.submit();
        Thread.sleep(1000);
        accesNotesTab();
    }

    public void deleteExistingNote(Integer noteId) {
        accesNotesTab();
        this.noteTable.findElement(By.id("dltbtn of row " + noteId)).click();

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
}