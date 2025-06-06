package br.edu.ifsp.testing.class10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class AlertTest extends BaseSeleniumTest {

    @Test
    @DisplayName("Should click on alert")
    void shouldClickOnAlert() {
        driver.get("https://testpages.herokuapp.com/styled/alerts/alert-test.html");
        driver.findElement(By.id("alertexamples")).click();
        final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        final String alertText = alert.getText();
        alert.accept(); // accepts the alert (click ok)
        assertThat(alertText).isEqualTo("I am an alert box!");
    }

    @Test
    @DisplayName("Should dismiss alert")
    void shouldDismissAlert() {
        driver.get("https://testpages.herokuapp.com/styled/alerts/alert-test.html");
        driver.findElement(By.id("confirmexample")).click();
        final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        alert.dismiss(); // click cancel
        final String confirmation = driver.findElement(By.id("confirmexplanation")).getText();
        assertThat(confirmation).isEqualTo("You clicked Cancel, confirm returned false.");
    }

    @Test
    @DisplayName("Should fill text in alert")
    void shouldFillTextInAlert() {
        driver.get("https://testpages.herokuapp.com/styled/alerts/alert-test.html");
        driver.findElement(By.id("promptexample")).click();
        final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        alert.sendKeys("Hello!"); // fill the prompt
        alert.accept(); // click ok
        final String confirmationText = driver.findElement(By.id("promptexplanation")).getText();
        assertThat(confirmationText).isEqualTo("You clicked OK. 'prompt' returned Hello!");
    }

}
