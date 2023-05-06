package logInTests;

import UserData.User;
import UserData.UserCreds;
import UserData.UserCreator;
import api.Request;
import api.TokenModel;
import api.UserDataCreation;
import driver.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import PageObject.LogInPage;
import PageObject.MainPage;
import PageObject.RegistrationPage;

import static config.AppConfig.MAIN_PAGE_URL;
import static config.AppConfig.REG_URL;

public class LogInTest {
    private User user;
    private UserCreator userCreator;
    private UserCreds userCreds;
    private String name;
    private String email;
    private String password;
    private WebDriver driver;

    @Before
    public void setUp() {
        name = UserDataCreation.name;
        email = UserDataCreation.email;
        password = UserDataCreation.password;
        user = new User(name, email, password);
        userCreator = new UserCreator();
        userCreds = new UserCreds(email, password);
        userCreator
                .create(user);
        driver = WebDriverFactory.get();
    }

    @After
    public void tearDown() {
        TokenModel tokenResponse = Request.requestUserToken(userCreds);
        userCreator
                .delete(user, tokenResponse);
        driver.quit();
    }

    @DisplayName("Login with button Войти в аккаунт")
    @Test
    public void loginWithLoginButtonTest() {
        driver.get(MAIN_PAGE_URL);
        MainPage page = new MainPage(driver);
        LogInPage userLogIn = new LogInPage(driver);
        page.getLoginPageByClickingEnterAccountButton();
        userLogIn.login(email, password);
        userLogIn.checkForUrl(MAIN_PAGE_URL);
    }

    @DisplayName("Login with button Личный Кабинет")
    @Test
    public void loginWithProfileButtonTest() {
        driver.get(MAIN_PAGE_URL);
        MainPage mainPage = new MainPage(driver);
        LogInPage userLogIn = new LogInPage(driver);
        mainPage.getLoginPageByClickingUserCabinetArea();
        userLogIn.login(email, password);
        userLogIn.checkForUrl(MAIN_PAGE_URL);
    }

    @DisplayName("Login with button Войти from register page")
    @Test
    public void loginFromRegisterPageTest() {
        driver.get(REG_URL);
        LogInPage userLogIn = new LogInPage(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        regPage.getLogInPage();
        userLogIn.login(email, password);
        userLogIn.checkForUrl(MAIN_PAGE_URL);
    }

    @DisplayName("Login with button Войти from forgot-password page")
    @Test
    public void loginFromForgotPasswordPageTest() {
        driver.get(MAIN_PAGE_URL);
        MainPage mainPage = new MainPage(driver);
        LogInPage userLogIn = new LogInPage(driver);
        mainPage.getLoginPageByClickingUserCabinetArea();
        userLogIn.getForgotPasswordPage();
        userLogIn.getLoginPageFromForgotPassword();
        userLogIn.login(email, password);
        userLogIn.checkForUrl(MAIN_PAGE_URL);
    }

}
