import generate.random.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.objects.LogInPage;
import page.objects.MainPage;
import page.objects.RecoveryPagePassword;
import page.objects.RegistrationPage;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginUserTest {
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
    @DisplayName("Вход в аккаунт")
    @Description("Успешный вход в аккаунт с главной страницы")
    public void loginFromMainPageTest() {
        WebDriver driver =driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();
        logInPage.fillKnownEmail(userEmail);
        logInPage.fillKnownPassword(userPassword);
        logInPage.clickLoginButton();
        mainPage.clickLoginAccountButtonRight();
        assertEquals("https://stellarburgers.nomoreparties.site/account/profile", "https://stellarburgers.nomoreparties.site/account/profile");

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
        logInPage.fillKnownEmail(userEmail);
        logInPage.fillKnownPassword(userPassword);
        logInPage.clickLoginButton();
        assertEquals("https://stellarburgers.nomoreparties.site/account/profile", "https://stellarburgers.nomoreparties.site/account/profile");
    }

    @Test
    @DisplayName("Вход в аккаунт")
    @Description("Успешный вход в аккаунт со страницы регистрации")
    public void loginFromButtonInRegistrationPageTest() {
        WebDriver driver =driverSettings.getDriver();
        LogInPage logInPage = new LogInPage(driver);
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.clickLoginButton();
        logInPage.fillKnownEmail(userEmail);
        logInPage.fillKnownPassword(userPassword);
        logInPage.clickLoginButton();
        assertEquals("https://stellarburgers.nomoreparties.site/account/profile", "https://stellarburgers.nomoreparties.site/account/profile");
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
        logInPage.fillKnownEmail(userEmail);
        logInPage.fillKnownPassword(userPassword);
        logInPage.clickLoginButton();
        assertEquals("https://stellarburgers.nomoreparties.site/account/profile", "https://stellarburgers.nomoreparties.site/account/profile");

    }


    @After
    public void tearDown() {
        userApi.deleteUser(userEmail);
         driverSettings.getDriver().quit();
    }
}
