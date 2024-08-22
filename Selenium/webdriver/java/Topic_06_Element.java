import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Topic_06_Element {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Actions actions = new Actions(driver);
    }

    @Test
    public void TC_01_Check_isDisplayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed());
        Assert.assertNotEquals(driver.findElement(By.xpath("//input[@id='name']")).getText(), "User5");

        WebElement imageElement = driver.findElement(By.xpath("//div[@class='figure']//img[@alt='User Avatar 05']"));
        Actions actions = new Actions(driver);  // Initialize the Actions instance
        actions.moveToElement(imageElement).perform();

        if (imageElement.isDisplayed()) {
            driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation Testing");
            driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation Testing");
            driver.findElement(By.xpath("//input[@id='under_18']")).click();
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }
    }


    @Test
    public void TC_02_Check_isEnabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement mailInput = driver.findElement(By.xpath("//input[@id='mail']"));
        System.out.println("Mail input is enabled: " + mailInput.isEnabled());
        Assert.assertTrue(mailInput.isEnabled());

        WebElement under18Input = driver.findElement(By.xpath("//input[@id='under_18']"));
        System.out.println("Under 18 input is enabled: " + under18Input.isEnabled());
        Assert.assertTrue(under18Input.isEnabled());

        WebElement eduTextarea = driver.findElement(By.xpath("//textarea[@id='edu']"));
        System.out.println("Edu textarea is enabled: " + eduTextarea.isEnabled());
        Assert.assertTrue(eduTextarea.isEnabled());

        WebElement job1Select = driver.findElement(By.xpath("//select[@id='job1']"));
        System.out.println("Job 1 select is enabled: " + job1Select.isEnabled());
        Assert.assertTrue(job1Select.isEnabled());

        WebElement job2Select = driver.findElement(By.xpath("//select[@id='job2']"));
        System.out.println("Job 2 select is enabled: " + job2Select.isEnabled());
        Assert.assertTrue(job2Select.isEnabled());

        WebElement developmentInput = driver.findElement(By.xpath("//input[@id='development']"));
        System.out.println("Development input is enabled: " + developmentInput.isEnabled());
        Assert.assertTrue(developmentInput.isEnabled());

        WebElement slider1Input = driver.findElement(By.xpath("//input[@id='slider-1']"));
        System.out.println("Slider 1 input is enabled: " + slider1Input.isEnabled());
        Assert.assertTrue(slider1Input.isEnabled());

        WebElement disablePasswordInput = driver.findElement(By.xpath("//input[@id='disable_password']"));
        System.out.println("Disable Password input is displayed: " + disablePasswordInput.isDisplayed());
        Assert.assertTrue(disablePasswordInput.isDisplayed());

        WebElement radioDisabledInput = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
        System.out.println("Radio Disabled input is displayed: " + radioDisabledInput.isDisplayed());
        Assert.assertTrue(radioDisabledInput.isDisplayed());

        WebElement bioInput = driver.findElement(By.xpath("//textarea[@id='bio']"));
        System.out.println("Bio input is displayed: " + bioInput.isDisplayed());
        Assert.assertTrue(bioInput.isDisplayed());

        WebElement job3Select = driver.findElement(By.xpath("//select[@id='job3']"));
        System.out.println("Job 3 select is displayed: " + job3Select.isDisplayed());
        Assert.assertTrue(job3Select.isDisplayed());

        WebElement checkDisabledInput = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
        System.out.println("Check Disabled input is displayed: " + checkDisabledInput.isDisplayed());
        Assert.assertTrue(checkDisabledInput.isDisplayed());

        WebElement slider2Input = driver.findElement(By.xpath("//input[@id='slider-2']"));
        System.out.println("Slider 2 input is displayed: " + slider2Input.isDisplayed());
        Assert.assertTrue(slider2Input.isDisplayed());
    }
    @Test
    public void TC03_isSelected(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement checkboxUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
        checkboxUnder18.click();
        Assert.assertTrue(checkboxUnder18.isSelected());
        System.out.println("Checkbox Under 18 is Selected" + checkboxUnder18.isSelected());
        WebElement checkboxJava = driver.findElement(By.xpath("//input[@id='java']"));
        checkboxJava.click();
        Assert.assertTrue(checkboxJava.isSelected());
        System.out.println("Checkbox Java is Selected" + checkboxJava.isSelected());
        checkboxJava.click();
        Assert.assertFalse(checkboxJava.isSelected());
        System.out.println("Checkbox Java is not Selected" + checkboxJava.isSelected());
    }
    @Test
    public void TC04_Check_Combination(){
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automationtest@gmail.com");
        String email = driver.findElement(By.xpath("//input[@id='email']")).getText();
        driver.findElement(By.xpath("//input[@id='new_username']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("1");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("a");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("A");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("@");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("1234");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='passwordHint']")).isDisplayed());
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}