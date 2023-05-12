package registration_tests;

import api.Request;
import api.TokenModel;
import api.UserDataCreation;
import driver.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_object.RegistrationPage;
import userdata.User;
import userdata.UserCreds;
import userdata.UserRequests;

import static config.AppConfig.REG_URL;

public class UserRegistrationWithInvalidPasswordTest {
    private WebDriver driver;
    private User user;
    private UserRequests userCreator;
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
        TokenModel tokenResponse = Request.requestUserToken(userCreds);
        if (tokenResponse.getAccessToken() != null) {
            userCreator
                    .delete(user, tokenResponse);
        }
        driver.quit();
    }

    @Test
    public void registrationWithInvalidPasswordShowsError() {
        RegistrationPage user = new RegistrationPage(driver);
        name = UserDataCreation.name;
        email = UserDataCreation.email;
        password = "12345";
        userCreds = new UserCreds(email, password);
        user.setNameField(name);
        user.setEmailField(email);
        user.setPassword(password);
        user.clickRegistrationButton();
        user.checkIncorrectPasswordMessageAppeared();
    }

}
