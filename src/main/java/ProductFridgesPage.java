import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Locatable;

import java.util.List;

public class ProductFridgesPage {

    WebDriver driver;
    public ProductFridgesPage(WebDriver driver) {
        this.driver = driver;
    }

    By checkboxColorList = By.xpath("//fieldset[@id='edit-field15']/descendant::input");
    String checkboxColor = "//fieldset[@id='edit-field15']/descendant::label[text()='%s']/preceding-sibling::input";
    By filterColor = By.xpath("//div[text()='Фильтр:']//h3[text()='Цвет:'] ");
    By blockSideMenu = By.xpath("//div[@class='fieldset-wrapper']");
    By resetBtn = By.xpath("//input[@id='edit-reset']");
    By findBtn = By.xpath("//input[@id='edit-submit--2']");
    By buyBtn = By.xpath("//div[contains(@class, 'shop-catalog-products')]/descendant::div/a[text()='Купить']");
    By backToSiteBtn = By.xpath("//a[text()='Вернуться на сайт']");
    By tableString = By.xpath("//table[@class='shop-cart-items']/descendant::tbody/tr");
    By deleteGoodsBtn = By.xpath("//table[@class='shop-cart-items']/descendant::tbody/tr//a[text()='Удалить товар']");
    String deliveryOption = "//td[@class='delivery-options']//select/option[text()='%s']";
    By deliveryNotice = By.xpath("//td[@class='delivery-options']/div[@class='notice']");
    By emptyMessage = By.xpath("//div[@class='shop-cart-empty']");
    By evenString = By.xpath("//tbody/tr[@class='even']");

    public final String URL = "http://tehni.ru/catalog/holodilniki";
    public final String SILVER_COLOR = "серебристый ";
    public final String TITLE = "Корзина | Техни.ру";
    public final String DELIVERY_ADDRESS = "Самовывоз, ул. Тверская, 81";
    public final String NOTICE = "Осмотр и вынос техники осуществляется самостоятельно.";
    public final String MESSAGE = "Ваша корзина пуста.";

    public void toFind(){
        driver.findElement(findBtn).click();
    }

    public void resetFilters(){
        driver.findElement(resetBtn).click();
    }

    public void chooseFridgesByColor(String color) {
        driver.findElement(By.xpath(String.format(checkboxColor, color))).click();
    }

    public void scrollToSideBlockMenu(int blockNumber){
        WebElement sideBlockMenu = driver.findElements(blockSideMenu).get(blockNumber);
        ((Locatable)sideBlockMenu).getCoordinates().inViewPort();
    }

    public void clickBuyBtn(int numBtn){
        List<WebElement> btns = driver.findElements(buyBtn);
        btns.get(numBtn).click();
    }

    public void chooseDeliveryOption(String address) {
        driver.findElement(By.xpath(String.format(deliveryOption, address))).click();
    }

    public void deleteGoods(int numBtn) {
        List<WebElement> btns = driver.findElements(deleteGoodsBtn);
        btns.get(numBtn).click();
    }
}
  