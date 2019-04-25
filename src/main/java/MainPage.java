import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver=driver;
    }

    // header menu
    By companyLogo = By.xpath("//div[@id='header']/a[@id='logo']");
    By companyPhone = By.xpath("//div[@id='header']//div[@class='number']/a");
    By callMeBtn = By.xpath("//div[@id='header']//div[@class='callme-button']/a");
    By workTime = By.xpath("//div[@id='header']//div[@class='work-time']/a");
    By signInLink = By.xpath("//div[@id='header']//li[@class='first']/a");
    By signUpLink = By.xpath("//div[@id='header']//li[@class='last']/a");
    By basketLink = By.xpath("//div[@id='header']//li[@class='view_cart first']");
    By goodsNumber = By.xpath("//div[@id='header']//div[@class='field-item even']/a");
    By topMenu = By.xpath("//div[@id='header']//div[@class='region region-top-menu']//li");

    // main menu
    By searchForm = By.xpath("//div[@id='block-search-form']//input[@name='search_block_form']");
    By searchBtn = By.xpath("//div[@id='block-search-form']//input[@id='edit-submit']");
    By catalogLink = By.xpath("//a[@id='catalog-link']");
    By mainMenuCategories = By.xpath("//div[@id='block-menu-menu-header-menu']//li");
    By catalogCategories = By.xpath("//ul[@class='shop-categories']/li");

}