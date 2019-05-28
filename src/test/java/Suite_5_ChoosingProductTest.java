import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Suite_5_ChoosingProductTest extends BaseFixture {

    private Date dateNow = new Date();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-hh.mm.ss");

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (!result.isSuccess()) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File("G:\\Arbeit\\github\\tehni-selenium-tests\\screenshots\\" + result.getName() + "-" + format.format(dateNow) + ".png"));
            System.out.println("Test failed! Get a screenshot");
        }
    }

    @Test(priority = 50)
    public void goToFridgesPage() {
        driver.findElement(mainPage.linkToFridges).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(productFridgesPage.findBtn));
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, productFridgesPage.URL);
    }

    @Test(priority = 51)
    public void chooseAllColorFridges() {
        productFridgesPage.scrollToSideBlockMenu(4);
        List<WebElement> checkboxNamesList = driver.findElements(productFridgesPage.checkboxColorList);
        for (WebElement cbx : checkboxNamesList) {
            cbx.click();
            Assert.assertTrue(cbx.isSelected());
        }
    }

    @Test(priority = 52)
    public void findAllColorFridges() {
        productFridgesPage.toFind();
        wait.until(ExpectedConditions.visibilityOfElementLocated(productFridgesPage.filterColor));
        Assert.assertTrue(driver.findElement(productFridgesPage.filterColor).isDisplayed());
    }

    @Test(priority = 53)
    public void resetAllColorFridges() {
        WebElement filterColor = driver.findElement(productFridgesPage.filterColor);
        productFridgesPage.resetFilters();
        Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(filterColor)));
}

    @Test(priority = 54)
    public void findSilverFridges() {
        productFridgesPage.scrollToSideBlockMenu(4);
        productFridgesPage.chooseFridgesByColor(productFridgesPage.SILVER_COLOR);
        productFridgesPage.toFind();
        wait.until(ExpectedConditions.visibilityOfElementLocated(productFridgesPage.filterColor));
        Assert.assertTrue(driver.findElement(productFridgesPage.filterColor).isDisplayed());
    }

    @Test(priority = 55)
    public void chooseFirstFridge() {
        productFridgesPage.scrollToSideBlockMenu(4);
        productFridgesPage.clickBuyBtn(0);

        wait.until(ExpectedConditions.visibilityOfElementLocated(productFridgesPage.backToSiteBtn)).click();
        String goodsNumber = mainPage.getGoodsNumber();
        Assert.assertEquals(goodsNumber, "1");
    }

    @Test(priority = 56)
    public void chooseSecondFridge() {
        productFridgesPage.scrollToSideBlockMenu(4);
        productFridgesPage.clickBuyBtn(1);

        wait.until(ExpectedConditions.visibilityOfElementLocated(productFridgesPage.backToSiteBtn)).click();
        String goodsNumber = mainPage.getGoodsNumber();
        Assert.assertEquals(goodsNumber, "2");
    }

    @Test(priority = 57)
    public void goToBasket() {
        driver.findElement(mainPage.basketLink).click();
        String title = driver.getTitle();
        Assert.assertEquals(title, productFridgesPage.TITLE);
    }

    @Test(priority = 58)
    public void checkGoodsNumberOnBasketPage() {
        int goodsInBasket = driver.findElements(productFridgesPage.tableString).size();
        Assert.assertEquals(goodsInBasket, 2);
    }

    @Test(priority = 59)
    public void deleteOneFridge() {
        WebElement tableString = driver.findElement(productFridgesPage.evenString);
        productFridgesPage.deleteGoods(1);
        wait.until(ExpectedConditions.stalenessOf(tableString));
        int goodsInBasket = driver.findElements(productFridgesPage.tableString).size();
        Assert.assertEquals(goodsInBasket, 1);
    }

    @Test(priority = 60)
    public void chooseDeliveryAddress() {
        productFridgesPage.chooseDeliveryOption(productFridgesPage.DELIVERY_ADDRESS);
        String notice = driver.findElement(productFridgesPage.deliveryNotice).getText();
        Assert.assertEquals(notice, productFridgesPage.NOTICE);
    }

    @Test(priority = 61)
    public void clearBasket() {
        productFridgesPage.deleteGoods(0);
        String message = driver.findElement(productFridgesPage.emptyMessage).getText();
        Assert.assertEquals(message, productFridgesPage.MESSAGE);
    }
}
