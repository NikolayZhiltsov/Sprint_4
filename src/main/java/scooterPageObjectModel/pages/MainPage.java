package scooterPageObjectModel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

//Класс локаторов кнопок создания заказа, аккордеона FAQ и методов их нажатия
public class MainPage {

    private WebDriver webDriver;

    //Локатор для кнопки "Заказать" в хедере
    private By headerOrderButtonLocator = By.xpath("//div[contains(@class, 'Header')]/button[text()='Заказать']");

    //Локатор для кнопки "Заказать" на основной странице
    private By mainOrderButtonLocator = By.xpath("//div[contains(@class, 'Home_Finish')]/button[text()='Заказать']");

    //Локатор для кнопки "да все привыкли"
    private By cookieButtonLocator = By.id("rcc-confirm-button");

    //Локатор для открытия вопроса аккордеона
    private String questionLocator = "//div[@id='accordion__heading-%s']";

    //Локатор для ответа аккоредеона
    private String answerLocator = "//div[contains(@id, 'accordion__panel')][.='%s']";


    public MainPage(WebDriver driver) {
        this.webDriver = driver;
    }

    //Метод клика по кнопке "Заказать" в хедере
    public void clickHeaderOrderButton() {
        WebElement clickHeaderOrder = webDriver.findElement(headerOrderButtonLocator);
        clickHeaderOrder.click();
    }

    //Метод клика по кнопке "да все привыкли"
    public void clickCookieButton() {
        WebElement clickCookie = webDriver.findElement(cookieButtonLocator);
        clickCookie.click();
    }

    //Метод клика по кнопке "Заказать" внизу основной страницы
    public void clickMainOrderButton() {
        WebElement clickMainOrder = webDriver.findElement(mainOrderButtonLocator);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", clickMainOrder);
        new WebDriverWait(webDriver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(clickMainOrder));
        clickMainOrder.click();
    }

    //Метод раскрытия вопросов Аккордеона
    public void expandQuestion(int questionId) {
        WebElement clickQuestion = webDriver.findElement(By.xpath((String.format(questionLocator, questionId))));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", clickQuestion);
        new WebDriverWait(webDriver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(clickQuestion));
        clickQuestion.click();
    }

    //Метод проверки соответствия вопроса и ответа
    public boolean answerIsDisplayed(String expectedAnswer) {
        WebElement answerCheck = webDriver.findElement(By.xpath((String.format(answerLocator, expectedAnswer))));
        return answerCheck.isDisplayed();
    }
}
