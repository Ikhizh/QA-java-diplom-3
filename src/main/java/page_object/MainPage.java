package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By enterAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private By userCabinetArea = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By crateOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By pageTitle = By.xpath(".//h1[text()='Соберите бургер']");
    private final By bunsButton = By.xpath(".//span[text()='Булки']");
    private final By saucesButton = By.xpath(".//span[text()='Соусы']");
    private final By toppingsButton = By.xpath(".//span[text()='Начинки']");
    private final By bunsSection = By.xpath(
            ".//div[contains(@class, 'tab_type_current') and contains(span/text(), 'Булки')]");
    private final By saucesSection = By.xpath(
            ".//div[contains(@class, 'tab_type_current') and contains(span/text(), 'Соусы')]");
    private final By toppingsSection = By.xpath(
            ".//div[contains(@class, 'tab_type_current') and contains(span/text(), 'Начинки')]");

    @Step("Check that user can get to local cabinet by clicking to enter account button")
    public void getLoginPageByClickingEnterAccountButton() {
        new WebDriverWait(driver, 3).until(driver -> (driver.findElement(userCabinetArea).getText() != null
                && !driver.findElement(userCabinetArea).getText().isEmpty()));
        driver.findElement(userCabinetArea).isDisplayed();
        driver.findElement(enterAccountButton).click();
    }

    @Step("Check that user can get to local cabinet by clicking to user cabinet")
    public void getLoginPageByClickingUserCabinetArea() {
        new WebDriverWait(driver, 3).until(driver -> (driver.findElement(userCabinetArea).getText() != null
                && !driver.findElement(userCabinetArea).getText().isEmpty()));
        driver.findElement(userCabinetArea).isDisplayed();
        driver.findElement(userCabinetArea).click();
    }

    @Step("Wait page title load")
    public void waitForLoadPageTitleTitle() {
        new WebDriverWait(driver, 3).until(driver ->
                driver.findElement(pageTitle).isDisplayed());
    }

    @Step("Click on buns")
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    @Step("Click on 'Соусы'")
    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
    }

    @Step("Click on 'Начинки'")
    public void clickToppingsButton() {
        driver.findElement(toppingsButton).click();
    }

    @Step("Проверка отображения ингредиентов из раздела Булки")
    public boolean isBuns() {
        return driver.findElement(bunsSection).isEnabled();
    }

    @Step("Проверка отображения ингредиентов из раздела Соусы")
    public boolean isSauces() {
        return driver.findElement(saucesSection).isEnabled();
    }

    @Step("Проверка отображения ингредиентов из раздела Начинки")
    public boolean isToppings() {
        return driver.findElement(toppingsSection).isEnabled();
    }

    @Step("Проверка отображения заголовка страницы")
    public boolean isPageTitle() {
        waitForLoadPageTitleTitle();
        return driver.findElement(pageTitle).isDisplayed();
    }

}
