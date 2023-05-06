package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static config.WebDriverConfig.BROWSER;
import static config.WebDriverConfig.WAIT_SECONDS_TIME_OUT;

public class WebDriverFactory {
    public static WebDriver get() {
        WebDriver driver;
        switch (BROWSER) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "yandex":
                driver = createYandexDriver();
                break;
            default:
                throw new RuntimeException("Browser " + BROWSER + " not exist");
        }
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(WAIT_SECONDS_TIME_OUT, TimeUnit.SECONDS);
        return driver;
    }

    private static WebDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver", String.format("%s/%s", System.getenv("BROWSER_DRIVERS"),
                System.getenv("YANDEX_BROWSER_DRIVER_FILENAME")));
        ChromeOptions options = new ChromeOptions();
        options.setBinary(System.getenv("YANDEX_BROWSER_PATH"));
        return new ChromeDriver(options);
    }
}
