import org.junit.Assert;
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

public class SignInPageTest extends BaseFixture {

    Date dateNow = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-hh.mm.ss");
    SoftAssert s = new SoftAssert();

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(! result.isSuccess()){
            TakesScreenshot screenshot = (TakesScreenshot)driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File("G:\\Arbeit\\github\\tehni-selenium-tests\\screenshots" + result.getName() + "-" + format.format(dateNow) + ".png"));
            System.out.println("Test failed. Get a screenshot");
        }
    }

    @Test(priority = 1)
    public void signInModalIsDisplayed(){
        mainPage.goToSignInPage();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(signInPage.modalForm)).isDisplayed());
    }

    @Test(priority = 2)
    public void checkModalTitle(){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(signInPage.modalTitle,"Войти"));
        String title = driver.findElement(signInPage.modalTitle).getText();
        Assert.assertEquals("Войти", title);
    }

    @Test(priority = 3)
    public void modalCloseActionIsDisplayed(){
        Assert.assertTrue(driver.findElement(signInPage.modalClose).isDisplayed());
    }

    @Test(priority = 4)
    public void inputEmailIsDisplayed(){
        Assert.assertTrue(driver.findElement(signInPage.inputEmail).isDisplayed());
    }

    @Test(priority = 5)
    public void inputPassIsDisplayed(){
        Assert.assertTrue(driver.findElement(signInPage.inputPass).isDisplayed());
    }

    @Test(priority = 6)
    public void signInBtnIsDisplayed(){
        Assert.assertTrue(driver.findElement(signInPage.btnSignIn).isDisplayed());
    }

    @Test(priority = 7)
    public void linkToRegistrationIsDisplayed(){
        Assert.assertTrue(driver.findElement(signInPage.linkToRegistration).isDisplayed());
    }

    @Test(priority = 8)
    public void linkForgotPassIsDisplayed(){
        Assert.assertTrue(driver.findElement(signInPage.linkForgotPass).isDisplayed());
    }

    @Test(priority = 9)
    public void signInWithoutData(){
        signInPage.submit();
        s.assertTrue(driver.findElement(signInPage.errorMessagesForm).isDisplayed());
        WebElement errorForm = driver.findElement(signInPage.errorMessagesForm);
        s.assertTrue(wait.until(ExpectedConditions.stalenessOf(errorForm)));
        signInPage.closeSignInModal();
    }

    @Test(priority = 10)
    public void signInWithoutEmail(){
        mainPage.goToSignInPage();
        driver.findElement(signInPage.inputPass).sendKeys("123");
        signInPage.submit();
        String errorText = driver.findElement(signInPage.errorMessagesForm).getText();
        Assert.assertTrue(errorText.contains("Поле E-mail обязательно для заполнения."));
        signInPage.hideErrorMessage();
        signInPage.closeSignInModal();
    }

    @Test(priority = 11)
    public void signInWithoutPass(){
        mainPage.goToSignInPage();
        driver.findElement(signInPage.inputEmail).sendKeys("a@a.ru");
        signInPage.submit();
        String errorText = driver.findElement(signInPage.errorMessagesForm).getText();
        Assert.assertTrue(errorText.contains("Поле Пароль обязательно для заполнения."));
        signInPage.hideErrorMessage();
        signInPage.closeSignInModal();
    }

    @Test(priority = 12)
    public void linkInErrorMessageIsDisplayed(){
        mainPage.goToSignInPage();
        driver.findElement(signInPage.inputEmail).sendKeys("a@a.ru");
        signInPage.submit();
        WebElement link = driver.findElement(signInPage.linkToRestorePass);
        Assert.assertNotNull(link);
        signInPage.hideErrorMessage();
        signInPage.closeSignInModal();
    }
}
