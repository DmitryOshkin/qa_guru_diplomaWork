package yandex.oshkin.pages.uiPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class ProductPageUi {
    SelenideElement
            addOrderButton = $(".ProductCardLayout .ProductHeader__add-to-cart"),
            addCompareButton = $(".ProductCardLayout .js--ProductHeader__add-to-compare"),
            closePopUpButton = $(".UpsaleBasket__main-popup__close path");

    @Step("Добавляем товар в корзину")
    public ProductPageUi addToOrder(String productCode) {
        addProductOrder();
        checkResultAddOrder(productCode);
        closePopUpOrder();
        return this;
    }

    @Step("Проверяем результат поиска")
    public ProductPageUi checkResultSearch(String value) {
        sleep(5000);
        $(".ProductCardLayout .ProductHeader__product-id")
                .shouldHave(text("Код товара: " + value));
        return this;
    }

    @Step("Добавляем товар в корзину")
    public ProductPageUi addProductOrder() {
        addOrderButton.click();
        return this;
    }

    @Step("Добавляем товар в список сравнения")
    public ProductPageUi addProductCompare() {
        addCompareButton.click();
        return this;
    }

    @Step("Проверяем добавление товара в корзину")
    public ProductPageUi checkResultAddOrder(String value) {
        sleep(5000);
        $(".UpsaleBasket__header-title")
                .shouldHave(text("Товар добавлен в корзину"));
        $(".ProductCardForUpsale__vendor-code")
                .shouldHave(text("Код товара: " + value));
        return this;
    }

    @Step("Закрываем всплывающее окно с товаром")
    public ProductPageUi closePopUpOrder() {
        closePopUpButton.click();
        return this;
    }
}
