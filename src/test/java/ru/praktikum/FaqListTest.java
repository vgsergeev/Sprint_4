package ru.praktikum;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import ru.praktikum.pages.MainPage;
import static ru.praktikum.utils.FaqButtonsPanelsText.*;

public class FaqListTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testFaqList() {
        var driver = factory.getDriver();
        var mainPage = new MainPage(driver);

        mainPage.openMainPage();
        for (WebElement faqButton : mainPage.getFaqButtons()) {
            int i = mainPage.getFaqButtons().indexOf(faqButton);
            mainPage.faqButtonScrollClick(faqButton);
            //Сопоставление клика FAQ-кнопки с открытием корреспондирующей FAQ-панели по индексу элемента в списке
            mainPage.checkFaqPanelIsDisplayed(mainPage.getFaqPanels().get(i));
            //Проверка соответствия текста FAQ-кнопки и текста FAQ-панели с заданными текстовыми значениями
            Assert.assertEquals("Текст кнопки " + i + " в FAQ не совпадает", TEXT_FAQ_BUTTON[i], faqButton.getText());
            Assert.assertEquals("Текст панели " + i + " в FAQ не совпадает", TEXT_FAQ_PANEL[i], mainPage.getFaqPanels().get(i).getText());
        }
    }
}