package page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;
    private final By loginAccountButtonRight = By.xpath(".//*[@href=\"/account\"]");
    private final By loginAccountButtonDown = By.xpath(".//button[text()=\"Войти в аккаунт\"]");
    private final By logoButton = By.xpath(".//*[local-name()='svg' and @height = '50']");
    private final By sauceButton = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect')]/span[contains(@class, 'text text_type_main-default') and text() ='Соусы']");
    private final By breadButton = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect')]/span[contains(@class, 'text text_type_main-default') and text() ='Булки']");
    private final By fillingButton = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect')]/span[contains(@class, 'text text_type_main-default') and text() ='Начинки']");
    private final By constructorButton = By.xpath(".//*[contains(@class, 'AppHeader_header__link__3D_hX')]");
    private final By logOutButton = By.xpath(".//button[text()=\"Выход\"]");
    private final By breadExample = By.xpath("//div[contains(@class, 'current')]/span[contains(@class, 'text text_type_main-default') and text() ='Булки']");
    private final By sauceExample = By.xpath("//div[contains(@class, 'current')]/span[contains(@class, 'text text_type_main-default') and text() ='Соусы']");
    private final By fillingExample = By.xpath("//div[contains(@class, 'current')]/span[contains(@class, 'text text_type_main-default') and text() ='Начинки']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие сайта")
    public void open () {
        driver.get(Config.BASE_URL);
    }

    @Step("Нажатие на кнопку логина в аккаунт по середени экрана")
    public void clickLoginAccountButton () {
        driver.findElement(loginAccountButtonDown).click();
    }

    @Step("Нажатие на кнопку логина в аккаунт справа сверху экрана")
    public void clickLoginAccountButtonRight () {
        driver.findElement(loginAccountButtonRight).click();
    }

    @Step("нажатие на конструктор")
    public void clickOnConstructorButton () {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на логотип")
    public void clickOnLogo () {
        driver.findElement(logoButton).click();
    }


    @Step("Нажатие на соус")
    public void clickOnSauce() {
        driver.findElement(sauceButton).click();
    }

    @Step("Нажать на булку")
    public void clickOnBread() {
        driver.findElement(breadButton).click();
    }

    @Step("Нажатие на начинку")
    public void clickOnFilling () {
        driver.findElement(fillingButton).click();
    }

    @Step("Нажатие на кнопку выхода")
    public void clickButtonLogOut() {
        driver.findElement(logOutButton).click();
    }


    public boolean IsFillingOnMain() {
        try {
            return driver.findElement(fillingExample).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean IsBreadOnMain() {
        try {
            return driver.findElement(breadExample).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public boolean IsSauceOnMain() {
        try {
            return driver.findElement(sauceExample).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
