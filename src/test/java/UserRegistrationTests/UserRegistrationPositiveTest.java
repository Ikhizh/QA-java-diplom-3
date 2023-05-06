package UserRegistrationTests;

import UserData.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.RegistrationPage;

import static config.AppConfig.REG_URL;

public class UserRegistrationPositiveTest {
    private WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get(REG_URL);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void RegistrationUserWithValidData() {
        RegistrationPage userRegister = new RegistrationPage(driver);
        String name = UserGenerator.name;
        String email = UserGenerator.email;
        String password = UserGenerator.password;
        userRegister.registerNewUser(name, email, password);
        userRegister.waitLogInPageOpened();
        userRegister.checkThatUserRegistered();
    }
}
