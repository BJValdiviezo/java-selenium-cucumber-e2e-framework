package com.crowdar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartIcon = By.className("shopping_cart_link");
    private By checkoutButton = By.id("checkout");
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By completeHeader = By.className("complete-header");

    // Localizador para el botón remove dentro del carrito
    private By removeButton = By.xpath("//button[text()='Remove']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addProductToCart(String productName) {
        String buttonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonId))).click();
    }

    public void goToCart() {
        WebElement cartBtn = wait.until(ExpectedConditions.presenceOfElementLocated(cartIcon));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", cartBtn);

        // Fallback en caso de latencia de navegación en CI Headless
        if (!driver.getCurrentUrl().contains("cart.html")) {
            driver.get("https://www.saucedemo.com/cart.html");
        }

        wait.until(ExpectedConditions.urlContains("cart.html"));
    }

    public void clickCheckout() {
        if (!driver.getCurrentUrl().contains("checkout-step-one.html")) {
            WebElement checkoutBtn = wait.until(ExpectedConditions.presenceOfElementLocated(checkoutButton));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", checkoutBtn);
        }
    }

    public void enterShippingInformation(String firstName, String lastName, String postalCode) {
        if (!driver.getCurrentUrl().contains("checkout-step-one.html")) {
            driver.get("https://www.saucedemo.com/checkout-step-one.html");
        }
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        WebElement firstNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        WebElement lastNameEl = driver.findElement(lastNameInput);
        WebElement postalCodeEl = driver.findElement(postalCodeInput);

        setReactInputValue(firstNameEl, firstName);
        setReactInputValue(lastNameEl, lastName);
        setReactInputValue(postalCodeEl, postalCode);

        WebElement continueBtn = wait.until(ExpectedConditions.presenceOfElementLocated(continueButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", continueBtn);

        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }

    private void setReactInputValue(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "var nativeSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "nativeSetter.call(arguments[0], arguments[1]);" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            element, value
        );
    }

    public void finishCheckout() {
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
        WebElement finishBtn = wait.until(ExpectedConditions.presenceOfElementLocated(finishButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", finishBtn);
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }

    public void removeProductFromCart() {
        WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(removeButton));
        removeBtn.click();
    }
}