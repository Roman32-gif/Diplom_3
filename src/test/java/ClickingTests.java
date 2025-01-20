import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.objects.LogInPage;
import page.objects.MainPage;
import generate.random.UserApi;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static page.objects.Config.*;

public class ClickingTests {
    private final DriverBrowsers driverSettings = new DriverBrowsers();
     private UserApi userApi;
    private String userEmail;
    private String userPassword;
    private String userName;


    @Before
    public void startUp () {
        driverSettings.initYandexBrowser();
        userApi = new UserApi();
        userEmail = "user" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
        userPassword = UUID.randomUUID().toString();
        userName = "User_" + UUID.randomUUID().toString().substring(0, 8);
        userApi.createUser(userEmail, userPassword, userName);

    }

    @Test
    @DisplayName("Нажатие на личный кабинет")
    @Description("Успешное нажатие на личный кабинет")
    public void clickOnPersonalAccountTest() {
        WebDriver driver =driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButtonRight();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("URL страницы после регистрации должен быть равен LOGIN_URL", LOGIN_URL, currentUrl);
    }

    @Test
    @DisplayName("Нажатие на Конструктор")
    @Description("Успешное нажатие на конструктор из личного кабинета")
    public void clickOnConstructorFromPersonalAccountTest(){
        WebDriver driver =driverSettings.getDriver();
        LogInPage logInPage = new LogInPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButtonRight();
        logInPage.fillKnownEmail(userEmail);
        logInPage.fillKnownPassword(userPassword);
        logInPage.clickLoginButton();
        mainPage.clickLoginAccountButtonRight();
        mainPage.clickOnConstructorButton();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("URL страницы после регистрации должен быть равен BASE_URL", BASE_URL, currentUrl);
    }

    @Test
    @DisplayName("Нажатие на логотип")
    @Description("Успешное нажатие на логотип из личного кабинета")
    public void clickOnLogoFromPersonalAccountTest()  {
        WebDriver driver =driverSettings.getDriver();
        LogInPage logInPage = new LogInPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButtonRight();
        logInPage.fillKnownEmail(userEmail);
        logInPage.fillKnownPassword(userPassword);
        logInPage.clickLoginButton();
        mainPage.clickLoginAccountButtonRight();
        mainPage.clickOnLogo();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("URL страницы после регистрации должен быть равен BASE_URL", BASE_URL, currentUrl);
    }

    @Test
    @DisplayName("Нажатие на выход из аккаунта")
    @Description("Успешный выход из аккаунта из личного кабинета")
    public void clickOnButtonFormPersonalAccountToLeaveAnAccountTest() {
        WebDriver driver =driverSettings.getDriver();
        LogInPage logInPage = new LogInPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButtonRight();
        logInPage.fillKnownEmail(userEmail);
        logInPage.fillKnownPassword(userPassword);
        logInPage.clickLoginButton();
        mainPage.clickLoginAccountButtonRight();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[text()=\"Выход\"]")));
        mainPage.clickButtonLogOut();
        assertTrue(logInPage.isUserNoLoggedIn());

    }

    @Test
    @DisplayName("Нажатие на разные виды конструктора")
    @Description("Успешное переходы между разделами конструкторами")
    public void clickingBetweenTermsTest() {
        WebDriver driver =driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(@class, 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect')]/span[contains(@class, 'text text_type_main-default') and text() ='Соусы']")));
        mainPage.clickOnSauce();
        WebDriverWait wait1 = new WebDriverWait(driver, 100);
        wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(@class, 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect')]/span[contains(@class, 'text text_type_main-default') and text() ='Булки']")));
        mainPage.clickOnBread();
        WebDriverWait wait2 = new WebDriverWait(driver, 100);
        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(@class, 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect')]/span[contains(@class, 'text text_type_main-default') and text() ='Начинки']")));
        mainPage.clickOnFilling();
        assertTrue(mainPage.IsFillingOnMain());
    }

    @After
    public void tearDown() {
        userApi.deleteUser(userEmail);
        driverSettings.getDriver().quit();
    }
}
