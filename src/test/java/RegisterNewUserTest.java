import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.RegistrationPage;
import pageObjects.User;

public class RegisterNewUserTest {
    private final DriverBrowsers driverSettings = new DriverBrowsers();
    private User user;

    @Before
    public void startUp(){
        driverSettings.initYandexBrowser();
        user = GenerateRandom.RandomUser.getRandomUser();
    }


    @Test
    public void registerCorrectTest() {
        WebDriver driver =driverSettings.getDriver();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.fillNameField(user.getUserName());
        registrationPage.fillEmailField(user.getUserEmail());
        registrationPage.fillPasswordField(user.getUserPassword());
        registrationPage.clickRegistrationButton();
    }

    @Test
    public void registerBadTest() {
        WebDriver driver =driverSettings.getDriver();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.fillNameField(user.getUserName());
        registrationPage.fillEmailField(user.getUserEmail());
        registrationPage.fillPasswordField("12345");
        registrationPage.clickRegistrationButton();
    }


    @After
    public void tearDown() {
        driverSettings.getDriver().quit();
    }

}
