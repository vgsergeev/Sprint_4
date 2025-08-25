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
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Кнопки Заказать на главной странице
    private final By orderButtonHeader = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    private final By orderButtonMiddle = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Метод для открытия главной страницы
    public void openMainPage() {
        driver.get(BASE_URL);
    }


    // Методы для области Вопросы о важном (FAQ) главной страницы

    public List<WebElement> getFaqButtons() {
        return driver.findElements(By.cssSelector(".accordion__button"));
    }

    public List<WebElement> getFaqPanels() {
        return driver.findElements(By.cssSelector(".accordion__panel"));
    }

    public void faqButtonScrollClick(WebElement faqButton) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", faqButton);
        faqButton.click();
    }

    public void checkFaqPanelIsDisplayed(WebElement faqPanel) {
        new WebDriverWait(driver, Duration.ofSeconds(IMPLICITY_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(faqPanel));
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