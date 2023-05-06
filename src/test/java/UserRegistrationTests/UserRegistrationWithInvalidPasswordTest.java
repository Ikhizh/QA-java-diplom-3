package UserRegistrationTests;

import UserData.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.RegistrationPage;

import static config.AppConfig.REG_URL;

public class UserRegistrationWithInvalidPasswordTest {
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
    public void registrationWithInvalidPasswordShowsError(){
        RegistrationPage user = new RegistrationPage(driver);
        String name = UserGenerator.name;
        String email = UserGenerator.email;
        user.setNameField(name);
        user.setEmailField(email);
        user.setInvalidPassword();
        user.clickRegistrationButton();
        user.checkIncorrectPasswordMessageAppeared();
    }

}
