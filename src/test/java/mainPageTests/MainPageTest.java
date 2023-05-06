package mainPageTests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageObject.MainPage;

import static config.AppConfig.MAIN_PAGE_URL;

public class MainPageTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(MAIN_PAGE_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Click on Bun's section header on the main page and check for the header in the menu")
    public void clickOnBunSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnHomePageLocator(mainPage.saucesSection);
        mainPage.clickOnHomePageLocator(mainPage.bunsSection);
        mainPage.checkSection(mainPage.bunsHeaderSection);
    }

    @Test
    @DisplayName("Click on Sauce's section header on the main page and check for the header in the menu")
    public void clickOnSauceSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnHomePageLocator(mainPage.saucesSection);
        mainPage.checkSection(mainPage.saucesHeaderSection);
    }

    @Test
    @DisplayName("Click on Filler's section header on the main page and check for the header in the menu")
    public void clickOnFillersSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnHomePageLocator(mainPage.fillersSection);
        mainPage.checkSection(mainPage.fillersHeaderSection);
    }

}
