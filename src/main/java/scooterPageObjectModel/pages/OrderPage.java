package scooterPageObjectModel.pages;

import org.openqa.selenium.*;

//Класс локаторов полей и кнопок страницы заказа и методов их заполнения
public class OrderPage {

    private WebDriver webDriver;

    //Локатор для поля "Имя"
    private By nameInputLocator = By.xpath("//input[@placeholder='* Имя']");

    //Локатор для поля "Фамилия"
    private By lastnameInputLocator = By.xpath("//input[@placeholder='* Фамилия']");

    //Локатор для поля "Адрес"
    private By addressInputLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    //Локатор для поля "Станция метро"
    private By subwayInputLocator = By.xpath("//input[@placeholder='* Станция метро']");

    //Локатор выбора станции метро из дропдауна
    private String subwayTitleLocator = "//*[@style='background-color: rgb(%s);']/ancestor::button[contains(@class, Order_SelectOption)]/div[text()='%s']";

    //Локатор для поля "Телефон"
    private By phoneInputLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Локатор для кнопки "Далее"
    private By nextButtonLocator = By.xpath("//button[text()='Далее']");

    //Локатор для поля "Когда привезти"
    private By dateInputLocator = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //Локатор для поля "Срок аренды"
    private By rentPeriodLocator = By.xpath("//span[@class='Dropdown-arrow']");

    //Локатор для выбора срока аренды
    private String rentPeriodSelectorLocator = "//div[@class='Dropdown-option' and text()='%s']";

    //Локатор для кнопки "Заказать"
    private By createOrderButtonLocator = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    //Локатор для кнопки "Да"
    private By confirmOrderButton = By.xpath("//button[text()='Да']");

    //Локатор сообщение "Заказ оформлен"
    private By orderCreatedLocator = By.xpath("//div[text()='Заказ оформлен']");


    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Метод заполнения полей первой страницы заказа
    public void fillCustomerInfo(String name, String lastname, String address, String subWayColor, String subwayTitle, String phone) {
        WebElement nameInput = webDriver.findElement(nameInputLocator);
        nameInput.sendKeys(name);

        WebElement lastnameInput = webDriver.findElement(lastnameInputLocator);
        lastnameInput.sendKeys(lastname);

        WebElement addressInput = webDriver.findElement(addressInputLocator);
        addressInput.sendKeys(address);

        WebElement subWayInput = webDriver.findElement(subwayInputLocator);
        subWayInput.click();

        WebElement subWaySelectStation = webDriver.findElement(By.xpath((String.format(subwayTitleLocator, subWayColor, subwayTitle))));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", subWaySelectStation);
        subWaySelectStation.click();

        WebElement phoneInput = webDriver.findElement(phoneInputLocator);
        phoneInput.sendKeys(phone);

    }

    //Метод клика по кнопке "Далее"
    public void clickNextButton() {
        WebElement clickNextButton = webDriver.findElement(nextButtonLocator);
        clickNextButton.click();
    }

    //Метод заполнения полей второй страницы заказа
    public void fillDatePeriodInfo(String date, String period) {
        WebElement dateInput = webDriver.findElement(dateInputLocator);
        dateInput.sendKeys(date);

        WebElement rentPeriodInput = webDriver.findElement(rentPeriodLocator);
        rentPeriodInput.click();

        WebElement rentPeriodSelect = webDriver.findElement(By.xpath((String.format(rentPeriodSelectorLocator, period))));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", rentPeriodSelect);
        rentPeriodSelect.click();
    }

    //Метод создания заказа
    public void createOrder() {
        WebElement clickCreateOrderButton = webDriver.findElement(createOrderButtonLocator);
        clickCreateOrderButton.click();

        WebElement clickConfirmOrderButton = webDriver.findElement(confirmOrderButton);
        clickConfirmOrderButton.click();
    }

    //Метод оценки успешности заказа
    public boolean successOrderCreated() {
        WebElement successOrder = webDriver.findElement(orderCreatedLocator);
        successOrder.isDisplayed();
        return true;
    }
}
