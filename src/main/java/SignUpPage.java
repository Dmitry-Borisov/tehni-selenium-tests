import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    By modalForm = By.xpath("//div[@id='modalContent']");
    By modalTitle = By.xpath("//span[@id='modal-title']");
    By modalClose = By.xpath("//a[@class='close']");
    By inputName = By.xpath("//input[@id='edit-field-first-name-und-0-value']");
    By inputEmail = By.xpath("//input[@id='edit-mail']");
    By inputPass = By.xpath("//input[@id='edit-pass']");
    By btnSignUp = By.xpath("//div[@id='modalContent']//input[@type='submit']");
    By inputCaptcha = By.xpath("//input[@id='edit-captcha-response']");
    By imgCaptcha = By.xpath("//div[@class='captcha']/img");
    By errorMessagesForm = By.xpath("//div[@class='messages error processed']");

    public void submit() {
        driver.findElement(btnSignUp).click();
    }

    public void hideErrorMessage(){
        driver.findElement(errorMessagesForm).click();
    }

    public void closeSignUpModal(){
        driver.findElement(modalClose).click();
    }
}
