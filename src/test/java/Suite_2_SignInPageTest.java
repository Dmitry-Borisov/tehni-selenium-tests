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

public class Suite_2_SignInPageTest extends BaseFixture {

    private Date dateNow = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-hh.mm.ss");
    SoftAssert s = new SoftAssert();

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(! result.isSuccess()){
            TakesScreenshot screenshot = (TakesScreenshot)driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File("./screenshots/" + result.getName() + "-" + format.format(dateNow) + ".png"));
            System.out.println("Test failed. Get a screenshot");
        }
    }

    @Test(priority = 11)
    public void signInModalIsDisplayed(){
        mainPage.goToSignInPage();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(signInPage.modalForm)).isDisplayed());
    }

    @Test(priority = 22)
    public void checkModalTitle(){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(signInPage.modalTitle,"Войти"));
        String title = driver.findElement(signInPage.modalTitle).getText();
        Assert.assertEquals(title, "Войти");
    }

    @Test(priority = 23)
    public void modalCloseActionIsDisplayed(){
        Assert.assertTrue(driver.findElement(signInPage.modalClose).isDisplayed());
    }

    @Test(priority = 24)
    public void inputEmailIsDisplayed(){
        Assert.assertTrue(driver.findElement(signInPage.inputEmail).isDisplayed());
    }

    @Test(priority = 25)
    public void inputPassIsDisplayed(){
        Assert.assertTrue(driver.findElement(signInPage.inputPass).isDisplayed());
    }

    @Test(priority = 26)
    public void signInBtnIsDisplayed(){
        Assert.assertTrue(driver.findElement(signInPage.btnSignIn).isDisplayed());
    }

    @Test(priority = 27)
    public void linkToRegistrationIsDisplayed(){
        Assert.assertTrue(driver.findElement(signInPage.linkToRegistration).isDisplayed());
    }

    @Test(priority = 28)
    public void linkForgotPassIsDisplayed(){
        Assert.assertTrue(driver.findElement(signInPage.linkForgotPass).isDisplayed());
    }

    @Test(priority = 29)
    public void signInWithoutData(){
        signInPage.submit();
        s.assertTrue(driver.findElement(signInPage.errorMessagesForm).isDisplayed());
        WebElement errorForm = driver.findElement(signInPage.errorMessagesForm);
        s.assertTrue(wait.until(ExpectedConditions.stalenessOf(errorForm)));
        s.assertAll();
        signInPage.closeSignInModal();
    }

    @Test(priority = 30)
    public void signInWithoutEmail(){
        mainPage.goToSignInPage();
        driver.findElement(signInPage.inputPass).sendKeys(signInPage.signInData.get("pass"));
        signInPage.submit();
        String errorText = driver.findElement(signInPage.errorMessagesForm).getText();
        Assert.assertTrue(errorText.contains(signInPage.errorMessages[0]));
        signInPage.hideErrorMessage();
        signInPage.closeSignInModal();
    }

    @Test(priority = 31)
    public void signInWithoutPass(){
        mainPage.goToSignInPage();
        driver.findElement(signInPage.inputEmail).sendKeys(signInPage.signInData.get("email"));
        signInPage.submit();
        String errorText = driver.findElement(signInPage.errorMessagesForm).getText();
        Assert.assertTrue(errorText.contains(signInPage.errorMessages[1]));
        signInPage.hideErrorMessage();
        signInPage.closeSignInModal();
    }

    @Test(priority = 32)
    public void linkInErrorMessageIsDisplayed(){
        mainPage.goToSignInPage();
        driver.findElement(signInPage.inputEmail).sendKeys(signInPage.signInData.get("email"));
        signInPage.submit();
        WebElement link = driver.findElement(signInPage.linkToRestorePass);
        Assert.assertNotNull(link);
        signInPage.hideErrorMessage();
        signInPage.closeSignInModal();
    }
}
