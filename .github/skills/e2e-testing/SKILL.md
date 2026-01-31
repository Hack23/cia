---
name: e2e-testing
description: Implement end-to-end testing with Selenium, Playwright for CIA platform UI and workflow validation
license: Apache-2.0
---

# E2E Testing Skill

## Purpose
Test complete user workflows and UI interactions using Selenium/Playwright.

## When to Use
- ✅ Testing user registration/login flows
- ✅ Testing complex multi-step workflows
- ✅ Testing UI rendering and interactions
- ✅ Cross-browser compatibility testing

## Selenium WebDriver Pattern
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
class LoginE2ETest {
    private WebDriver driver;
    
    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }
    
    @Test
    void shouldLoginSuccessfully() {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("login-btn")).click();
        
        assertThat(driver.getCurrentUrl()).contains("/dashboard");
    }
}
```

## Page Object Model
```java
public class LoginPage {
    private WebDriver driver;
    
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "login-btn")
    private WebElement loginButton;
    
    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
```

## References
- Selenium: https://www.selenium.dev/
- E2ETestPlan.md