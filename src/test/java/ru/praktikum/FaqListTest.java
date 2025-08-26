package ru.praktikum;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import ru.praktikum.pages.MainPage;
import static ru.praktikum.utils.FaqButtonsPanelsText.*;

@RunWith(Parameterized.class)
public class FaqListTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    private final String textFaqButton;
    private final String textFaqPanel;
    private final int indexFaqButtonPanel;

    public FaqListTest(String textFaqButton, String textFaqPanel, int indexFaqButtonPanel) {
        this.textFaqButton = textFaqButton;
        this.textFaqPanel = textFaqPanel;
        this.indexFaqButtonPanel = indexFaqButtonPanel;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {TEXT_FAQ_BUTTON[0], TEXT_FAQ_PANEL[0], 0},
                {TEXT_FAQ_BUTTON[1], TEXT_FAQ_PANEL[1], 1},
                {TEXT_FAQ_BUTTON[2], TEXT_FAQ_PANEL[2], 2},
                {TEXT_FAQ_BUTTON[3], TEXT_FAQ_PANEL[3], 3},
                {TEXT_FAQ_BUTTON[4], TEXT_FAQ_PANEL[4], 4},
                {TEXT_FAQ_BUTTON[5], TEXT_FAQ_PANEL[5], 5},
                {TEXT_FAQ_BUTTON[6], TEXT_FAQ_PANEL[6], 6},
                {TEXT_FAQ_BUTTON[7], TEXT_FAQ_PANEL[7], 7},
        };
    }

    @Test
    public void testFaqList() {
        var driver = factory.getDriver();
        var mainPage = new MainPage(driver);

        mainPage.openMainPage();
        //Получение кнопки и корреспондирующей панели из списков по индексу из тестовых параметров
        WebElement faqButton = mainPage.getFaqButtons().get(indexFaqButtonPanel);
        WebElement faqPanel = mainPage.getFaqPanels().get(indexFaqButtonPanel);
        //Сопоставление клика FAQ-кнопки с открытием корреспондирующей FAQ-панели по индексу элемента в списке
        mainPage.faqButtonScrollClick(faqButton);
        mainPage.checkFaqPanelIsDisplayed(faqPanel);
        //Проверка соответствия текста FAQ-кнопки и текста FAQ-панели с заданными текстовыми значениями
        Assert.assertEquals("Текст кнопки " + (indexFaqButtonPanel+1) + " в FAQ не совпадает", textFaqButton, faqButton.getText());
        Assert.assertEquals("Текст панели " + (indexFaqButtonPanel+1) + " в FAQ не совпадает", textFaqPanel, faqPanel.getText());
    }
}