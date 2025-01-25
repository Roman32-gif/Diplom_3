import generate.random.RandomUser;
import generate.random.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.objects.*;

import static org.junit.Assert.*;

public class LoginUserTest {
    private final DriverBrowsers driverSettings = new DriverBrowsers();
    private UserApi userApi;
    private String email;
    private String password;

    @Before
    public void startUp () {
        driverSettings.initYandexBrowser();
        userApi = new UserApi();
        User createUser = userApi.createUser();
        email = createUser.getEmail();
        password = createUser.getPassword();
    }

    @Test
    @DisplayName("Вход в аккаунт")
    @Description("Успешный вход в аккаунт с главной страницы")
    public void loginFromMainPageTest() {
        WebDriver driver =driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();
        logInPage.fillKnownEmail(email);
        logInPage.fillKnownPassword(password);
        boolean button = logInPage.clickLoginButton();
        assertFalse(button);


    }

    @Test
    @DisplayName("Вход в аккаунт")
    @Description("Успешный вход в аккаунт с главной страницы кнопкой справа сверху")
    public void loginFromMainPageRightUpButtonTest() {
        WebDriver driver =driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButtonRight();
        logInPage.fillKnownEmail(email);
        logInPage.fillKnownPassword(password);
        boolean button = logInPage.clickLoginButton();
        assertFalse(button);

    }

    @Test
    @DisplayName("Вход в аккаунт")
    @Description("Успешный вход в аккаунт со страницы регистрации")
    public void loginFromButtonInRegistrationPageTest() {
        WebDriver driver =driverSettings.getDriver();
        LogInPage logInPage = new LogInPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.clickLoginButton();
        logInPage.fillKnownEmail(email);
        logInPage.fillKnownPassword(password);
        boolean button =  logInPage.clickLoginButton();
        assertFalse(button);

    }

    @Test
    @DisplayName("Вход в аккаунт")
    @Description("Успешный вход в аккаунт со страницы восстановления пароля")
    public void loginFromRecoveryPasswordPageTest() {
        WebDriver driver =driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        RecoveryPagePassword recoveryPagePassword = new RecoveryPagePassword(driver);
        mainPage.open();
        mainPage.clickLoginAccountButtonRight();
        logInPage.clickRecoveryButton();
        recoveryPagePassword.clickLoginButton();
        logInPage.fillKnownEmail(email);
        logInPage.fillKnownPassword(password);
        boolean button = logInPage.clickLoginButton();
        assertFalse(button);

    }


    @After
    public void tearDown() {
        userApi.deleteUser(email);
        driverSettings.getDriver().quit();
    }
}
