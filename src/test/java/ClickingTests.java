import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.LogInPage;
import pageObjects.MainPage;

import java.util.concurrent.TimeUnit;

public class ClickingTests {
    private final DriverBrowsers driverSettings = new DriverBrowsers();

    @Before
    public void startUp () {
        driverSettings.initYandexBrowser();
    }

    @Test
    public void clickOnPersonalAccount() {
        WebDriver driver =driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButtonRight();
    }

    @Test
    public void clickOnConstructorFromPersonalAccount() throws InterruptedException {
        WebDriver driver =driverSettings.getDriver();
        LogInPage logInPage = new LogInPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButtonRight();
        logInPage.fillKnownEmail("TopPlayerAlfred@yandex.ru");
        logInPage.fillKnownPassword("1234567");
        logInPage.clickLoginButton();
        mainPage.clickLoginAccountButtonRight();
        TimeUnit.SECONDS.sleep(2);
        mainPage.clickOnConstructorButton();

    }

    @Test
    public void clickOnLogoFromPersonalAccount() throws InterruptedException {
        WebDriver driver =driverSettings.getDriver();
        LogInPage logInPage = new LogInPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButtonRight();
        logInPage.fillKnownEmail("TopPlayerAlfred@yandex.ru");
        logInPage.fillKnownPassword("1234567");
        logInPage.clickLoginButton();
        mainPage.clickLoginAccountButtonRight();
        TimeUnit.SECONDS.sleep(2);
        mainPage.clickOnLogo();
    }

    @Test
    public void clickOnButtonFormPersonalAccountToLeaveAnAccount() throws InterruptedException {
        WebDriver driver =driverSettings.getDriver();
        LogInPage logInPage = new LogInPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButtonRight();
        logInPage.fillKnownEmail("TopPlayerAlfred@yandex.ru");
        logInPage.fillKnownPassword("1234567");
        TimeUnit.SECONDS.sleep(2);
        logInPage.clickLoginButton();
        mainPage.clickLoginAccountButtonRight();
        TimeUnit.SECONDS.sleep(2);
        mainPage.clickButtonLogOut();

    }

    @Test
    public void clickingBetweenTerms () throws InterruptedException {
        WebDriver driver =driverSettings.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOnSauce();
        TimeUnit.SECONDS.sleep(2);
        mainPage.clickOnBread();
        TimeUnit.SECONDS.sleep(2);
        mainPage.clickOnFilling();
    }

    @After
    public void tearDown() {
        driverSettings.getDriver().quit();
    }
}
