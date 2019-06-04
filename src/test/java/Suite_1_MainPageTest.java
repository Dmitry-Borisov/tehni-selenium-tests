import org.testng.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Suite_1_MainPageTest extends BaseFixture {
    private Date dateNow = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-hh.mm.ss");
    SoftAssert s = new SoftAssert();

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(! result.isSuccess()){
            TakesScreenshot screenshot = (TakesScreenshot)driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File("./screenshots/" + result.getName() + "-" + format.format(dateNow) + ".png"));
            System.out.println("Test failed! Get a screenshot");
        }
    }

    @Test(priority = 1)
    public void companyLogoIsDisplayed(){
        Assert.assertTrue(driver.findElement(mainPage.companyLogo).isDisplayed());
    }

    @Test(priority = 2)
    public void companyPhoneIsDisplayed(){
        String number = driver.findElement(mainPage.companyPhone).getText();
        Assert.assertEquals(number, mainPage.PHONE);
    }

    @Test(priority = 3)
    public void callMeBtnIsDisplayed(){
        Assert.assertTrue(driver.findElement(mainPage.callMeBtn).isDisplayed());
    }

    @Test(priority = 4)
    public void workTimeIsDisplayed(){
        String time = driver.findElement(mainPage.workTime).getText();
        Assert.assertEquals(time, mainPage.WORK_TIME);
    }

    @Test(priority = 5)
    public void signInLinkIsDisplayed(){
        Assert.assertTrue(driver.findElement(mainPage.signInLink).isDisplayed());
    }

    @Test(priority = 6)
    public void signUpLinkIsDisplayed(){
        Assert.assertTrue(driver.findElement(mainPage.signUpLink).isDisplayed());
    }

    @Test(priority = 7)
    public void basketLinkIsDisplayed(){
        Assert.assertTrue(driver.findElement(mainPage.basketLink).isDisplayed());
    }

    @Test(priority = 8)
    public void goodsNumberIsDisplayed(){
        Assert.assertTrue(driver.findElement(mainPage.goodsNumber).isDisplayed());
    }

    @Test(priority = 9)
    public void topMenuIsDisplayed(){
        Assert.assertTrue(driver.findElement(mainPage.topMenu).isDisplayed());
    }

    @Test(priority = 10)
    public void checkNumberTopMenuItems(){
        int menuSize = driver.findElements(mainPage.topMenu).size();
        Assert.assertEquals(menuSize, 7);
    }

    @Test(priority = 11)
    public void checkTopMenuItemsName(){
        List<WebElement> list = driver.findElements(mainPage.topMenu);
        s.assertEquals(list.get(0).getText(),"О нас");
        s.assertEquals(list.get(1).getText(),"Доставка");
        s.assertEquals(list.get(2).getText(),"Оплата");
        s.assertEquals(list.get(3).getText(),"Кредит/Рассрочка");
        s.assertEquals(list.get(4).getText(),"Контакты");
        s.assertEquals(list.get(5).getText(),"Покупатели");
        s.assertEquals( list.get(6).getText(),"Помощь");
        s.assertAll();
    }

    @Test(priority = 12)
    public void searchFormIsDisplayed(){
        Assert.assertTrue(driver.findElement(mainPage.searchForm).isDisplayed());
    }

    @Test(priority = 13)
    public void searchBtnIsDisplayed(){
        Assert.assertTrue(driver.findElement(mainPage.searchBtn).isDisplayed());
    }

    @Test(priority = 14)
    public void catalogLinkIsDisplayed(){
        Assert.assertTrue(driver.findElement(mainPage.catalogLink).isDisplayed());
    }

    @Test(priority = 15)
    public void mainMenuCategoriesIsDisplayed(){
        Assert.assertTrue(driver.findElement(mainPage.mainMenuCategories).isDisplayed());
    }

    @Test(priority = 16)
    public void checkNumberMainMenuCategoriesItems(){
        int menuSize = driver.findElements(mainPage.mainMenuCategories).size();
        Assert.assertEquals(menuSize, 4);
    }

    @Test(priority = 17)
    public void checkMainMenuCategoriesItemsName(){
        List<WebElement> list = driver.findElements(mainPage.mainMenuCategories);
        s.assertEquals(list.get(0).getText(),"Распродажа");
        s.assertEquals(list.get(1).getText(),"Акции");
        s.assertEquals(list.get(2).getText(),"Отзывы");
        s.assertEquals( list.get(3).getText(),"О Техни.ру");
        s.assertAll();
    }

    @Test(priority = 18)
    public void catalogCategoriesIsDisplayed(){
        Assert.assertTrue(driver.findElement(mainPage.catalogCategories).isDisplayed());
    }

    @Test(priority = 19)
    public void checkNumberCatalogCategoriesItems(){
        int menuSize = driver.findElements(mainPage.catalogCategories).size();
        Assert.assertEquals(menuSize, 6);
    }

    @Test(priority = 20)
    public void checkCatalogCategoriesItemsName(){
        List<WebElement> list = driver.findElements(mainPage.catalogCategories);
        s.assertEquals(list.get(0).getText(),"Крупная и встраиваемая техника");
        s.assertEquals(list.get(1).getText(),"Техника для кухни");
        s.assertEquals(list.get(2).getText(),"Техника для дома");
        s.assertEquals(list.get(3).getText(),"Климатическая техника");
        s.assertEquals(list.get(4).getText(),"Распродажа остатков");
        s.assertEquals(list.get(5).getText(),"Уцененные товары");
        s.assertAll();
    }
}
