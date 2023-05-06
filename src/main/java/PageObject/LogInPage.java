package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class LogInPage {
    private WebDriver driver;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.xpath(".//label[text()='Email']/../input[@name='name']");
    private By passwordField = By.xpath(".//label[text()='Пароль']/../input[@class= 'text input__textfield text_type_main-default']");
    private By logInButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Войти']");
    private By forgotPasswordLink = By.xpath(".//a[text() = 'Восстановить пароль']");
    private By logInLinkOnForgotPassword = By.xpath(".//a[text() = 'Войти']");

    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogInButton() {
        driver.findElement(logInButton).click();
    }

    public void checkForUrl(String url) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlToBe(url));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(String.format("Адрес страницы должен быть %s", url), url, currentUrl);
    }

    public void getForgotPasswordPage() {
        driver.findElement(forgotPasswordLink).click();
    }

    public void getLoginPageFromForgotPassword() {
        driver.findElement(logInLinkOnForgotPassword).click();
    }

    public void login(String email, String password) {
        setEmailField(email);
        setPassword(password);
        clickLogInButton();
    }

}
