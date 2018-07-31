package com.nsa.cm6213.example.charity2018.page;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import com.nsa.cm6213.example.charity2018.controllers.CharitySearchController;
import com.nsa.cm6213.example.charity2018.domain.Charity;
import com.nsa.cm6213.example.charity2018.services.CharityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class DonationTest {

    private WebDriver webDriver;

    @Value("${local.server.port}")
    private int port;

    @Test
    public void searchShouldFindNSPCC() {

        System.setProperty("webdriver.chrome.driver", "C:\\Projects\\Examples\\SpringBoot\\chromedriver.exe");

        webDriver = new ChromeDriver();

        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/home.html");
        WebElement searchBox = this.webDriver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("12345678");
        this.webDriver.findElement(By.id("submit")).click();
        assertThat(webDriver.findElement(By.id("charityListContainer")).getText()).contains("National");
    }
}


