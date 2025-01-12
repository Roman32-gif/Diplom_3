import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.LogInPage;
import pageObjects.MainPage;
import pageObjects.RecoveryPagePassword;
import pageObjects.RegistrationPage;

public class LoginUserTest {
    private final DriverBrowsers driverSettings = new DriverBrowsers();

    @Before
    public void startUp () {
        driverSettings.initYandexBrowser();
    }

    @Test
    public void loginFromMainPage() {
        WebDriver driver =driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();
        logInPage.fillKnownEmail("TopPlayerAlfred@yandex.ru");
        logInPage.fillKnownPassword("1234567");
        logInPage.clickLoginButton();

    }

    @Test
    public void loginFromMainPageRightUpButton() {
        WebDriver driver =driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButtonRight();
        logInPage.fillKnownEmail("TopPlayerAlfred@yandex.ru");
        logInPage.fillKnownPassword("1234567");
        logInPage.clickLoginButton();
    }

    @Test
    public void loginFromButtonInRegistrationPage() {
        WebDriver driver =driverSettings.getDriver();
        LogInPage logInPage = new LogInPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.clickLoginButton();
        logInPage.fillKnownEmail("TopPlayerAlfred@yandex.ru");
        logInPage.fillKnownPassword("1234567");
        logInPage.clickLoginButton();
    }

    @Test
    public void loginFromRecoveryPasswordPage () {
        WebDriver driver =driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        RecoveryPagePassword recoveryPagePassword = new RecoveryPagePassword(driver);
        mainPage.open();
        mainPage.clickLoginAccountButtonRight();
        logInPage.clickRecoveryButton();
        recoveryPagePassword.clickLoginButton();
        logInPage.fillKnownEmail("TopPlayerAlfred@yandex.ru");
        logInPage.fillKnownPassword("1234567");
        logInPage.clickLoginButton();

    }


    @After
    public void tearDown() {
        driverSettings.getDriver().quit();
    }
}
