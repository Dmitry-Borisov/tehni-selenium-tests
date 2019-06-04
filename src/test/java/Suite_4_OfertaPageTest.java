import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Suite_4_OfertaPageTest extends BaseFixture{

    private Date dateNow = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-hh.mm.ss");

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(! result.isSuccess()){
            TakesScreenshot screenshot = (TakesScreenshot)driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File("./screenshots/" + result.getName() + "-" + format.format(dateNow) + ".png"));
            System.out.println("Test failed. Get a screenshot");
        }
    }

    @Test(priority = 47)
    public void ofertaPageIsAvailable(){
        mainPage.goToSignUpPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpPage.ofertaPageLink));
        signUpPage.goToOfertaPage();
        switchToNextTab();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ofertaPage.titleContent));
        String url = driver. getCurrentUrl();
        Assert.assertEquals(url, ofertaPage.OFERTA_PAGE);
    }

    @Test(priority = 48)
    public void scrollPageDownAndUp(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        String phoneNumber = driver.findElement(ofertaPage.phoneInFooter).getText();
        Assert.assertEquals(phoneNumber, ofertaPage.PHONE);
        driver.findElement(ofertaPage.bottomTopButton).click();
    }

    @Test(priority = 49)
    public void goToMainPage(){
        driver.switchTo().window(firstTab);
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpPage.modalTitle));
        String url = driver. getCurrentUrl();
        Assert.assertEquals(url, ofertaPage.HOME_PAGE);
    }
}


