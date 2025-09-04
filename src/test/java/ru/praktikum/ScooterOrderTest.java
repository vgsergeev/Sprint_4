package ru.praktikum;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.pages.MainPage;
import ru.praktikum.pages.OrderPage;


@RunWith(Parameterized.class)
public class ScooterOrderTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phone;
    private final String date;
    private final int duration; // от 0 до 6 вкл (7 опций выбора длительности аренды самоката)
    private final String comment;

    public ScooterOrderTest (String name, String surname, String address, String station, String phone,
                             String date, int duration, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.duration = duration;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Егор", "Летов", "ул. Трофимова, д.10, кв. 67", "Технопарк", "+79278066209",
                        "03.09.2025", 4, "Перед приходом, позвоните"},
                {"Яна", "Дягилева", "Аминьевское шоссе, д. 26, кв. 7", "Славянский бульвар", "+79162419071",
                        "08.09.2025", 0, "" },
        };
    }

    @Test
    public void testFaqListHeader() {
        var driver = factory.getDriver();
        var mainPage = new MainPage(driver);

        //действия на главной странице
        mainPage.openMainPage();
        OrderPage orderPage =  mainPage.clickOnOrderButtonHeader();

        //Заполнение полей заказчика в форме заказчика
        orderPage.sendDataCustomerContainers(name, surname, address, station, phone);
        orderPage.clickOnDropdownListStations();
        orderPage.clickOnButtonNextCustomerForm();

        //Заполнение полей формы аренды
        orderPage.sendDataRentContainers(date, comment);
        orderPage.getDurationOptions().get(duration).click();
        orderPage.clickOnBlackScooterCheckbox();
        orderPage.clickOnButtonOrderRentForm();

        //Подтверждение заказа
        orderPage.clickOnYesOrderButton();

        //Проверка
        Assert.assertTrue("Заказ самоката не подтвержден", orderPage.getConfirmationText().contains("Заказ оформлен"));
    }

    @Test
    public void testFaqListMiddle() {
        var driver = factory.getDriver();
        var mainPage = new MainPage(driver);

        //действия на главной странице
        mainPage.openMainPage();
        OrderPage orderPage =  mainPage.clickOnOrderButtonMiddle();

        //Заполнение полей заказчика в форме заказчика
        orderPage.sendDataCustomerContainers(name, surname, address, station, phone);
        orderPage.clickOnDropdownListStations();
        orderPage.clickOnButtonNextCustomerForm();

        //Заполнение полей формы аренды
        orderPage.sendDataRentContainers(date, comment);
        orderPage.getDurationOptions().get(duration).click();
        orderPage.clickOnBlackScooterCheckbox();
        orderPage.clickOnButtonOrderRentForm();

        //Подтверждение заказа
        orderPage.clickOnYesOrderButton();

        //Проверка
        Assert.assertTrue("Заказ самоката не подтвержден", orderPage.getConfirmationText().contains("Заказ оформлен"));
    }
}