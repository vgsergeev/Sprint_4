package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static ru.praktikum.utils.EnvConfig.*;

public class OrderPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(IMPLICITY_TIMEOUT));
    }

    private final By stationContainer = By.cssSelector(".select-search__input");
    private final By dropdownListStations = By.cssSelector(".select-search__select");
    private final By buttonNextCustomerForm = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");
    private final By commentContainer = By.xpath(".//div[@class='Input_InputContainer__3NykH']/input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    private final By dateContainer = By.xpath(".//div[@class='react-datepicker__input-container']/input");
    private final By dropDownPlaceholder = By.cssSelector(".Dropdown-placeholder");
    private final By dropDownDurationOption = By.cssSelector(".Dropdown-option");
    private final By blackColouredScooter = By.cssSelector(".Checkbox_Input__14A2w[id='black']");
    private final By buttonOrderRentForm = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    private final By yesOrderButton = By.xpath(".//button[text()='Да']");
    private final By headerConfirmation = By.cssSelector(".Order_ModalHeader__3FDaJ");
    private final By customerContainersAll = By.cssSelector(".Input_Input__1iN_Z.Input_Responsible__1jDKN");


    //Методы для заполнения полей в форме заказчика
    public List<WebElement> customerContainers() {
        return driver.findElements(customerContainersAll);
    }

    public void sendDataCustomerContainers(String name, String surname, String address, String station, String phone) {
        customerContainers().get(0).sendKeys(name);
        customerContainers().get(1).sendKeys(surname);
        customerContainers().get(2).sendKeys(address);
        customerContainers().get(3).sendKeys(phone);
        driver.findElement(stationContainer).sendKeys(station);
    }

    public void clickOnDropdownListStations() {
       wait.until(ExpectedConditions.presenceOfElementLocated(dropdownListStations));
        driver.findElement(dropdownListStations).click();
    }

    public void clickOnButtonNextCustomerForm() {
        driver.findElement(buttonNextCustomerForm).click();
    }

    //Методы для заполнения полей в форме аренды самоката
    public void sendDataRentContainers(String date, String comment) {
        driver.findElement(dateContainer).sendKeys(date + Keys.ENTER);
        driver.findElement(commentContainer).sendKeys(comment);
    }

    public List<WebElement> getDurationOptions() {
        driver.findElement(dropDownPlaceholder).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(dropDownDurationOption));
        return driver.findElements(dropDownDurationOption);
    }

    public void clickOnBlackScooterCheckbox() {
        driver.findElement(blackColouredScooter).click();
    }

    public void clickOnButtonOrderRentForm() {
        driver.findElement(buttonOrderRentForm).click();
    }

    //Методы для подтверждения заказа
    public void clickOnYesOrderButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(yesOrderButton));
        driver.findElement(yesOrderButton).click();
    }

    public String getConfirmationText() {
        wait.until(ExpectedConditions.presenceOfElementLocated(headerConfirmation));
        return driver.findElement(headerConfirmation).getText();
    }
}