package page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final WebDriver driver;
    private final By nameField = By.cssSelector("input[name='name']");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.cssSelector("input[type='password']");
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginButton = By.xpath(".//a[contains(@class, 'Auth_link__1fOlj')]");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Открыть страницу ресгистрации")
    public void open () {
        driver.get(Config.REGISTRATION_URL);
    }

    @Step("Заполнить поле имя")
    public void fillNameField (String userName) {
        driver.findElement(nameField).sendKeys(userName);
    }

    @Step("Заполнить поле почта")
    public void fillEmailField(String userEmail) {
        driver.findElement(emailField).sendKeys(userEmail);
    }

    @Step("Заполнить поле пароль")
    public void fillPasswordField (String userPassword) {
        driver.findElement(passwordField).sendKeys(userPassword);
    }

    @Step("Нажать на кнопку регистрация")
    public void clickRegistrationButton () {
        driver.findElement(registrationButton).click();
    }

    @Step("Нажать на кнопку логин")
    public void clickLoginButton (){
        driver.findElement(loginButton).click();
    }

    public boolean badRegistration() {
        try {
            return driver.findElement(By.xpath("//*[contains(@class, 'input__error text_type_main-default')]")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
