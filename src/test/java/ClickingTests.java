import generate.random.RandomUser;
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
import page.objects.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static page.objects.Config.*;

public class ClickingTests {
    private final DriverBrowsers driverSettings = new DriverBrowsers();
    private UserApi userApi;
    private String email;
    private String password;
    private static final String LOGOUT_BUTTON_XPATH = ".//button[text()=\"Выход\"]";
    @Before
    public void startUp () {
        driverSettings.initYandexBrowser();
        userApi = new UserApi();
        User createUser = userApi.createUser();
        email = createUser.getEmail();
        password = createUser.getPassword();

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
        logInPage.fillKnownEmail(email);
        logInPage.fillKnownPassword(password);
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
        logInPage.fillKnownEmail(email);
        logInPage.fillKnownPassword(password);
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
        logInPage.fillKnownEmail(email);
        logInPage.fillKnownPassword(password);
        logInPage.clickLoginButton();
        mainPage.clickLoginAccountButtonRight();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOGOUT_BUTTON_XPATH)));
        mainPage.clickButtonLogOut();
        assertTrue(logInPage.isUserNoLoggedIn());

    }

    @Test
    @DisplayName("Нажатие на разные виды конструктора")
    @Description("Успешный переход между разделами конструктора к соусу")
    public void clickingOnFillingTest() {
        WebDriver driver =driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOnSauce();
        assertTrue(mainPage.IsSauceOnMain());
    }

    @Test
    @DisplayName("Нажатие на разные виды конструктора")
    @Description("Успешный переход между разделами конструктора к добавкам")
    public void clickingOnSauceTest() {
        WebDriver driver = driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOnFilling();
        assertTrue(mainPage.IsFillingOnMain());
    }

    @Test
    @DisplayName("Нажатие на разные виды конструктора")
    @Description("Успешный переход между разделами конструктора к булке")
    public void clickingOnBreadTest() {
        WebDriver driver = driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOnFilling();
        mainPage.clickOnBread();
        assertTrue(mainPage.IsBreadOnMain());
    }

    @After
    public void tearDown() {
        userApi.deleteUser(email);
        driverSettings.getDriver().quit();
    }
}
