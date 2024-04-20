package scooterTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import scooterPageObjectModel.pages.MainPage;
import scooterPageObjectModel.pages.OrderPage;
import scooterPageObjectModel.WebDriverFactory;

import static org.junit.Assert.assertTrue;

public class OrderTest {
    private static final String BROWSER = "chrome";
    private WebDriver webDriver;

    @Before
    public void setup() {
        webDriver = WebDriverFactory.getWebDriver(BROWSER);
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void orderFromHeaderButton() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickHeaderOrderButton();

        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.fillCustomerInfo("Имя", "Фамилия", "Абельмана, 47", "77, 190, 82", "Водный стадион", "12345678901");
        orderPage.clickNextButton();
        orderPage.fillDatePeriodInfo("02.05.2035","двое суток");
        orderPage.createOrder();
        assertTrue(orderPage.successOrderCreated());
    }

    @Test
    public void orderFromMainButton() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickCookieButton();
        mainPage.clickMainOrderButton();

        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.fillCustomerInfo("Пирожок", "Сладкий", "Повидловая, 2", "217, 43, 44", "Воробьёвы горы", "88002000600");
        orderPage.clickNextButton();
        orderPage.fillDatePeriodInfo("25.12.2024","шестеро суток");
        orderPage.createOrder();
        assertTrue(orderPage.successOrderCreated());
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
