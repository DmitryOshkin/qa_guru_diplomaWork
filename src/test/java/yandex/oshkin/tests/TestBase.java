package yandex.oshkin.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import yandex.oshkin.pages.ComparePage;
import yandex.oshkin.pages.MainPage;
import yandex.oshkin.pages.OrderPage;
import yandex.oshkin.pages.ProductPage;
import yandex.oshkin.tests.commonsteps.CommonStepsAPI;
import yandex.oshkin.tests.mobile.steps.StepsMobile;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static yandex.oshkin.helpers.Attach.*;
import static yandex.oshkin.helpers.DeviceSelection.getDeviceDriver;

public class TestBase {

    public MainPage mainPage = new MainPage();
    public ProductPage productPage = new ProductPage();
    public OrderPage orderPage = new OrderPage();
    public ComparePage comparePage = new ComparePage();
    public StepsMobile stepMobile = new StepsMobile();
    public CommonStepsAPI commonStepsAPI = new CommonStepsAPI();

    private static final String deviceHost = System.getProperty("deviceHost", "ui");  // ui / emulation / browserstack / realmobile

    @BeforeAll
    public static void setUp() {
        addListener("allure", new AllureSelenide());
        getDeviceDriver(deviceHost);
        if (deviceHost.equals("ui")) {
        } else {
            Configuration.browserSize = null;
        }
    }

    @BeforeEach
    public void startDriver() {
        if (deviceHost.equals("ui")) {
            open("/");
        } else {
            open();
        }
    }

    @AfterEach
    public void addAttachments() {
        String sessionId = getSessionId();
        screenshotAs("Last screenshot");
        pageSourceText();
        pageSourceHtml();
        if (deviceHost.equals("ui")) browserConsoleLogs();
        closeWebDriver();

        switch (deviceHost) {
            case "browserstack":
                browserstackVideo(sessionId);
                break;
            case "ui":
                selenoidVideo(sessionId);
                break;
        }
    }
}
