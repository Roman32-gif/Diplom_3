package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPagePassword {
    private final WebDriver driver;
    private final By loginButton = By.xpath("//a[contains(@href,'/login')]");

    public RecoveryPagePassword(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать на кнопку логин")
    public void clickLoginButton () {
        driver.findElement(loginButton).click();
    }
}
