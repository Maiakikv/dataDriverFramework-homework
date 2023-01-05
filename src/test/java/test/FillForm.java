package test;



import io.github.bonigarcia.wdm.WebDriverManager;
import main.DDT.SelectQuery;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FillForm {
    WebDriver driver;

    @BeforeTest
    public void setUp (){
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
            this.driver.manage().window().maximize();
            this.driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        }


    @Test(dataProvider = "DB", dataProviderClass = SelectQuery.class)
    public void fillForm(String id, String firstName, String lastName, String mobileNumber){
            driver.get("https://demoqa.com/automation-practice-form");
         WebElement name= driver.findElement(By.id("firstName"));
         name.sendKeys(firstName);
         WebElement lastN = driver.findElement(By.id("lastName"));
         lastN.sendKeys(lastName);
         WebElement mobile = driver.findElement(By.xpath("//input[@id = 'userNumber']"));
         mobile.sendKeys(mobileNumber);


//  საბმითზე არ ეწერა დავალებაში და დავტოვე ასე

//        SelenideElement submit = $(By.id("submit"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        WebElement ad = driver.findElement(By.id("adplus-anchor"));
//        js.executeScript("return arguments[0].remove(); ", ad);
//        js.executeScript("document.body.style.zoom='67%'");
//        js.executeScript("arguments[0].scrollIntoView();", submit);
//        js.executeScript("arguments[0].click();", submit);


        }


}
