import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
    private WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    By modalForm = By.xpath("//div[@id='modalContent']");
    By modalTitle = By.xpath("//span[@id='modal-title']");
    By modalClose = By.xpath("//a[@class='close']");
    By inputEmail = By.xpath("//input[@id='edit-name']");
    By inputPass = By.xpath("//input[@id='edit-pass']");
    By btnSignIn = By.xpath("//div[@id='modalContent']//input[@type='submit']");
    By errorMessagesForm = By.xpath("//div[@class='messages error processed']");
    By linkToRestorePass = By.xpath("//div[@class='messages error processed']//li/a");
    By linkToRegistration = By.xpath("//ul[@class='ajax-register-links inline']/li[@class='first']");
    By linkForgotPass = By.xpath("//ul[@class='ajax-register-links inline']/li[@class='last']");

    public void submit() {
        driver.findElement(btnSignIn).click();
    }

    public void hideErrorMessage(){
        driver.findElement(errorMessagesForm).click();
    }

    public void closeSignInModal(){
        driver.findElement(modalClose).click();
    }

}
