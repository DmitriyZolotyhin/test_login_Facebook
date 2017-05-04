package samplestest;


import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;


/**
 * Created by Администратор on 03.05.2017.
 */
public class TestLoginSiteWithParameters
{
    public static WebDriver driver;

    @DataProvider(name = "Authentication")

    public static Object[][] credentials() {

        return new Object[][] { { null, null}, { "zolotoy18.12.1988@gmail.com", "123" } , { "zolotoy18.12.1988@gmail.com","samara" }};

    }
   @Test(dataProvider = "Authentication")

   public void test1(String sUsername, String sPassword) {
       //здесь код, который потенциально может привести к ошибке
       try{  driver.findElement(By.xpath(".//*[@id='email']")).sendKeys(sUsername);

           driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys(sPassword);

           driver.findElement(By.xpath(".//*[@id='u_0_q']")).click();
           driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
           driver.findElement(By.xpath(".//*[@id='navItem_100016796564791']/a/div/span")).click();
           WebElement elementButtonEnterLogin = driver.findElement(By.xpath(".//*[@id='fb-timeline-cover-name']"));
           String strng2 = elementButtonEnterLogin.getText();
           Assert.assertEquals("Тест Тестов", strng2);
           //driver.quit();



       } catch( NoSuchWindowException e )
       { /*в скобках указывается класс конкретной ожидаемой ошибки.*/
           WebElement elementButtonEnterLogin1 = driver.findElement(By.xpath(".//*[@id='content']/div/div/div[1]/span"));
           String strng3 = elementButtonEnterLogin1.getText();
           Assert.assertEquals("Вход на Facebook", strng3);

           driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
           //assertEquals("Вход на Facebook", driver.findElement(By.className(".//*[@id='content']/div/div/div[1]/span")));
           driver.quit();
       }
       catch(  NoSuchElementException e )
       { /*в скобках указывается класс конкретной ожидаемой ошибки.*/
          // WebElement elementButtonEnterLogin2 = driver.findElement(By.xpath(".//*[@id='globalContainer']/div[3]/div/div/div"));
          // String strng4 = elementButtonEnterLogin2.getText();
         //  Assert.assertEquals("Вы ввели неверный пароль", strng4);

          // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
          // assertEquals("Войти как Тест Тестов", driver.findElement(By.className(".//*[@id='content']/div/div/div[1]/span/div/div[1]/div[2]/span")));
           driver.quit();
//здесь описываются действия, направленные на обработку исключений
       }
       finally{
//выполняется в любом случае ( блок finnaly не обязателен)
           driver.quit();
       }


   }


    @BeforeMethod
    public void beforeMethod()
    {
        try
        {
            Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        //driver.manage().deleteAllCookies();
        System.setProperty("webdriver.ie.driver", "C:\\Driver\\IEDriverServer.exe");
        driver = new InternetExplorerDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://ru-ru.facebook.com/");

    }
        @AfterMethod
        public void afterMethod()
        {
            driver.quit();
        }

    }







