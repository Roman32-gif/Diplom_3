import generate.random.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.objects.RegistrationPage;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static page.objects.Config.REGISTRATION_URL;

public class RegisterNewUserTest {
    private final DriverBrowsers driverSettings = new DriverBrowsers();
    private UserApi userApi;
    private String userEmail;
    private String userPassword;
    private String userName;



    @Before
    public void startUp(){
        driverSettings.initYandexBrowser();
        userApi = new UserApi();
        userEmail = "user" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
        userPassword = UUID.randomUUID().toString();
        userName = "User_" + UUID.randomUUID().toString().substring(0, 8);
    }


    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Успешная регистрация пользователя с валидными данными")
    public void registerCorrectTest() {
        WebDriver driver =driverSettings.getDriver();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.fillNameField(userName);
        registrationPage.fillEmailField(userEmail);
        registrationPage.fillPasswordField(userPassword);
        registrationPage.clickRegistrationButton();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("URL страницы после регистрации должен быть равен AUTHORISATION_URL", "https://stellarburgers.nomoreparties.site/register", currentUrl);

    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Не успешная решистрация пользователя при вводе невалидных данных")
    public void registerBadTest() {
        WebDriver driver =driverSettings.getDriver();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.fillNameField(userName);
        registrationPage.fillEmailField(userEmail);
        registrationPage.fillPasswordField("12345");
        registrationPage.clickRegistrationButton();
        assertTrue(registrationPage.badRegistration());
    }


    @After
    public void tearDown() {
        userApi.deleteUser(userEmail);
        driverSettings.getDriver().quit();
    }

}
