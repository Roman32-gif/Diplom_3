import generate.random.RandomUser;
import generate.random.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.objects.RegistrationPage;
import page.objects.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static page.objects.Config.REGISTRATION_URL;

public class RegisterNewUserTest {
    private final DriverBrowsers driverSettings = new DriverBrowsers();
    private UserApi userApi;
    private String email;
    private String password;
    private String name;



    @Before
    public void startUp(){
        driverSettings.initYandexBrowser();
        userApi = new UserApi();
        User createUser = userApi.createUser();
        email = createUser.getEmail();
        password = createUser.getPassword();
        name = createUser.getName();

    }


    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Успешная регистрация пользователя с валидными данными")
    public void registerCorrectTest() {
        WebDriver driver =driverSettings.getDriver();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.fillNameField(name);
        registrationPage.fillEmailField(email);
        registrationPage.fillPasswordField(password);
        registrationPage.clickRegistrationButton();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("URL страницы после регистрации должен быть равен AUTHORISATION_URL", REGISTRATION_URL, currentUrl);

    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Не успешная регистрация пользователя при вводе невалидных данных")
    public void registerBadTest() {
        WebDriver driver =driverSettings.getDriver();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.fillNameField(name);
        registrationPage.fillEmailField(email);
        registrationPage.fillPasswordField("12345");
        registrationPage.clickRegistrationButton();
        assertTrue(registrationPage.badRegistration());
    }


    @After
    public void tearDown() {
        userApi.deleteUser(email);
        driverSettings.getDriver().quit();
    }

}
