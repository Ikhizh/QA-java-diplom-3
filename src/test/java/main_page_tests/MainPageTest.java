package main_page_tests;

import driver.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_object.MainPage;

import static config.AppConfig.BASE_URL;
import static org.junit.Assert.assertTrue;

public class MainPageTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverFactory.get();
        driver.get(BASE_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Успешный переход в раздел \"Булки\"")
    public void clickOnBunSectionTest() {
        MainPage mainPage = new MainPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadPageTitleTitle();
        mainPage.clickSaucesButton();
        mainPage.clickBunsButton();
        assertTrue(mainPage.isBuns());
    }

    @Test
    @DisplayName("Успешный переход в раздел \"Соусы\"")
    public void clickOnSauceSectionTest() {
        MainPage mainPage = new MainPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadPageTitleTitle();
        mainPage.clickSaucesButton();
        assertTrue(mainPage.isSauces());
    }

    @Test
    @DisplayName("Успешный переход в раздел \"Начинки\"")
    public void clickOnFillersSectionTest() {
        MainPage mainPage = new MainPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadPageTitleTitle();
        mainPage.clickToppingsButton();
        assertTrue(mainPage.isToppings());
    }

}
