package yandex.oshkin.tests.ui;

import io.qameta.allure.Step;
import yandex.oshkin.tests.TestBase;

public class CommonSteps extends TestBase {

    int countOrder = 0;
    int countCompare = 0;

    @Step("Ищем товар")
    public CommonSteps searchProduct(String productCode) {
        mainPage
                .openMainPage()
                .search(productCode);
        productPage
                .checkResultSearch(productCode);
        return this;
    }

    @Step("Добавляем товар в корзину")
    public CommonSteps addToOrder(String productCode) {
        productPage
                .addProductOrder()
                .checkResultAddOrder(productCode)
                .closePopUpOrder();
        countOrder = countOrder + 1;
        mainPage
                .checkOrderCount(String.valueOf(countOrder));
        return this;
    }

    @Step("Добавляем товар в список сравнения")
    public CommonSteps addToCompare() {
        productPage
                .addProductCompare();
        countCompare = countCompare + 1;
        mainPage
                .checkCompareCount(String.valueOf(countCompare));
        return this;
    }
}