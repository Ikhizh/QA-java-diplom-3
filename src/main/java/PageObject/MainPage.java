package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By enterAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private By userCabinetArea = By.xpath(".//p[text() = 'Личный Кабинет']");
    public By bunsSection = By.xpath(".//section[1]/div[1]/div[1]/span");
    public By saucesSection = By.xpath(".//section[1]/div[1]/div[2]/span");
    public By fillersSection = By.xpath(".//section[1]/div[1]/div[3]/span");
    public By bunsHeaderSection = By.xpath(".//section[1]/div[2]/h2[1]");
    public By saucesHeaderSection = By.xpath(".//section[1]/div[2]/h2[2]");
    public By fillersHeaderSection = By.xpath(".//section[1]/div[2]/h2[3]");

    public void getLoginPageByClickingEnterAccountButton() {
        driver.findElement(enterAccountButton).click();
    }

    public void getLoginPageByClickingUserCabinetArea() {
        driver.findElement(userCabinetArea).click();
    }

    public void checkSection(By header) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(header));
        assertTrue("Ожидается появление заголовка для секции с ингредиентами", driver.findElement(header).isDisplayed());
    }

    public void clickOnHomePageLocator(By locator) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

}
