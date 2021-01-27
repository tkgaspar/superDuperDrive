package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

    @LocalServerPort
    private int port;
    private WebDriver driver;
    private String baseURL = "http://localhost:";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new FirefoxDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

        @Test
        public void getHomePageUnauthorized () {
            driver.get("http://localhost:" + this.port + "/home");
            Assertions.assertEquals("Login", driver.getTitle());
        }


        public void userSignupLogin () {
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
        public void testUserSignupLogin () throws InterruptedException {
            userSignupLogin();
            Thread.sleep(1000);
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
        public void testCreateNote () throws InterruptedException {
            userSignupLogin();
            HomePage homepage = new HomePage(driver);
            Thread.sleep(1000);
            int row = 1;
            homepage.recordNewNote("Testing the title", "Testing the description");
            Thread.sleep(1000);
            homepage.accesNotesTab();
            Assertions.assertEquals("Testing the title", homepage.getTitleInList(row));
            Assertions.assertEquals("Testing the description", homepage.getDescription(row));
            homepage.editExistingNote("Testing changes", "Testing changes to the description", row);
            Assertions.assertEquals("Testing changes", homepage.getTitleInList(row));
            Assertions.assertEquals("Testing changes to the description", homepage.getDescription(row));
            homepage.recordNewNote("Second test", "Testing the second description");
            Thread.sleep(1000);
            homepage.accesNotesTab();
            row++;
            homepage.deleteExistingNote(row);
            Assertions.assertFalse(homepage.noteExists("Second test"));

        }

        //Testing error messages
        @Test
        public void errorMessageTests () throws InterruptedException {
            userSignupLogin();
            HomePage homepage = new HomePage(driver);
            Thread.sleep(1000);

        }
    }
