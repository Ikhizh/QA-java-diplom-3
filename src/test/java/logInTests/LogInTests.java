package logInTests;

import UserData.User;
import UserData.UserCreds;
import UserData.UserGenerator;
import api.Request;
import api.TokenModel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LogInPage;
import pages.MainPage;
import pages.RegistrationPage;

import static config.AppConfig.MAIN_PAGE_URL;
import static config.AppConfig.REG_URL;

public class LogInTests {
    private User user;
    private UserGenerator userGenerator;
    private UserCreds userCreds;
    private String name;
    private String email;
    private String password;
    private WebDriver driver;

    @Before
    public void setUp() {
        name = UserGenerator.name;
        email = UserGenerator.email;
        password = UserGenerator.password;
        user = new User(name, email, password);
        userGenerator = new UserGenerator();
        userCreds = new UserCreds(email, password);
        userGenerator
                .create(user);
    }

    @After
    public void tearDown() {
        TokenModel tokenResponse = Request.requestUserToken(userCreds);
        userGenerator
                .delete(user, tokenResponse);
    }

    @DisplayName("Login with button Войти в аккаунт")
    @Test
    public void loginWithLoginButtonTest() {
        driver = new ChromeDriver();
        driver.get(MAIN_PAGE_URL);
        MainPage page = new MainPage(driver);
        LogInPage userLogIn = new LogInPage(driver);
        page.getLoginPageByClickingEnterAccountButton();
        userLogIn.login(email, password);
//        userLogIn.checkForUrl(MAIN_PAGE_URL);
    }

    @DisplayName("Login with button Личный Кабинет")
    @Test
    public void loginWithProfileButtonTest() {
        driver = new ChromeDriver();
        driver.get(MAIN_PAGE_URL);
        MainPage mainPage = new MainPage(driver);
        LogInPage userLogIn = new LogInPage(driver);
        mainPage.getLoginPageByClickingUserCabinetArea();
        userLogIn.login(email, password);
    }

    @DisplayName("Login with button Войти from register page")
    @Test
    public void loginFromRegisterPageTest() {
        driver = new ChromeDriver();
        driver.get(REG_URL);
        LogInPage userLogIn = new LogInPage(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        regPage.getLogInPage();
        userLogIn.login(email, password);
    }

    @Test
    public void loginFromForgotPasswordPageTest(){

    }

}
