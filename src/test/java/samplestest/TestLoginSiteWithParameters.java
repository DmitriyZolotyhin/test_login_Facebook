package samplestest;


import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.ConnectException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertEquals;


/**
 * Created by Администратор on 07.05.2017.
 */


public class TestLoginSiteWithParameters {

    public static WebDriver driver;

    @DataProvider(name = "Authentication")

    public static Object[][] credentials() {

        return new Object[][]{{null, null}, {"zolotoy18.12.1988@gmail.com", "123"}, {"zolotoy18.12.1988@gmail.com", "samara"}};

    }

    @Test(groups = {"test"}, dataProvider = "Authentication")
    @Step
    @Title( "Test with parametrs")
    public void test1(String sUsername, String sPassword) {
        //здесь код, который потенциально может привести к ошибке
        try {
            driver.findElement(By.xpath(".//*[@id='email']")).sendKeys(sUsername);

            driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys(sPassword);

            driver.findElement(By.xpath(".//*[@id='u_0_q']")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.xpath(".//*[@id='navItem_100016796564791']/a/div/span")).click();
            WebElement elementButtonEnterLogin = driver.findElement(By.xpath(".//*[@id='fb-timeline-cover-name']"));
            String strng2 = elementButtonEnterLogin.getText();
            Assert.assertEquals("Тест Тестов", strng2);
        }

        catch (NoSuchWindowException e )   /*в скобках указывается класс конкретной ожидаемой ошибки.*/
        {

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            // String currentUrl = driver.getCurrentUrl();
            // Assert.assertEquals( currentUrl , "https://www.facebook.com/login.php?login_attempt=1&lwv=110");
            driver.quit();

        }
        catch (NoSuchElementException e )
        {
            //driver.findElement(By.cssSelector("div._4rbf._53ij")).getText().matches("^exact:Вы ввели неверный пароль\\. Забыли пароль[\\s\\S]$");
            driver.quit();

        }

    }
    @BeforeMethod
    @Step

    public void beforeMethod ()
    {
        try {
            Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("webdriver.ie.driver", "C:\\Driver\\IEDriverServer.exe");
        //driver = new InternetExplorerDriver();
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        driver = new InternetExplorerDriver(ieCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ru-ru.facebook.com/");
        driver.manage().window().maximize();

    }
    @AfterMethod
    public void afterMethod ()
    {
        driver.quit();
    }

}






