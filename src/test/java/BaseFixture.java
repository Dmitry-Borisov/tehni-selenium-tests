import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

public class BaseFixture {

    static WebDriver driver = new ChromeDriver();
    static WebDriverWait wait = (new WebDriverWait(driver, 20));
    static MainPage mainPage = new MainPage(driver);
    static SignInPage signInPage = new SignInPage(driver);
    static SignUpPage signUpPage = new SignUpPage(driver);

    @BeforeClass
    public void setUp(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://tehni.ru/");
    }

    @AfterClass
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
