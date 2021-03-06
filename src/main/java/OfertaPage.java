import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OfertaPage {
    private WebDriver driver;
    public OfertaPage(WebDriver driver) {
        this.driver = driver;
    }

    By phoneInFooter = By.xpath("//div[@class='region region-footer']/descendant::strong/span");
    By bottomTopButton = By.xpath("//a[@id='bottom-top-button']");
    By titleContent = By.xpath("//h1[text()='Публичный Договор-оферта']");

    public final String OFERTA_PAGE = "http://tehni.ru/oferta";
    public final String PHONE = "Звоните по телефону: 663-500!";
    public final String HOME_PAGE = "http://tehni.ru/";
}
