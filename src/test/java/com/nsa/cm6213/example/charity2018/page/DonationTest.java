package com.nsa.cm6213.example.charity2018.page;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.nsa.cm6213.example.charity2018.controllers.CharitySearchController;
import com.nsa.cm6213.example.charity2018.domain.Charity;
import com.nsa.cm6213.example.charity2018.domain.Donation;
import com.nsa.cm6213.example.charity2018.repositories.DonationRepository;
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

    @Autowired
    DonationRepository donationRepo;

    @Test
    public void searchShouldFindNSPCC() {

        System.setProperty("webdriver.chrome.driver", "C:\\Projects\\Examples\\SpringBoot\\chromedriver.exe");

        webDriver = new ChromeDriver();

        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/home.html");
        WebElement searchBox = this.webDriver.findElement(By.id("search"));
        searchBox.clear(); //clear the search box
        searchBox.sendKeys("12345678"); //enter a search term
        this.webDriver.findElement(By.id("submit")).click();
        assertThat(webDriver.findElement(By.id("charityListContainer")).getText()).contains("National");  //now on charity results page
        this.webDriver.findElement(By.id("charityLink1")).click(); //click
        assertThat(webDriver.findElement(By.id("charityInfo")).getText()).contains("National"); //now on profile page
        this.webDriver.findElement(By.id("donateLink1")).click();

        assertThat(webDriver.getTitle().contains("National")); //now on donation page

        this.webDriver.findElement(By.id("name")).clear();
        this.webDriver.findElement(By.id("name")).sendKeys("Carl Jones");

        this.webDriver.findElement(By.id("addressLine1")).clear();
        this.webDriver.findElement(By.id("addressLine1")).sendKeys("45 Cwm Nofydd");

        this.webDriver.findElement(By.id("addressLine2")).clear();
        this.webDriver.findElement(By.id("addressLine2")).sendKeys("Rhiwbina");

        this.webDriver.findElement(By.id("addressLine3")).clear();
        this.webDriver.findElement(By.id("addressLine3")).sendKeys("Cardiff");

        this.webDriver.findElement(By.id("postcode")).clear();
        this.webDriver.findElement(By.id("postcode")).sendKeys("CF14 6JX");

        this.webDriver.findElement(By.id("donationAmount")).clear();
        this.webDriver.findElement(By.id("donationAmount")).sendKeys("100");

        this.webDriver.findElement(By.id("isGiftAidEligible")).click();
        this.webDriver.findElement(By.id("submit")).click();

        assertThat(webDriver.getTitle().contains("Make Payment"));

        this.webDriver.findElement(By.id("cardNumber")).clear();
        this.webDriver.findElement(By.id("cardNumber")).sendKeys("1234567812345678");

        this.webDriver.findElement(By.id("isCardAddressHomeAddress")).click();

        this.webDriver.findElement(By.id("submit")).click();

        assertThat(webDriver.getTitle().contains("Confirm Donation"));

        this.webDriver.findElement(By.id("confirm")).click();

        assertThat(webDriver.getTitle().contains("Thank you"));

        Long lastDonationId = donationRepo.findLastDonationId();

        assertTrue(lastDonationId.equals(1001L));

    }
}


