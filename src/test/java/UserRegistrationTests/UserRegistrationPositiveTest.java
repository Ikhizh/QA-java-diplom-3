package UserRegistrationTests;

import UserData.User;
import UserData.UserCreator;
import UserData.UserCreds;
import api.Request;
import api.TokenModel;
import api.UserDataCreation;
import driver.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageObject.RegistrationPage;

import static config.AppConfig.REG_URL;

public class UserRegistrationPositiveTest {
    private WebDriver driver;
    private User user;
    private UserCreator userCreator;
    private UserCreds userCreds;
    private String name;
    private String email;
    private String password;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
        driver.get(REG_URL);
    }

    @After
    public void teardown() {
        user = new User(name, email, password);
        userCreator = new UserCreator();
        userCreds = new UserCreds(email, password);
        TokenModel tokenResponse = Request.requestUserToken(userCreds);
        userCreator
                .delete(user, tokenResponse);
        driver.quit();
    }

    @Test
    public void RegistrationUserWithValidData() {
        RegistrationPage userRegister = new RegistrationPage(driver);
        name = UserDataCreation.name;
        email = UserDataCreation.email;
        password = UserDataCreation.password;
        userRegister.registerNewUser(name, email, password);
        userRegister.waitLogInPageOpened();
        userRegister.checkThatUserRegistered();
    }
}
