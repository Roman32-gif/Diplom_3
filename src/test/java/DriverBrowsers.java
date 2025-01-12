import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverBrowsers {
    private WebDriver driver;


    public void initYandexBrowser() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "./myDriver/chromedriveryandex.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Users/Пользователь/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public void initChrome() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "./myDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
