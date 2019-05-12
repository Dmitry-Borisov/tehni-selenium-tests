import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

public class BaseFixture {

    static WebDriver driver;
    static WebDriverWait wait;
    static MainPage mainPage;
    static SignInPage signInPage;
    static SignUpPage signUpPage;
    static OfertaPage ofertaPage;
    static String firstTab;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        wait = (new WebDriverWait(driver, 20));
        mainPage = new MainPage(driver);
        signInPage = new SignInPage(driver);
        signUpPage = new SignUpPage(driver);
        ofertaPage = new OfertaPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://tehni.ru/");
        firstTab = getCurrentTabName();
    }

    @AfterClass
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

    public static String getCurrentTabName(){
        return (driver.getWindowHandle());
    }

    public static void switchToNextTab(){
        for (String tab : driver.getWindowHandles()){
            driver.switchTo().window(tab);
        };
    }
}
