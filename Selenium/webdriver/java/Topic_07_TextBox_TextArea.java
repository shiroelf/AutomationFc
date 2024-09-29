import net.bytebuddy.implementation.auxiliary.MethodCallProxy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_07_TextBox_TextArea {
    public static String getRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }

    public static String getRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "example.com"};
        String username = getRandomString(8);
        String domain = domains[new Random().nextInt(domains.length)];

        return username + "@" + domain;
    }

    WebDriver driver;

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
    public void TC_01_TextBox_TextArea() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//a[@class= 'button' and @title = 'Create an Account']")).click();
        String name = "Automation";
        driver.findElement(By.xpath("//input[@id = 'firstname']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@id = 'lastname']")).sendKeys("Test");
        String randomEmail = getRandomEmail();
        driver.findElement(By.xpath("//input[@id = 'email_address']")).sendKeys(randomEmail);
        driver.findElement(By.xpath("//input[@id = 'password']")).sendKeys("1234567");
        driver.findElement(By.xpath("//input[@id = 'confirmation']")).sendKeys("1234567");
        driver.findElement(By.xpath("//button[@type = 'submit' and @title = 'Register']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class = 'success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains("Automation"));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains(randomEmail));
        driver.findElement(By.xpath("//li[@class='level0 nav-1 first']/a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//li[@class='item last']/a[@title= 'Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//p[@class = 'rating-links']/a[text() = 'Add Your Review']")).click();
        driver.findElement(By.xpath("//table[@id = 'product-review-table']//td[@class = 'value last']//input[@value = '5']")).click();

        driver.findElement(By.xpath("//textarea[@id = 'review_field']")).sendKeys("Good applications \n");
        driver.findElement(By.xpath("//textarea[@id = 'review_field']")).sendKeys("Pretty easy to navigate");
        driver.findElement(By.xpath("//input[@id = 'summary_field']")).sendKeys("Good product");
        driver.findElement(By.xpath("//input[@id = 'nickname_field']")).sendKeys(name);
        driver.findElement(By.xpath("//button[@title = 'Submit Review']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class = 'messages']//span")).getText(), "Your review has been accepted for moderation.");

        driver.findElement(By.xpath("//span[@class = 'label' and text() = 'Account']")).click();
        driver.findElement(By.xpath("//a[@title ='Log Out']")).click();

        Thread.sleep(5000);

        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/");
    }
    @Test
    public void TC02_TextBox_TextArea() throws InterruptedException {
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        String adminUsername = "admin";
        String adminPassword = "admin123";
        String firstName = "Auto123";
        String lastName = "Mation123";
        String employeeID = getRandomString(5);
        String newUsername = getRandomString(10);
        String newPassword = "Admin123";
        String numberImmigration = getRandomString(5);
        String commentLine = "This is a comment line";

        driver.get(url);

        // Login as admin
        driver.findElement(By.xpath("//input[@name = 'username']")).sendKeys(adminUsername);
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys(adminPassword);
        driver.findElement(By.xpath("//button[@type ='submit']")).click();

        // Navigate to PIM and add new employee
        driver.findElement(By.xpath("//a[@class='oxd-main-menu-item']//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//a[text() = 'Add Employee']")).click();
        driver.findElement(By.xpath("//input[@name = 'firstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name = 'lastName']")).sendKeys(lastName);
        driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div//input")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div//input")).sendKeys(employeeID);

        Thread.sleep(1500);
        driver.findElement(By.xpath("//span[contains(@class, 'oxd-switch-input')]")).click();

        // Create account for new employee
        driver.findElement(By.xpath("//label[text()='Username']/ancestor::div[1]/following-sibling::div//input[contains(@class, 'oxd-input')]")).sendKeys(newUsername);
        driver.findElement(By.xpath("//input[@type = 'password']")).sendKeys(newPassword);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/ancestor::div[1]/following-sibling::div//input[@type='password']")).sendKeys(newPassword);
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();

        // Create account for new employee
        Assert.assertEquals( driver.findElement(By.xpath("//input[@name = 'firstName']")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name = 'lastName']")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text() = 'Employee Id']//parent::div//following-sibling::div//input[@class= 'oxd-input oxd-input--active']")).getAttribute("value"), employeeID);

        // Add immigration record
        Thread.sleep(15000);
        driver.findElement(By.xpath("//div[@class= 'orangehrm-tabs']//a[text() = 'Immigration']")).click();
        driver.findElement(By.xpath("//div[@class = 'orangehrm-action-header']/h6[text() = 'Assigned Immigration Records']/following-sibling::button")).click();
        driver.findElement(By.xpath("//label[text() = 'Number']//parent::div//following-sibling::div//input")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        driver.findElement(By.xpath("//label[text() = 'Number']//parent::div//following-sibling::div//input")).sendKeys(numberImmigration);
        driver.findElement(By.xpath("//textarea[@placeholder = 'Type Comments here']")).sendKeys(commentLine);
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();

        // Verify immigration record
        driver.findElement(By.xpath("//i[contains(@class, 'pencil')]/parent::button")).click();
        Thread.sleep(15000);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text() = 'Number']//parent::div//following-sibling::div//input")).getAttribute("value"),numberImmigration);
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@placeholder = 'Type Comments here']")).getAttribute("value"),commentLine);

        // Logout
        driver.findElement(By.xpath("//p[@class = 'oxd-userdropdown-name']")).click();
        driver.findElement(By.xpath("//a[text() = 'Logout']")).click();

        // Login as new user
        driver.findElement(By.xpath("//input[@name = 'username']")).sendKeys(newUsername);
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys(newPassword);
        driver.findElement(By.xpath("//button[@type ='submit']")).click();

        // Navigate to My Info
        driver.findElement(By.xpath("//span[text() = 'My Info']")).click();

        // Navigate to My Info
        Thread.sleep(15000);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name = 'firstName']")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name = 'lastName']")).getAttribute("value"),lastName);

        Assert.assertEquals(driver.findElement(By.xpath("//label[text() = 'Employee Id']//parent::div//following-sibling::div//input[@class= 'oxd-input oxd-input--active']")).getAttribute("value"),employeeID);

        // Verify immigration record as new user
        driver.findElement(By.xpath("//div[@class= 'orangehrm-tabs']//a[text() = 'Immigration']")).click();
        Thread.sleep(15000);
        driver.findElement(By.xpath("//i[contains(@class, 'pencil')]/parent::button")).click();
        Thread.sleep(15000);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text() = 'Number']//parent::div//following-sibling::div//input")).getAttribute("value"),numberImmigration);
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@placeholder = 'Type Comments here']")).getAttribute("value"),commentLine);
    }

    @AfterClass
    public void afterClass(){driver.quit();}
}