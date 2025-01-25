package page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LogInPage {
    private final WebDriver driver;
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.cssSelector("input[type='password']");
    private final By loginButton = By.xpath("//button[contains(@class, 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa')]");
    private final By recoveryLoginButton = By.xpath(".//a[contains(@href,'/forgot-password')]");
    private final By noUserLogin = By.xpath("//button[contains(@class, 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa')]");
    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнеить поле почта")
    public void fillKnownEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнить поле пароль")
    public void fillKnownPassword (String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать на кнопку логин")
    public boolean clickLoginButton () {
        driver.findElement(loginButton).click();
        return false;
    }

    @Step("Нажать на кнопку восстановление пароля")
    public void clickRecoveryButton () {
        driver.findElement(recoveryLoginButton).click();
    }


    public boolean isUserNoLoggedIn() {
        try {
            return driver.findElement(noUserLogin).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
