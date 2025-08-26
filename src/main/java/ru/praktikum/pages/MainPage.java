package ru.praktikum.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static ru.praktikum.utils.EnvConfig.*;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(IMPLICITY_TIMEOUT));
    }

    //Кнопки Заказать на главной странице
    private final By orderButtonHeader = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    private final By orderButtonMiddle = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By faqButtonsAll = By.cssSelector(".accordion__button");
    private final By faqPanelsAll = By.cssSelector(".accordion__panel");
    private final By cookieYesButton = By.cssSelector(".App_CookieButton__3cvqF");

    // Метод для открытия главной страницы
    public void openMainPage() {
        driver.get(BASE_URL);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(cookieYesButton));
        driver.findElement(cookieYesButton).click();
    }


    // Методы для области Вопросы о важном (FAQ) главной страницы

    public List<WebElement> getFaqButtons() {
        return driver.findElements(faqButtonsAll);
    }

    public List<WebElement> getFaqPanels() {
        return driver.findElements(faqPanelsAll);
    }

    public void faqButtonScrollClick(WebElement faqButton) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", faqButton);
        faqButton.click();
    }

    public void checkFaqPanelIsDisplayed(WebElement faqPanel) {
        wait.until(ExpectedConditions.visibilityOf(faqPanel));
        Assert.assertTrue(faqPanel.isDisplayed());
    }

    // Метод для перехода на страницу Заказа (две кнопки)

    public OrderPage clickOnOrderButtonHeader() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButtonHeader));
        driver.findElement(orderButtonHeader).click();
        return new OrderPage(driver);
    }

    public OrderPage clickOnOrderButtonMiddle() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButtonMiddle));
        driver.findElement(orderButtonMiddle).click();
        return new OrderPage(driver);
    }
}