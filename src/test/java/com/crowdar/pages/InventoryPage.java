package com.crowdar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartIcon = By.className("shopping_cart_link");
    // Nuevo localizador para el menú desplegable de ordenamiento de productos
    private By sortDropdown = By.className("product_sort_container");
    // Nuevo localizador para obtener todos los precios de los productos en vista
    private By productPrices = By.className("inventory_item_price");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addProductToCart(String productName) {
        // Genera dinámicamente el id del botón 'Add to cart' basado en el nombre del producto
        String formattedName = productName.toLowerCase().replace(" ", "-");
        By addToCartButton = By.id("add-to-cart-" + formattedName);
        driver.findElement(addToCartButton).click();
    }

   public void goToCart() {
        int intentos = 0;
        while (intentos < 3) {
            try {
                WebElement cartElement = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
                cartElement.click();
                break; // Si hace clic con éxito, sale del bucle
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                intentos++;
                if (intentos == 3) {
                    throw e; // Si falla 3 veces, lanza la excepción
                }
            }
        }
    }

    // Nuevo método para seleccionar una opción del filtro de ordenamiento (ej: "Price (low to high)")
    public void selectSortOption(String visibleText) {
        // Usamos elementToBeClickable para asegurar que el combo no solo sea visible, sino operable en el DOM
        WebElement sortDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.className("product_sort_container")));
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(sortDropdown);
        select.selectByVisibleText(visibleText);
    }

    // Nuevo método para obtener la lista de precios actuales convertidos a Double y validar el ordenamiento
    public List<Double> getProductPrices() {
        List<WebElement> priceElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrices));
        List<Double> prices = new ArrayList<>();
        for (WebElement element : priceElements) {
            // Limpia el símbolo '$' y convierte el texto a número decimal
            String priceText = element.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }
}