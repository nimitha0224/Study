package com.qa.opencart.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    public WebDriver driver;
    public Properties prop;

    /**
     * This method initializes webdriver based on browser
     * Takes care of remote and local run
     * @param browser
     * @return
     */
    public WebDriver init_driver(String browser){

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            WebDriverManager.chromedriver().setup();
        }
            else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            WebDriverManager.firefoxdriver().setup();
        }
                else if (browser.equalsIgnoreCase("safari")){
                    driver= new SafariDriver();
            }
                else {
            System.out.println( " Pls pass right driver ::"+browser);
        }
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                driver.get("https://demo.opencart.com/index.php?route=account/login");
        return driver;
        }

    /**
     * Initialize properties based on QA/DEV/Stage/Prod
     * @return
     */
    public Properties init_prop(){
     prop = new Properties();
        try {
            FileInputStream ip =new FileInputStream("./src/test/resources/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }




    }

