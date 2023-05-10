package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;

    private By nameField = By.xpath(".//label[text()='Имя']/../input[@name='name']");
    private By emailField = By.xpath(".//label[text()='Email']/../input[@name='name']");
    private By passwordField = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and @name = 'Пароль']");
    private By registrationButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and  text()='Зарегистрироваться']");
    private By logInButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and  text()='Войти']");
    private By incorrectPasswordMessage = By.xpath(".//p[text()='Некорректный пароль']");
    private By logInRegistrationPageButton = By.xpath(".//a[text() = 'Войти']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmailField(String email) {

        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Check that user was moved to login page")
    public void getLogInPage() {
        new WebDriverWait(driver, 3).until(driver -> (driver.findElement(logInRegistrationPageButton).getText() != null
                && !driver.findElement(logInRegistrationPageButton).getText().isEmpty()));
        driver.findElement(logInRegistrationPageButton).isDisplayed();
        driver.findElement(logInRegistrationPageButton).click();
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Check that 'Некорректный пароль' message appeared")
    public void checkIncorrectPasswordMessageAppeared() {
        driver.findElement(incorrectPasswordMessage).isDisplayed();
    }

    @Step("Check that user was registered")
    public void checkThatUserRegistered() {
        driver.findElement(logInButton).isDisplayed();
    }

    public void waitLogInPageOpened() {
        new WebDriverWait(driver, 3).until(driver -> (driver.findElement(logInButton).getText() != null
                && !driver.findElement(logInButton).getText().isEmpty()));
        driver.findElement(logInButton).isDisplayed();
    }

    @Step("Register new user")
    public void registerNewUser(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPassword(password);
        clickRegistrationButton();
    }

}
