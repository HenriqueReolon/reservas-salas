package com.reservasala.reserva_sala.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuariosUiTest {
    @Container
    public static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
        .withDatabaseName("reservasdb")
        .withUsername("admin")
        .withPassword("admin");

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testFillAndSubmitUsuarioForm() {
        String baseUrl = "http://localhost:" + port;
        driver.get(baseUrl + "/usuarios");
        driver.findElement(By.name("nome")).sendKeys("Test User");
        driver.findElement(By.name("email")).sendKeys("test@example.com");
        driver.findElement(By.name("senha")).sendKeys("password123");
        driver.findElement(By.name("telefone")).sendKeys("123456789");
        driver.findElement(By.name("rua")).sendKeys("Test Street");
        driver.findElement(By.name("numero")).sendKeys("123");
        driver.findElement(By.name("cidade")).sendKeys("Test City");
        driver.findElement(By.name("cep")).sendKeys("00000-000");
        driver.findElement(By.name("cpf")).sendKeys("00000000000");
        driver.findElement(By.name("dataNascimento")).sendKeys("1990-01-01");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        List<WebElement> items = driver.findElements(By.cssSelector("ul li"));
        boolean found = items.stream().anyMatch(e -> e.getText().contains("Test User"));
        Assertions.assertTrue(found, "New user should be present in the list");
    }
}
