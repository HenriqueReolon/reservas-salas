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
public class SalasUiTest {

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
    public void testFillAndSubmitSalaForm() {
        String baseUrl = "http://localhost:" + port;
        driver.get(baseUrl + "/salas");
        driver.findElement(By.name("nome")).sendKeys("Test Sala");
        driver.findElement(By.name("capacidade")).sendKeys("50");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        List<WebElement> items = driver.findElements(By.cssSelector("ul li"));
        boolean found = items.stream().anyMatch(e -> e.getText().contains("Test Sala"));
        Assertions.assertTrue(found, "New sala should be present in the list");
    }
}
