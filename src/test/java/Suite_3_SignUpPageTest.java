import org.testng.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Suite_3_SignUpPageTest extends BaseFixture{

    private static final Map<String, String> signUpData;
    static {
        signUpData = new HashMap();
        signUpData.put("name", "Ivan");
        signUpData.put("email", "q@q.com");
        signUpData.put("pass", "123");
        signUpData.put("captcha", "000a");
    };

    private String[] errorMessages = {
            "Поле Имя обязательно для заполнения.",
            "Поле E-mail обязательно для заполнения.",
            "Поле Пароль обязательно для заполнения.",
            "Поле Какой код на картинке? обязательно для заполнения.",
            "Вы ввели неправильный ответ на контрольный вопрос."
    };

    Date dateNow = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-hh.mm.ss");
    SoftAssert s = new SoftAssert();

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(! result.isSuccess()){
            TakesScreenshot screenshot = (TakesScreenshot)driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File("G:\\Arbeit\\github\\tehni-selenium-tests\\screenshots\\" + result.getName() + "-" + format.format(dateNow) + ".png"));
            System.out.println("Test failed. Get a screenshot");
        }
    }

    @Test(priority = 33)
    public void signUpModalIsDisplayed(){
        mainPage.goToSignUpPage();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(signUpPage.modalForm)).isDisplayed());
    }

    @Test(priority = 34)
    public void checkModalTitle(){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(signUpPage.modalTitle,"Регистрация"));
        String title = driver.findElement(signUpPage.modalTitle).getText();
        Assert.assertEquals(title, "Регистрация");
    }

    @Test(priority = 35)
    public void modalCloseActionIsDisplayed(){
        Assert.assertTrue(driver.findElement(signUpPage.modalClose).isDisplayed());
    }

    @Test(priority = 36)
    public void inputNameIsDisplayed(){
        Assert.assertTrue(driver.findElement(signUpPage.inputName).isDisplayed());
    }

    @Test(priority = 37)
    public void inputEmailIsDisplayed(){
        Assert.assertTrue(driver.findElement(signUpPage.inputEmail).isDisplayed());
    }

    @Test(priority = 38)
    public void inputPassIsDisplayed(){
        Assert.assertTrue(driver.findElement(signUpPage.inputPass).isDisplayed());
    }

    @Test(priority = 39)
    public void signInBtnIsDisplayed(){
        Assert.assertTrue(driver.findElement(signUpPage.btnSignUp).isDisplayed());
    }

    @Test(priority = 40)
    public void inputCaptchaIsDisplayed(){
        Assert.assertTrue(driver.findElement(signUpPage.inputCaptcha).isDisplayed());
    }

    @Test(priority = 41)
    public void imageCaptchaIsDisplayed(){
        Assert.assertTrue(driver.findElement(signUpPage.imgCaptcha).isDisplayed());
    }

    @Test(priority = 42)
    public void signUpWithoutData(){
        signUpPage.submit();
        s.assertTrue(driver.findElement(signInPage.errorMessagesForm).isDisplayed());
        WebElement errorForm = driver.findElement(signInPage.errorMessagesForm);
        s.assertTrue(wait.until(ExpectedConditions.stalenessOf(errorForm)));
        s.assertAll();
        signUpPage.closeSignUpModal();
    }

    @Test(priority = 43)
    public void signUpOnlyWithName(){
        mainPage.goToSignUpPage();
        driver.findElement(signUpPage.inputName).sendKeys(signUpData.get("name"));
        signUpPage.submit();

        String errorText = driver.findElement(signUpPage.errorMessagesForm).getText();
        s.assertTrue(errorText.contains(errorMessages[1]));
        s.assertTrue(errorText.contains(errorMessages[2]));
        s.assertTrue(errorText.contains(errorMessages[3]));
        s.assertAll();
        signUpPage.hideErrorMessage();
        signUpPage.closeSignUpModal();
    }

    @Test(priority = 44)
    public void signUpOnlyWithEmail(){
        mainPage.goToSignUpPage();
        driver.findElement(signUpPage.inputEmail).sendKeys(signUpData.get("email"));
        signUpPage.submit();

        String errorText = driver.findElement(signUpPage.errorMessagesForm).getText();
        s.assertTrue(errorText.contains(errorMessages[0]));
        s.assertTrue(errorText.contains(errorMessages[2]));
        s.assertTrue(errorText.contains(errorMessages[3]));
        s.assertAll();
        signUpPage.hideErrorMessage();
        signUpPage.closeSignUpModal();
    }

    @Test(priority = 45)
    public void signUpOnlyWithPass(){
        mainPage.goToSignUpPage();
        driver.findElement(signUpPage.inputPass).sendKeys(signUpData.get("pass"));
        signUpPage.submit();

        String errorText = driver.findElement(signUpPage.errorMessagesForm).getText();
        s.assertTrue(errorText.contains(errorMessages[0]));
        s.assertTrue(errorText.contains(errorMessages[1]));
        s.assertTrue(errorText.contains(errorMessages[3]));
        s.assertAll();
        signUpPage.hideErrorMessage();
        signUpPage.closeSignUpModal();
    }

    @Test(priority = 46)
    public void signUpWithIncorrectCaptcha(){
        mainPage.goToSignUpPage();
        driver.findElement(signUpPage.inputName).sendKeys(signUpData.get("name"));
        driver.findElement(signUpPage.inputEmail).sendKeys(signUpData.get("email"));
        driver.findElement(signUpPage.inputPass).sendKeys(signUpData.get("pass"));
        driver.findElement(signUpPage.inputCaptcha).sendKeys(signUpData.get("captcha"));
        signUpPage.submit();

        String errorText = driver.findElement(signUpPage.errorMessagesForm).getText();
        Assert.assertTrue(errorText.contains(errorMessages[4]));
        signUpPage.hideErrorMessage();
        signUpPage.closeSignUpModal();
    }
}
