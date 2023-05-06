package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class ProfilePage {
    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public By constructorLink = By.xpath(".//p[text()='Конструктор']");
    public By logoLink = By.xpath(".//div/a[@href='/']");
    public By logoutButton = By.xpath(".//button[text() = 'Выход']");

    public void clickOnProfilePageLocator(By locator) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    public void checkForUrl(String url) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlToBe(url));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(String.format("Адрес страницы должен быть %s", url), url, currentUrl);
    }

    public void clickConstructorLink() {
        driver.findElement(constructorLink).click();
    }

    public void clickLogoLink() {
        driver.findElement(logoLink).click();
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public void waitPageOpened() {
        new WebDriverWait(driver, 3).until(driver -> (driver.findElement(logoutButton).getText() != null
                && !driver.findElement(logoutButton).getText().isEmpty()));
        driver.findElement(logoutButton).isDisplayed();
    }
}
