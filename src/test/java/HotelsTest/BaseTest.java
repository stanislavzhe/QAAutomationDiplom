package HotelsTest;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public static int timeOut = 15;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/article/faces/welcome.xhtml");
    }


    @AfterMethod(alwaysRun = true)
    public void closeDown() {
        driver.close();
    }
}
