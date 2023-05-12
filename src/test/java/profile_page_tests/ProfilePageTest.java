package profile_page_tests;

import userdata.User;
import userdata.UserRequests;
import userdata.UserCreds;
import api.Request;
import api.TokenModel;
import api.UserDataCreation;
import driver.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_object.LogInPage;
import page_object.MainPage;
import page_object.ProfilePage;

import static config.AppConfig.*;

public class ProfilePageTest {
    private User user;
    private UserRequests userCreator;
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
        userCreator = new UserRequests();
        userCreds = new UserCreds(email, password);
        driver = WebDriverFactory.get();
        driver.get(BASE_URL);
        userCreator
                .create(user);
    }

    @After
    public void tearDown() {
        TokenModel tokenResponse = Request.requestUserToken(userCreds);
        userCreator
                .delete(user, tokenResponse);
        driver.quit();
    }

    @DisplayName("Click on link Личный кабинет changes url to /account/profile")
    @Test
    public void clickOnProfileLinkTest() {
        MainPage mainPage = new MainPage(driver);
        LogInPage userLogIn = new LogInPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.getLoginPageByClickingEnterAccountButton();
        userLogIn.login(email, password);
        mainPage.getLoginPageByClickingUserCabinetArea();
        profilePage.checkForUrl(PROFILE_URL);
    }

    @DisplayName("Click on link Конструктор from profile page changes url to home page")
    @Test
    public void clickOnConstructorLinkTest() {
        MainPage mainPage = new MainPage(driver);
        LogInPage userLogIn = new LogInPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.getLoginPageByClickingEnterAccountButton();
        userLogIn.login(email, password);
        mainPage.getLoginPageByClickingUserCabinetArea();
        profilePage.clickConstructorLink();
        profilePage.checkForUrl(BASE_URL);
    }

    @Test
    @DisplayName("Click on logo from profile page changes url to home page")
    public void clickOnLogoLinkTest() {
        MainPage mainPage = new MainPage(driver);
        LogInPage userLogIn = new LogInPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.getLoginPageByClickingEnterAccountButton();
        userLogIn.login(email, password);
        mainPage.getLoginPageByClickingUserCabinetArea();
        profilePage.clickLogoLink();
        profilePage.checkForUrl(BASE_URL);
    }

    @Test
    @DisplayName("Click on logout button change url to /login")
    public void clickOnLogoutButtonTest() {
        MainPage mainPage = new MainPage(driver);
        LogInPage userLogIn = new LogInPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.getLoginPageByClickingEnterAccountButton();
        userLogIn.login(email, password);
        mainPage.getLoginPageByClickingUserCabinetArea();
        profilePage.waitPageOpened();
        profilePage.clickLogoutButton();
        profilePage.checkForUrl(LOGIN_URL);
    }
}
