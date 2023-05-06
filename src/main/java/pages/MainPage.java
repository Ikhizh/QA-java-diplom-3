package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By enterAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private By userCabinetArea = By.xpath(".//p[text() = 'Личный Кабинет']");

    public void getLoginPageByClickingEnterAccountButton() {
        driver.findElement(enterAccountButton).click();
        }
    public void getLoginPageByClickingUserCabinetArea(){
        driver.findElement(userCabinetArea).click();
    }

}
