package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

    @LocalServerPort
    private int port;
    private WebDriver driver;
    private String baseURL = "http://localhost:";
    private WebDriverWait wait;
    private EncryptionService encryptionService;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

   /* @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }*/

    @Test
    public void getHomePageUnauthorized() {
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Login", driver.getTitle());
    }


    public void userSignupLogin() {
        String username = "tkgaspar";
        String password = "tkg117";

        driver.get(baseURL + port + "/signup");

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Gaspar", "Tamas", username, password);

        driver.get(baseURL + port + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    @Test
    public void testUserSignupLogin() throws InterruptedException {
        userSignupLogin();
        Assertions.assertEquals("Home", driver.getTitle());

        HomePage homePage = new HomePage(driver);
        homePage.logout();
        Assertions.assertEquals("Login", driver.getTitle());

        driver.get(baseURL + port + "/Home");
        Assertions.assertEquals("Login", driver.getTitle());


    }

    /* Write a test that creates a note, and verifies it is displayed.*/
    /* Write a test that edits an existing note and verifies that the changes are displayed.*/
    /* Write a test that deletes a note and verifies that the note is no longer displayed.*/
    @Test
    public void testCreateNote() throws InterruptedException {
        userSignupLogin();
        HomePage homepage = new HomePage(driver);
        int row = 1;
        homepage.recordNewNote("Testing the title", "Testing the description");
        Assertions.assertEquals("Testing the title", homepage.getTitleInList(row));
        Assertions.assertEquals("Testing the description", homepage.getDescription(row));
        homepage.editExistingNote("Testing changes", "Testing changes to the description", row);
        Assertions.assertEquals("Testing changes", homepage.getTitleInList(row));
        Assertions.assertEquals("Testing changes to the description", homepage.getDescription(row));
        homepage.recordNewNote("Second test", "Testing the second description");
        homepage.accessNotesTab();
        row++;
        homepage.deleteExistingNote(row);
        Assertions.assertFalse(homepage.noteExists("Second test"));

    }


       /*

        Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.*/

    //Write a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.
    @Test
    public void testCredentials() throws InterruptedException {
        userSignupLogin();
        HomePage homepage = new HomePage(driver);
        int row = 1;
        homepage.recordNewCredential("www.hvv.ro", "tkgaspar", "dummypass");
        Assertions.assertEquals("www.hvv.ro", homepage.getUrlInList(row));
        Assertions.assertEquals("tkgaspar", homepage.getUsernameInList(row));

        SecureRandom random=new SecureRandom();
        byte [] key= new byte[16];
        String encodedKey= Base64.getEncoder().encodeToString(key);
        EncryptionService encryptionService= new EncryptionService();
        String encodedPassword=encryptionService.encryptValue("dummypass",encodedKey);
        Assertions.assertEquals(encodedPassword,homepage.getPasswordInList(row));

    }
    // Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
    // Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.*/
    @Test
    public void testExistingCredential() throws InterruptedException {
        userSignupLogin();
        HomePage homepage = new HomePage(driver);
        int row = 1;
        homepage.accessCredentialTab();
        homepage.recordNewCredential("www.hvv.ro", "tkgaspar", "dummypass");
        homepage.getCredentialForEditing(row);
        Assertions.assertEquals("www.hvv.ro", homepage.getCredoUrl());
        Assertions.assertEquals("tkgaspar",homepage.getCredoUsername());
        Assertions.assertEquals("dummypass",homepage.getCredoPassword());
        homepage.editExistingCredential("www.google.com", "Schumacher", "Ferrari",row);
        Assertions.assertEquals("www.google.com", homepage.getUrlInList(row));
        Assertions.assertEquals("Schumacher", homepage.getUsernameInList(row));
        SecureRandom random=new SecureRandom();
        byte [] key= new byte[16];
        String encodedKey= Base64.getEncoder().encodeToString(key);
        EncryptionService encryptionService= new EncryptionService();
        String encodedPassword=encryptionService.encryptValue("Ferrari",encodedKey);
        Assertions.assertEquals(encodedPassword,homepage.getPasswordInList(row));
        homepage.recordNewCredential("www.somepage.com", "someuser", "dummypass");
        row++;
        homepage.deleteExistingCredential(row);
        Assertions.assertFalse(homepage.credentialExists("www.somepage.com"));

    }
}
