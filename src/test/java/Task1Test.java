import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Домашнее задание №1
 * Тест проверяющий заголовок на странице сайта otus.ru
 * Александр Кремлёв
 * 1.0
 */

public class Task1Test extends Assert {
    private final Logger logger = LogManager.getLogger(Task1Test.class);
    protected static WebDriver driver;
    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Before
    public void Setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Startup web driver");
    }

    @Test
    public void StartTest() {
        logger.info("Test start");
        driver.get(cfg.url());
        try {
            assertEquals(driver.getTitle(), cfg.otusTitle());
            logger.info("Test passed, title is " + driver.getTitle());
        } catch(AssertionError e){
            logger.info("Test failed, title is " + driver.getTitle());
            throw e;
        }
        logger.info("Test finish");
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.close();
            logger.info("Shutdown web driver");
        } else {
            logger.error("Web driver not found");
        }
    }
}
